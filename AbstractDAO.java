package ua.kiev.prog.case2;

import ua.kiev.prog.shared.Id;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractDAO<K, T> {
    private final Connection conn;
    private final String table;

    public AbstractDAO(Connection conn, String table) {
        this.conn = conn;
        this.table = table;
    }

    public void add(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();

            StringBuilder names = new StringBuilder();
            StringBuilder values = new StringBuilder();

            for (Field f : fields) {
                f.setAccessible(true);

                names.append(f.getName()).append(',');
                values.append('"').append(f.get(t)).append("\",");
            }
            names.deleteCharAt(names.length() - 1); // last ','
            values.deleteCharAt(values.length() - 1); // last ','

            String sql = "INSERT INTO " + table + "(" + names.toString() +
                    ") VALUES(" + values.toString() + ")";

            try (Statement st = conn.createStatement()) {
                st.execute(sql);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public void delete(T t) {
        try {
            Field[] fields = t.getClass().getDeclaredFields();
            Field id = null;

            for (Field f : fields) {
                if (f.isAnnotationPresent(Id.class)) {
                    id = f;
                    id.setAccessible(true);
                    break;
                }
            }
            if (id == null)
                throw new RuntimeException("No Id field");

            String sql = "DELETE FROM " + table + " WHERE " + id.getName() +
                    " = \"" + id.get(t) + "\"";

            try (Statement st = conn.createStatement()) {
                st.execute(sql);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public List<T> getAll(Class<T> cls) {
        List<T> res = new ArrayList<>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT * FROM " + table)) {
                    ResultSetMetaData md = rs.getMetaData();

                    while (rs.next()) {
                        T client = cls.newInstance();

                        for (int i = 1; i <= md.getColumnCount(); i++) {
                            String columnName = md.getColumnName(i);

                            Field field = cls.getDeclaredField(columnName);
                            field.setAccessible(true);

                            field.set(client, rs.getObject(columnName));
                        }

                        res.add(client);
                    }
                }
            }

            return res;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public T find(int id, Class<T> tClass) {
        String sql = null;
        Field[] fields = tClass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(Id.class)) {
                sql = "SELECT * FROM " + table + " WHERE " + field.getName() + " = \"" + Integer.toString(id) + "\" ;";
            }
        }
        try (Statement st = conn.createStatement()) {
            try (ResultSet rs = st.executeQuery(sql)) {
                ResultSetMetaData md = rs.getMetaData();
                rs.next();
                T client = tClass.newInstance();

                for (int i = 1; i <= md.getColumnCount(); i++) {
                    String columnName = md.getColumnName(i);

                    Field field = tClass.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(client, rs.getObject(columnName));
                }
                return client;
            } catch (IllegalAccessException | InstantiationException | NoSuchFieldException e) {
                throw new RuntimeException();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(T t) {
        String sql = null;
        try {
            Field[] fields = t.getClass().getDeclaredFields();

            StringBuilder parameters = new StringBuilder();
            String nameId = null;
            String valueId = null;
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Id.class)) {
                    nameId = field.getName();
                    valueId = field.get(t).toString();
                    continue;
                }
                parameters.append(field.getName()).append(" = ").append("\"").append(field.get(t)).append("\"").append(",");
            }
            parameters.deleteCharAt(parameters.length() - 1); // last ','
            sql = "UPDATE " + table + " SET " + parameters + " WHERE " + nameId + " = " + valueId  + ";";
        } catch (IllegalAccessException e) {
            throw new RuntimeException();
        }
        try (Statement st = conn.createStatement()) {
            st.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
}



package com.gmail.a.a.kravchenko;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ReleaseClientsDAO implements ClientsDAO {
    private Connection connection = null;

    public ReleaseClientsDAO() throws SQLException {
        this.connection = ConnectionUtils.toGetConnection();
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Collection<Clients> findAll() throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM Clients;")) {
            try (ResultSet rs = ps.executeQuery()) {
                ArrayList<Clients> listClients = new ArrayList<>();
                int i = 0;
                for (; rs.next(); ) {
                    Clients tempClient = new Clients();
                    tempClient.setId(rs.getInt("id"));
                    tempClient.setName(rs.getString("name"));
                    tempClient.setAge(rs.getInt("age"));
                    listClients.add(i, tempClient);
                    i++;
                }
                return listClients;
            }
        }
    }

    public Clients find(String parameter, String value) throws SQLException {
        PreparedStatement ps = null;
        if ("id".equals(parameter)) {
            ps = connection.prepareStatement("SELECT * FROM Clients WHERE id = ?;");
        } else if ("name".equals(parameter)) {
            ps = connection.prepareStatement("SELECT * FROM Clients WHERE name = ?;");
        } else {
            ps = connection.prepareStatement("SELECT * FROM Clients WHERE age = ?;");
        }
        ps.setString(1, value);
        try {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                Clients tempClient = new Clients();
                tempClient.setId(rs.getInt("id"));
                tempClient.setName(rs.getString("name"));
                tempClient.setAge(rs.getInt("age"));
                return tempClient;
            }
        } finally {
            ps.close();
        }
    }

    public boolean create(Clients client) throws SQLException {
        connection.setAutoCommit(false);
        try {
            try {
                try (PreparedStatement ps = connection.prepareStatement("INSERT INTO Clients (name, age) VALUES(?, ?);")) {
                    ps.setString(1, client.getName());
                    ps.setInt(2, client.getAge());
                    ps.executeUpdate();
                    connection.commit();
                    return true;
                }
            } catch (Exception ex) {
                connection.rollback();
                return false;
            }
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public boolean delete(int id) throws SQLException {
        connection.setAutoCommit(false);
        try {
            try {
                try (PreparedStatement ps = connection.prepareStatement("DELETE FROM Clients WHERE name = ?;")) {
                    ps.setString(1, Integer.toString(id));
                    ps.executeUpdate();
                    connection.commit();
                    return true;
                }
            } catch (Exception ex) {
                connection.rollback();
                return false;
            }
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public boolean update(Clients client) throws SQLException {
        connection.setAutoCommit(false);
        try {
            try {
                try (PreparedStatement ps = connection.prepareStatement("UPDATE Clients SET name = ?, age = ? WHERE ID = ?;")) {
                    ps.setString(1, client.getName());
                    ps.setInt(2, client.getAge());
                    ps.setInt(3, client.getId());
                    ps.executeUpdate();
                    connection.commit();
                    return true;
                }
            } catch (Exception ex) {
                connection.rollback();
                return false;
            }
        } finally {
            connection.setAutoCommit(true);
        }
    }
}

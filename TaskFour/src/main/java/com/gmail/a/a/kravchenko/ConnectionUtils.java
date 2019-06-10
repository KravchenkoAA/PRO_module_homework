package com.gmail.a.a.kravchenko;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/test?serverTimezone=Europe/Kiev&autoReconnect=true&useSSL=false";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "SQLpassword";

    public static Connection toGetConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        return connection;
    }

    public static void toCloseConnection (ClientsDAO clientsDAO) {
        try {
            clientsDAO.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

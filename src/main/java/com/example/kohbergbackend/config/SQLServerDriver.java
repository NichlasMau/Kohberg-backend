package com.example.kohbergbackend.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class SQLServerDriver  {
    public static Connection getConnection(String url, String username, String password) throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Properties properties = new Properties();
        properties.setProperty("user", username);
        properties.setProperty("password", password);

        return DriverManager.getConnection(url, properties);
    }

    public static void main(String[] args) {
        String url = "jdbc:sqlserver://your-server-name.database.windows.net:1433;database=your-database-name";
        String username = "your-username";
        String password = "your-password";

        try (Connection connection = getConnection(url, username, password)) {
            // Do something with the connection
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

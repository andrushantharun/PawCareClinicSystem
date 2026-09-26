/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pawcare.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Arulthas
 */
public class DBConnection {
       private static final String URL = "jdbc:mysql://localhost:3306/pawcare_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = ""; // default XAMPP MySQL password is empty

    // The single shared instance
    private static Connection connection = null;

    // Private constructor prevents other classes from doing "new DBConnection()"
    private DBConnection() {
    }

    /**
     * Returns the single shared Connection instance, creating it
     * on first use, and re-creating it if it has been closed.
     * @return 
     * @throws java.sql.SQLException
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found. Check Libraries.", e);
            }
        }
        return connection;
    }
}

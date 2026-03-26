package com.bookstore.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bookstore",
                "root",
                "password"
            );
        } catch (Exception e) {
            return null;
        }
    }
}

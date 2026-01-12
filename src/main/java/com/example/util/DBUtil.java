package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    // Read database info from environment variables
    private static final String URL = "jdbc:mysql://" + 
        System.getenv("DB_HOST") + ":3306/" + System.getenv("DB_NAME");
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

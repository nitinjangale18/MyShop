package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    // Railway environment variables
    private static final String HOST =
            System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";

    private static final String PORT =
            System.getenv("MYSQLPORT") != null ? System.getenv("MYSQLPORT") : "3306";

    private static final String DB_NAME =
            System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "shoppingdb2";

    private static final String USER =
            System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";

    private static final String PASSWORD =
            System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "";

    // JDBC URL
    private static final String URL =
            "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME +
            "?useSSL=false&allowPublicKeyRetrieval=true";

    // Load MySQL driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

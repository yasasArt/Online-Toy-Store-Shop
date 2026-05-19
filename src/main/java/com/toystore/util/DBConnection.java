package com.toystore.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.io.InputStream;
import java.util.Properties;

/**
 * Provides raw JDBC connections using credentials from application.properties.
 * Used by all DB-based service classes.
 */
public class DBConnection {

    private static String URL;
    private static String USER;
    private static String PASSWORD;

    static {
        try {
            // Read datasource config from application.properties at runtime
            InputStream is = DBConnection.class.getClassLoader()
                    .getResourceAsStream("application.properties");
            Properties props = new Properties();
            props.load(is);

            URL      = props.getProperty("spring.datasource.url",
                           "jdbc:mysql://localhost:3306/toy_store");
            USER     = props.getProperty("spring.datasource.username", "root");
            PASSWORD = props.getProperty("spring.datasource.password", "");

        } catch (Exception e) {
            // Fallback defaults if properties file cannot be read
            URL      = "jdbc:mysql://localhost:3306/toy_store";
            USER     = "root";
            PASSWORD = "";
            System.err.println("DBConnection: could not load application.properties, using defaults.");
        }
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

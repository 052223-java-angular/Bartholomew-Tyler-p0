package com.revature.app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The ConnectionFactory class handles the connection between the app
 * and the postgresql database.
 */
public class ConnectionFactory {
    private static ConnectionFactory instance;
    private Connection connection;

    private ConnectionFactory() throws ClassNotFoundException, IOException, SQLException {
        Class.forName("org.postgresql.Driver");
        Properties props = getProperties();
        connection = DriverManager.getConnection(
                props.getProperty("url"),
                props.getProperty("username"),
                props.getProperty("password"));
    }

    /* ----------------- Methods ----------------- */

    public static ConnectionFactory getInstance() throws SQLException, ClassNotFoundException, IOException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    /* ----------------- Helper Methods ----------------- */

    private Properties getProperties() throws IOException {
        Properties props = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new IOException("Unable to find application.properties");
            }
            props.load(input);
        }
        return props;
    }
}
package org.example.servlet.app.dao.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    private static DataSource INSTANCE;
    private static final String DB_URL = "jdbc:postgresql://localhost:5437/goit-lesson-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";
    BasicDataSource basicDataSource = new BasicDataSource();

    private DataSource() {
        basicDataSource.setUrl(DB_URL);
        basicDataSource.setUsername(DB_USER);
        basicDataSource.setPassword(DB_PASSWORD);
        basicDataSource.setDriverClassName("org.postgresql.Driver");
    }

    public static DataSource getInstance() {
        if (INSTANCE != null) {
            return INSTANCE;
        } else {
            INSTANCE = new DataSource();
            return INSTANCE;
        }
    }

    public Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }
}

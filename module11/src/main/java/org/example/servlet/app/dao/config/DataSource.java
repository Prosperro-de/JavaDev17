package org.example.servlet.app.dao.config;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static DataSource INSTANCE;
    private static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";
    private static final String DB_URL = "jdbc:postgresql://localhost:5437/goit-lesson-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";
    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private DataSource() {
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        config.setDriverClassName(POSTGRESQL_DRIVER);
        ds = new HikariDataSource(config);
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
        return ds.getConnection();
    }
}

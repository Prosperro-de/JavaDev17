package org.example;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {
    private static DataSource INSTANCE;
    private static final String DB_URL = "jdbc:postgresql://localhost:5437/goit-lesson-db";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "password";

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    private DataSource() {
        config.setJdbcUrl(DB_URL);
        config.setUsername(DB_USER);
        config.setPassword(DB_PASSWORD);
        ds = new HikariDataSource(config);
        Flyway flyway = Flyway.configure()
                .dataSource(ds)
                .locations("db/migrations")
                .load();
        flyway.migrate();
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

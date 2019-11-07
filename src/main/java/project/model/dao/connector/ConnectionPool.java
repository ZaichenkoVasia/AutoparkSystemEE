package project.model.dao.connector;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;
import project.model.exception.InvalidDatabaseConnectionException;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ResourceBundle;

public final class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);
    private static volatile DataSource dataSource;

    public ConnectionPool(String fileConfigName) {
        ResourceBundle resource = ResourceBundle.getBundle(fileConfigName);
        if (dataSource == null) {
            synchronized (DataSource.class) {
                if (dataSource == null) {
                    BasicDataSource basicDataSource = new BasicDataSource();
                    basicDataSource.setUrl(resource.getString("db.url"));
                    basicDataSource.setDriverClassName(resource.getString("db.driver"));
                    basicDataSource.setUsername(resource.getString("db.user"));
                    basicDataSource.setPassword(resource.getString("db.password"));
                    basicDataSource.setMinIdle(5);
                    basicDataSource.setMaxIdle(10);
                    basicDataSource.setMaxOpenPreparedStatements(100);
                    dataSource = basicDataSource;
                }
            }
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Could not get connection from database" + e.getMessage());
            throw new InvalidDatabaseConnectionException("Could not get connection from database " + e.getMessage());
        }
    }
}

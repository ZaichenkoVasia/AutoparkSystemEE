package model.dao.connection;

import model.dao.constants.ExceptionMessages;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class PoolConection {

    private static final Logger logger = Logger.getLogger(PoolConection.class);

    private DataSource dataSource;
    private static BasicDataSource basicDataSource;

    public PoolConection() {
        if (basicDataSource == null) {
            try {
                ResourceBundle dbConfig = ResourceBundle.getBundle("database");
                basicDataSource = new BasicDataSource();
                basicDataSource.setUrl(dbConfig.getString("url"));
                basicDataSource.setDriverClassName(dbConfig.getString("driver"));
                basicDataSource.setUsername(dbConfig.getString("user"));
                basicDataSource.setPassword(dbConfig.getString("password"));
                basicDataSource.setMinIdle(Integer.parseInt(dbConfig.getString("minIdle")));
                basicDataSource.setMaxIdle(Integer.parseInt(dbConfig.getString("maxIdle")));
                basicDataSource.setMaxActive(Integer.parseInt(dbConfig.getString("maxActive")));
                basicDataSource.setMaxOpenPreparedStatements(Integer.parseInt(dbConfig.getString("maxOpenPreparedStatements")));
                dataSource = basicDataSource;
            } catch (Exception e) {
                logger.warn(ExceptionMessages.CAN_NOT_INSTANTIATE_CONNECTION_POOL, e);
            }
        }
    }

    public Connection getConnection() throws SQLException{
        logger.info("Getting connection");
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_ESTABLISH_CONNECTION, e);
            throw e;
        }
    }

}

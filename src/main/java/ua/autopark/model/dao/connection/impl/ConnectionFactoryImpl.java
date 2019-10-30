package ua.autopark.model.dao.connection.impl;

import ua.autopark.model.dao.connection.ConnectionFactory;
import org.apache.log4j.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

    private static final Logger logger = Logger.getLogger(ConnectionFactoryImpl.class);
    private static ConnectionFactory factory;

    private DataSource dataSource;

    private ConnectionFactoryImpl() {
        try {
            InitialContext initialContext = new InitialContext();
            dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/bus_station_demo");
        } catch (NamingException e) {
            logger.warn(e);
        }
    }

    public static ConnectionFactory getInstance(){
        if (factory == null){
            synchronized (ConnectionFactoryImpl.class){
                factory = new ConnectionFactoryImpl();
            }
        }
        return factory;
    }

    @Override
    public Connection getConnection() throws SQLException{
        logger.info("Getting connection");
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.error(e);
            throw e;
        }
    }

    @Override
    public void freeConnection(Connection connection){
        logger.info("Close connection");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}

package ua.autopark.model.dao.impl;

import ua.autopark.model.dao.*;
import ua.autopark.model.dao.connection.ConnectionFactory;
import ua.autopark.model.dao.connection.impl.ConnectionFactoryImpl;

public class DAOFactoryImpl implements DAOFactory {

    private static DAOFactory instance;
    private ConnectionFactory connectionFactory;

    private DAOFactoryImpl() {
        connectionFactory = ConnectionFactoryImpl.getInstance();
    }

    @Override
    public AdminDAO getAdminDAO() {
        return new AdminDAOImpl(connectionFactory);
    }

    @Override
    public DriverDAO getDriverDAO() {
        return new DriverDAOImpl(connectionFactory);
    }

    @Override
    public BusDAO getBusDAO() {
        return new BusDAOImpl(connectionFactory);
    }

    @Override
    public RouteDAO getRouteDAO() {
        return new RouteDAOImpl(connectionFactory);
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserDAOImpl(connectionFactory);
    }

    public static DAOFactory getInstance(){
        if (instance == null){
            synchronized (DAOFactory.class){
                instance = new DAOFactoryImpl();
            }
        }
        return instance;
    }
}

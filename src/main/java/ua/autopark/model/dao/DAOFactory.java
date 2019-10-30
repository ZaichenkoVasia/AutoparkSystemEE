package ua.autopark.model.dao;


public interface DAOFactory {

    AdminDAO getAdminDAO();

    DriverDAO getDriverDAO();

    BusDAO getBusDAO();

    RouteDAO getRouteDAO();

    UserDAO getUserDAO();
}

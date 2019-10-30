package ua.autopark.model.dao;

import ua.autopark.model.domain.Driver;

import java.sql.Connection;
import java.util.List;

public interface DriverDAO extends GenericDAO<Driver> {

    Driver getDriverByBusId(int idBus);

    void cancelDriverFromBus(int idBus, Connection connection);

    List<Driver> getFreeDrivers();
}

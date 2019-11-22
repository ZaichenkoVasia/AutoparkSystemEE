package controller.service;

import controller.exception.ServiceLayerException;
import domain.Driver;

import java.sql.Connection;
import java.util.List;

public interface DriverService extends GenericService<Driver, Integer> {

    Driver getDriverByUserId(Integer idUser);

    Driver getDriverByBusId(Integer idBus);

    void setStatusNew(Integer idDriver);

    void setStatusWork(Integer idDriver);

    void cancelDriverFromBus(Integer idBus);

    void updateBusInfoForDriver(Integer idBus, Integer idDriver);

    List<Driver> getFreeDrivers();
}
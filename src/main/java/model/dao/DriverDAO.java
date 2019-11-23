package model.dao;

import model.entity.DriverEntity;

import java.util.List;

public interface DriverDAO extends GenericDAO<DriverEntity, Integer> {

    DriverEntity getDriverByUserId(Integer idUser);

    DriverEntity getDriverByBusId(Integer idBus);

    void setStatusNew(Integer idDriver);

    void setStatusWork(Integer idDriver);

    void cancelDriverFromBus(Integer idBus);

    void updateBusInfoForDriver(Integer idBus, Integer idDriver);

    List<DriverEntity> getFreeDrivers();
}

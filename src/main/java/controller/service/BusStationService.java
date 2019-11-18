package controller.service;

import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import domain.*;

public interface BusStationService {

    Boolean saveAdmin(Admin admin, User user, String idAdmin, String idUser);

    Driver getDriverAccountDataByUserId(Integer idUser);

    Boolean cancelDriver(Integer idBus);

    Boolean appointDriverToBus(Integer idBus, Integer idDriver);

    Boolean saveDriver(Driver driver, User user, String idDriver, String idUser);

    void deleteDriver(Integer idDriver);

    void deleteRoute(Integer idRoute);

    Boolean appointBusToRoute(Integer idRoute, Integer idBus);

    void deleteBus(Integer idBus);

    Boolean saveBus(Bus bus, Schedule schedule, String idBus, String idSchedule);

    void cancelBus(Integer idBus, Integer idRoute);
}

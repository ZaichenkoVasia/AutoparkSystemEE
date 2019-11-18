package controller.service.impl;

import controller.exception.ServiceLayerException;
import controller.service.AbstractGenericService;
import controller.service.DriverService;
import domain.Driver;
import model.dao.DriverDAO;
import org.apache.log4j.Logger;

import java.util.List;

public class DriverServiceImpl extends AbstractGenericService<Driver> implements DriverService {

    private DriverDAO driverDAO;
    private static final Logger LOGGER = Logger.getLogger(DriverServiceImpl.class);

    public DriverServiceImpl(DriverDAO driverDAO) {
        super(driverDAO);
        this.driverDAO = driverDAO;
    }

    @Override
    public Driver getDriverByUserId(Integer idUser) {
        LOGGER.info("Getting driver by user id");
        return driverDAO.getDriverByUserId(idUser);
    }

    @Override
    public Driver getDriverByBusId(Integer idBus) {
        LOGGER.info("Getting driver by bus id");
        return driverDAO.getDriverByBusId(idBus);
    }

    @Override
    public void setStatusNew(Integer idDriver) {
        LOGGER.info("Setting status new for driver");
        driverDAO.setStatusNew(idDriver);
    }

    @Override
    public void setStatusWork(Integer idDriver) {
        LOGGER.info("Setting status work for driver");
        driverDAO.setStatusWork(idDriver);
    }

    @Override
    public void cancelDriverFromBus(Integer idBus) {
        LOGGER.info("Cancel driver from bus");
        driverDAO.cancelDriverFromBus(idBus);
    }

    @Override
    public List<Driver> getFreeDrivers() throws ServiceLayerException {
        LOGGER.info("Getting free drivers");
        return driverDAO.getFreeDrivers();
    }

    @Override
    public void updateBusInfoForDriver(Integer idBus, Integer idDriver) throws ServiceLayerException {
        LOGGER.info("Assigning bus for driver");
        driverDAO.updateBusInfoForDriver(idBus, idDriver);
    }
}

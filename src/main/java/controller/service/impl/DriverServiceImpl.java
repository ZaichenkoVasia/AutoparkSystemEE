package controller.service.impl;

import controller.exception.InvalidDataRuntimeException;
import controller.exception.ServiceLayerRuntimeException;
import controller.service.DriverService;
import controller.service.mapper.DriverMapper;
import controller.service.validator.impl.DriverValidator;
import domain.Driver;
import model.dao.DriverDAO;
import model.entity.DriverEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DriverServiceImpl implements DriverService {
    private static final Logger LOGGER = Logger.getLogger(DriverServiceImpl.class);

    private DriverDAO driverDAO;
    private DriverMapper mapper;
    private DriverValidator driverValidator;

    public DriverServiceImpl(DriverDAO driverDAO, DriverMapper mapper, DriverValidator driverValidator) {
        this.driverDAO = driverDAO;
        this.mapper = mapper;
        this.driverValidator = driverValidator;
    }

    @Override
    public Driver getDriverByUserId(Integer idUser) {
        LOGGER.info("Getting driver by user id");
        if (Objects.isNull(idUser)) {
            LOGGER.error("Incorrect getDriverByUserId value");
            throw new InvalidDataRuntimeException("Incorrect getDriverByUserId value");
        }
        DriverEntity driverEntity = driverDAO.getDriverByUserId(idUser);
        return mapper.mapDriverEntityToDriver(driverEntity);
    }

    @Override
    public Driver getDriverByBusId(Integer idBus) {
        LOGGER.info("Getting driver by bus id");
        if (Objects.isNull(idBus)) {
            LOGGER.error("Incorrect getDriverByBusId value");
            throw new InvalidDataRuntimeException("Incorrect getDriverByBusId value");
        }
        DriverEntity driverEntity = driverDAO.getDriverByBusId(idBus);
        if (driverEntity == null) {
            return null;
        }
        return mapper.mapDriverEntityToDriver(driverEntity);
    }

    @Override
    public void setStatusNew(Integer idDriver) {
        LOGGER.info("Setting status new for driver");
        if (Objects.isNull(idDriver)) {
            LOGGER.error("Incorrect setStatusNew value");
            throw new InvalidDataRuntimeException("Incorrect setStatusNew value");
        }
        driverDAO.setStatusNew(idDriver);
    }

    @Override
    public void setStatusWork(Integer idDriver) {
        LOGGER.info("Setting status work for driver");
        if (Objects.isNull(idDriver)) {
            LOGGER.error("Incorrect setStatusNew value");
            throw new InvalidDataRuntimeException("Incorrect setStatusNew value");
        }
        driverDAO.setStatusWork(idDriver);
    }

    @Override
    public void cancelDriverFromBus(Integer idBus) {
        LOGGER.info("Cancel driver from bus");
        if (Objects.isNull(idBus)) {
            LOGGER.error("Incorrect cancelDriverFromBus value");
            throw new InvalidDataRuntimeException("Incorrect cancelDriverFromBus value");
        }
        driverDAO.cancelDriverFromBus(idBus);
    }

    @Override
    public List<Driver> getFreeDrivers() throws ServiceLayerRuntimeException {
        LOGGER.info("Getting free drivers");
        List<DriverEntity> result = driverDAO.getFreeDrivers();
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapDriverEntityToDriver)
                .collect(Collectors.toList());
    }

    @Override
    public void updateBusInfoForDriver(Integer idBus, Integer idDriver) throws ServiceLayerRuntimeException {
        LOGGER.info("Assigning bus for driver");
        if (Objects.isNull(idBus) || Objects.isNull(idDriver)) {
            LOGGER.error("Incorrect updateBusInfoForDriver value");
            throw new InvalidDataRuntimeException("Incorrect updateBusInfoForDriver value");
        }
        driverDAO.updateBusInfoForDriver(idBus, idDriver);
    }

    @Override
    public Integer insertElement(Driver element) {
        LOGGER.info("Inserting element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect insertElement value");
            throw new InvalidDataRuntimeException("Incorrect insertElement value");
        }
        driverValidator.validate(element);
        DriverEntity driverEntity = mapper.mapDriverToDriverEntity(element);
        return driverDAO.insertElement(driverEntity);
    }

    @Override
    public Driver getElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect getElementById value");
            throw new InvalidDataRuntimeException("Incorrect getElementById value");
        }
        DriverEntity driverEntity = driverDAO.getElementById(id);
        return mapper.mapDriverEntityToDriver(driverEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect deleteElement value");
            throw new InvalidDataRuntimeException("Incorrect deleteElement value");
        }
        driverDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Driver element) {
        LOGGER.info("Updating element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect updateElement value");
            throw new InvalidDataRuntimeException("Incorrect updateElement value");
        }
        driverValidator.validate(element);
        DriverEntity driverEntity = mapper.mapDriverToDriverEntity(element);
        driverDAO.updateElement(driverEntity);
    }

    @Override
    public Integer getElementsAmount() {
        LOGGER.info("Getting elements amount");
        return driverDAO.getElementsCount();
    }

    @Override
    public List<Driver> getPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<DriverEntity> result = driverDAO.getPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapDriverEntityToDriver)
                .collect(Collectors.toList());
    }
}

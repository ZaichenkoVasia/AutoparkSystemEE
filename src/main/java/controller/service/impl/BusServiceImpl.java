package controller.service.impl;

import controller.exception.InvalidDataRuntimeException;
import controller.service.BusService;
import controller.service.mapper.BusMapper;
import controller.service.validator.impl.BusValidator;
import domain.Bus;
import model.dao.BusDAO;
import model.entity.BusEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BusServiceImpl implements BusService {
    private static final Logger LOGGER = Logger.getLogger(BusStationServiceImpl.class);

    private BusDAO busDAO;
    private BusMapper mapper;
    private BusValidator busValidator;

    public BusServiceImpl(BusDAO busDAO, BusMapper mapper, BusValidator busValidator) {
        this.busDAO = busDAO;
        this.mapper = mapper;
        this.busValidator = busValidator;
    }

    @Override
    public List<Bus> findFreeBuses() {
        LOGGER.info("Getting free buses");
        List<BusEntity> result = busDAO.findFreeBuses();
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapBusEntityToBus)
                .collect(Collectors.toList());
    }

    @Override
    public Integer countBusesOnRouteById(Integer idRoute) {
        LOGGER.info("Counting buses on route");
        if (Objects.isNull(idRoute)) {
            LOGGER.error("Incorrect countBusesOnRouteById value");
            throw new InvalidDataRuntimeException("Incorrect countBusesOnRouteById value");
        }
        return busDAO.countBusesOnRouteById(idRoute);
    }

    @Override
    public void cancelBusFromRoute(Integer idBus) {
        LOGGER.info("Try cancel bus from route");
        if (Objects.isNull(idBus)) {
            LOGGER.error("Incorrect cancelBusFromRoute value");
            throw new InvalidDataRuntimeException("Incorrect cancelBusFromRoute value");
        }
        busDAO.cancelBusFromRoute(idBus);
    }

    @Override
    public void appointBusToRoute(Integer idRoute, Integer idBus) {
        LOGGER.info("Assigning appoint bus to route");
        if (Objects.isNull(idBus) || Objects.isNull(idRoute)) {
            LOGGER.error("Incorrect appointBusToRoute value");
            throw new InvalidDataRuntimeException("Incorrect appointBusToRoute value");
        }
        busDAO.appointBusToRoute(idRoute, idBus);
    }

    @Override
    public List<Bus> findBusesByIdRoute(Integer idRoute) {
        LOGGER.info("Getting buses by route id");
        if (Objects.isNull(idRoute)) {
            LOGGER.error("Incorrect findBusesByIdRoute value");
            throw new InvalidDataRuntimeException("Incorrect findBusesByIdRoute value");
        }
        List<BusEntity> result = busDAO.findBusesByIdRoute(idRoute);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapBusEntityToBus)
                .collect(Collectors.toList());
    }

    @Override
    public Integer insertElement(Bus element) {
        LOGGER.info("Inserting element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect insertElement value");
            throw new InvalidDataRuntimeException("Incorrect insertElement value");
        }
        busValidator.validate(element);
        BusEntity busEntity = mapper.mapBusToBusEntity(element);
        return busDAO.insertElement(busEntity);
    }

    @Override
    public Bus findElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect findElementById value");
            throw new InvalidDataRuntimeException("Incorrect findElementById value");
        }
        BusEntity busEntity = busDAO.findElementById(id);
        return mapper.mapBusEntityToBus(busEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect deleteElement value");
            throw new InvalidDataRuntimeException("Incorrect deleteElement value");
        }
        busDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Bus element) {
        LOGGER.info("Updating element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect updateElement value");
            throw new InvalidDataRuntimeException("Incorrect updateElement value");
        }
        busValidator.validate(element);
        BusEntity busEntity = mapper.mapBusToBusEntity(element);
        busDAO.updateElement(busEntity);
    }

    @Override
    public Integer findElementsAmount() {
        LOGGER.info("Getting elements amount");
        return busDAO.count();
    }

    @Override
    public List<Bus> findPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<BusEntity> result = busDAO.findPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapBusEntityToBus)
                .collect(Collectors.toList());
    }
}

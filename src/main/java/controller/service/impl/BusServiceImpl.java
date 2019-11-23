package controller.service.impl;

import controller.service.BusService;
import controller.service.mapper.BusMapper;
import domain.Bus;
import model.dao.BusDAO;
import model.entity.BusEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class BusServiceImpl implements BusService {
    private static final Logger LOGGER = Logger.getLogger(BusStationServiceImpl.class);

    private BusDAO busDAO;
    private BusMapper mapper;

    public BusServiceImpl(BusDAO busDAO, BusMapper mapper) {
        this.busDAO = busDAO;
        this.mapper = mapper;
    }

    @Override
    public List<Bus> getFreeBuses() {
        LOGGER.info("Getting free buses");
        List<BusEntity> result = busDAO.getFreeBuses();
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapBusEntityToBus)
                .collect(Collectors.toList());
    }

    @Override
    public Integer countBusesOnRouteById(Integer idRoute) {
        LOGGER.info("Counting buses on route");
        return busDAO.countBusesOnRouteById(idRoute);
    }

    @Override
    public void cancelBusFromRoute(Integer idBus) {
        LOGGER.info("Try cancel bus from route");
        busDAO.cancelBusFromRoute(idBus);
    }

    @Override
    public void appointBusToRoute(Integer idRoute, Integer idBus) {
        LOGGER.info("Assigning appoint bus to route");
        busDAO.appointBusToRoute(idRoute, idBus);
    }

    @Override
    public List<Bus> getBusesByIdRoute(Integer idRoute) {
        LOGGER.info("Getting buses by route id");
        List<BusEntity> result = busDAO.getBusesByIdRoute(idRoute);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapBusEntityToBus)
                .collect(Collectors.toList());
    }

    @Override
    public Integer insertElement(Bus element) {
        LOGGER.info("Inserting element");
        BusEntity busEntity = mapper.mapBusToBusEntity(element);
        return busDAO.insertElement(busEntity);
    }

    @Override
    public Bus getElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        BusEntity busEntity = busDAO.getElementById(id);
        return mapper.mapBusEntityToBus(busEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        busDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Bus element) {
        LOGGER.info("Updating element");
        BusEntity busEntity = mapper.mapBusToBusEntity(element);
        busDAO.updateElement(busEntity);
    }

    @Override
    public Integer getElementsAmount() {
        LOGGER.info("Getting elements amount");
        return busDAO.getElementsCount();
    }

    @Override
    public List<Bus> getPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<BusEntity> result = busDAO.getPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapBusEntityToBus)
                .collect(Collectors.toList());
    }
}

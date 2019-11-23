package controller.service.impl;

import controller.service.RouteService;
import controller.service.mapper.RouteMapper;
import domain.Route;
import model.dao.RouteDAO;
import model.entity.RouteEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RouteServiceImpl implements RouteService {
    private static final Logger LOGGER = Logger.getLogger(DriverServiceImpl.class);

    private RouteDAO routeDAO;
    private RouteMapper mapper;

    public RouteServiceImpl(RouteDAO routeDAO, RouteMapper mapper) {
        this.routeDAO = routeDAO;
        this.mapper = mapper;
    }

    @Override
    public void setStatusEmpty(Integer idRoute) {
        LOGGER.info("Setting status empty for route");
        routeDAO.setStatusEmpty(idRoute);
    }

    @Override
    public void setStatusWork(Integer idRoute) {
        LOGGER.info("Setting status work for route");
        routeDAO.setStatusWork(idRoute);
    }

    @Override
    public void cancelAll(Integer idRoute) {
        LOGGER.info("Cancel all buses from route");
        routeDAO.cancelAll(idRoute);
    }

    @Override
    public List<Route> searchByCriteria(String departure, String arrival) {
        LOGGER.info("Searching by criteria");
        List<RouteEntity> result = routeDAO.searchByCriteria(departure, arrival);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapRouteEntityToRoute)
                .collect(Collectors.toList());
    }

    @Override
    public Integer insertElement(Route element) {
        LOGGER.info("Inserting element");
        RouteEntity driverEntity = mapper.mapRouteToRouteEntity(element);
        return routeDAO.insertElement(driverEntity);
    }

    @Override
    public Route getElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        RouteEntity routeEntity = routeDAO.getElementById(id);
        return mapper.mapRouteEntityToRoute(routeEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        routeDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Route element) {
        LOGGER.info("Updating element");
        RouteEntity routeEntity = mapper.mapRouteToRouteEntity(element);
        routeDAO.updateElement(routeEntity);
    }

    @Override
    public Integer getElementsAmount() {
        LOGGER.info("Getting elements amount");
        return routeDAO.getElementsCount();
    }

    @Override
    public List<Route> getPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<RouteEntity> result = routeDAO.getPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapRouteEntityToRoute)
                .collect(Collectors.toList());
    }
}

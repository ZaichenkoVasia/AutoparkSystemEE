package model.service.impl;

import model.exception.InvalidDataRuntimeException;
import model.service.RouteService;
import model.service.mapper.RouteMapper;
import model.service.validator.impl.RouteValidator;
import model.domain.Route;
import model.dao.RouteDAO;
import model.entity.RouteEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RouteServiceImpl implements RouteService {
    private static final Logger LOGGER = Logger.getLogger(DriverServiceImpl.class);

    private RouteDAO routeDAO;
    private RouteMapper mapper;
    private RouteValidator routeValidator;

    public RouteServiceImpl(RouteDAO routeDAO, RouteMapper mapper, RouteValidator routeValidator) {
        this.routeDAO = routeDAO;
        this.mapper = mapper;
        this.routeValidator = routeValidator;
    }

    @Override
    public void setStatusEmpty(Integer idRoute) {
        LOGGER.info("Setting status empty for route");
        if (Objects.isNull(idRoute)) {
            LOGGER.error("Incorrect setStatusEmpty value");
            throw new InvalidDataRuntimeException("Incorrect setStatusEmpty value");
        }
        routeDAO.setStatusEmpty(idRoute);
    }

    @Override
    public void setStatusWork(Integer idRoute) {
        LOGGER.info("Setting status work for route");
        if (Objects.isNull(idRoute)) {
            LOGGER.error("Incorrect setStatusWork value");
            throw new InvalidDataRuntimeException("Incorrect setStatusWork value");
        }
        routeDAO.setStatusWork(idRoute);
    }

    @Override
    public void cancelAll(Integer idRoute) {
        LOGGER.info("Cancel all buses from route");
        if (Objects.isNull(idRoute)) {
            LOGGER.error("Incorrect cancelAll value");
            throw new InvalidDataRuntimeException("Incorrect cancelAll value");
        }
        routeDAO.cancelAll(idRoute);
    }

    @Override
    public List<Route> searchByCriteria(String departure, String arrival) {
        LOGGER.info("Searching by criteria");
        if (Objects.isNull(departure) || Objects.isNull(arrival)) {
            LOGGER.error("Incorrect findByCriteria value");
            throw new InvalidDataRuntimeException("Incorrect findByCriteria value");
        }
        List<RouteEntity> result = routeDAO.findByCriteria(departure, arrival);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapRouteEntityToRoute)
                .collect(Collectors.toList());
    }

    @Override
    public Integer insertElement(Route element) {
        LOGGER.info("Inserting element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect insertElement value");
            throw new InvalidDataRuntimeException("Incorrect insertElement value");
        }
        routeValidator.validate(element);
        RouteEntity driverEntity = mapper.mapRouteToRouteEntity(element);
        return routeDAO.insertElement(driverEntity);
    }

    @Override
    public Route findElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect findElementById value");
            throw new InvalidDataRuntimeException("Incorrect findElementById value");
        }
        RouteEntity routeEntity = routeDAO.findElementById(id);
        return mapper.mapRouteEntityToRoute(routeEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect deleteElement value");
            throw new InvalidDataRuntimeException("Incorrect deleteElement value");
        }
        routeDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Route element) {
        LOGGER.info("Updating element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect updateElement value");
            throw new InvalidDataRuntimeException("Incorrect updateElement value");
        }
        routeValidator.validate(element);
        RouteEntity routeEntity = mapper.mapRouteToRouteEntity(element);
        routeDAO.updateElement(routeEntity);
    }

    @Override
    public Integer findElementsAmount() {
        LOGGER.info("Getting elements amount");
        return routeDAO.count();
    }

    @Override
    public List<Route> findPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<RouteEntity> result = routeDAO.findPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapRouteEntityToRoute)
                .collect(Collectors.toList());
    }
}

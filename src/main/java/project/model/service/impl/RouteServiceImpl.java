package project.model.service.impl;

import org.apache.log4j.Logger;
import project.model.dao.RouteDao;
import project.model.domain.Route;
import project.model.entity.RouteEntity;
import project.model.exception.InvalidEntityCreation;
import project.model.service.RouteService;
import project.model.service.mapper.RouteMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RouteServiceImpl implements RouteService {
    private static final Logger LOGGER = Logger.getLogger(RouteServiceImpl.class);

    private final RouteDao routeDao;
    private final RouteMapper mapper;

    public RouteServiceImpl(RouteDao routeDao, RouteMapper mapper) {
        this.routeDao = routeDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createRoute(Route route) {
        if (Objects.isNull(route) ) {
            LOGGER.warn("route is not valid");
            throw new InvalidEntityCreation("route is not valid");
        }

        return routeDao.save(mapper.mapRouteToRouteEntity(route));
    }

    public List<Route> findAllRoutes() {
        List<RouteEntity> result = routeDao.findAll();

        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapRouteEntityToRoute)
                .collect(Collectors.toList());
    }
}

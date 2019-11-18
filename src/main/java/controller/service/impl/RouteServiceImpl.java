package controller.service.impl;

import controller.service.AbstractGenericService;
import controller.service.RouteService;
import domain.Route;
import model.dao.RouteDAO;

import java.util.List;

public class RouteServiceImpl extends AbstractGenericService<Route> implements RouteService {

    private RouteDAO routeDAO;

    public RouteServiceImpl(RouteDAO routeDAO) {
        super(routeDAO);
        this.routeDAO = routeDAO;
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
        return routeDAO.searchByCriteria(departure, arrival);
    }
}

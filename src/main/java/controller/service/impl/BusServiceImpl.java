package controller.service.impl;

import controller.service.AbstractGenericService;
import controller.service.BusService;
import domain.Bus;
import model.dao.BusDAO;

import java.util.List;

public class BusServiceImpl extends AbstractGenericService<Bus> implements BusService {

    private BusDAO busDAO;

    public BusServiceImpl(BusDAO busDAO) {
        super(busDAO);
        this.busDAO = busDAO;
    }

    @Override
    public List<Bus> getFreeBuses() {
        LOGGER.info("Getting free buses");
        return busDAO.getFreeBuses();
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
        return busDAO.getBusesByIdRoute(idRoute);
    }
}

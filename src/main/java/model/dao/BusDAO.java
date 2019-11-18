package model.dao;

import domain.Bus;

import java.util.List;

public interface BusDAO extends GenericDAO<Bus, Integer> {

    List<Bus> getFreeBuses();

    Integer countBusesOnRouteById(Integer idRoute);

    void cancelBusFromRoute(Integer idBus);

    void appointBusToRoute(Integer idRoute, Integer idBus);

    List<Bus> getBusesByIdRoute(Integer idRoute);
}

package controller.service;

import domain.Bus;

import java.util.List;

public interface BusService extends GenericService<Bus, Integer> {

    List<Bus> findFreeBuses();

    Integer countBusesOnRouteById(Integer idRoute);

    void cancelBusFromRoute(Integer idBus);

    void appointBusToRoute(Integer idRoute, Integer idBus);

    List<Bus> findBusesByIdRoute(Integer idRoute);
}

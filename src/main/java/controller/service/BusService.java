package controller.service;

import controller.exception.ServiceLayerException;
import domain.Bus;

import java.sql.Connection;
import java.util.List;

public interface BusService extends GenericService<Bus, Integer>{

    List<Bus> getFreeBuses();

    Integer countBusesOnRouteById(Integer idRoute);

    void cancelBusFromRoute(Integer idBus);

    void appointBusToRoute(Integer idRoute, Integer idBus);

    List<Bus> getBusesByIdRoute(Integer idRoute);
}

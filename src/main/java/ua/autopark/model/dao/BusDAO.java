package ua.autopark.model.dao;

import ua.autopark.model.domain.Bus;

import java.util.List;

public interface BusDAO extends GenericDAO<Bus> {

    List<Bus> getFreeBuses();

    Integer countBusesOnRouteById(int idRoute);

    void cancelBusFromRoute(int idBus);

    void appointBusToRoute(int idRoute, int idBus);

    List<Bus> getBusesByIdRoute(int idRoute);
}

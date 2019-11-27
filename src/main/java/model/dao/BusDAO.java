package model.dao;

import model.entity.BusEntity;

import java.util.List;

public interface BusDAO extends GenericDAO<BusEntity, Integer> {

    List<BusEntity> findFreeBuses();

    Integer countBusesOnRouteById(Integer idRoute);

    void cancelBusFromRoute(Integer idBus);

    void appointBusToRoute(Integer idRoute, Integer idBus);

    List<BusEntity> findBusesByIdRoute(Integer idRoute);
}

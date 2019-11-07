package project.model.dao;

import project.model.entity.RouteEntity;

import java.util.List;

public interface RouteDao extends CrudRepository<Integer, RouteEntity> {

    List<RouteEntity> findByArrival(String arrival);

    List<RouteEntity> findByDeparture(String departure);

}

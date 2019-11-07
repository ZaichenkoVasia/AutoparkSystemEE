package project.model.dao;

import project.model.entity.RouteEntity;

import java.util.List;

public interface RouteDao extends CrudDao<Integer, RouteEntity> {

    List<RouteEntity> findByArrival(String arrival);

    List<RouteEntity> findByDeparture(String departure);

}
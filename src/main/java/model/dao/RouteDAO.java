package model.dao;

import model.entity.RouteEntity;

import java.util.List;

public interface RouteDAO extends GenericDAO<RouteEntity, Integer> {

    void setStatusEmpty(Integer idRoute);

    void setStatusWork(Integer idRoute);

    void cancelAll(Integer idRoute);

    List<RouteEntity> searchByCriteria(String departure, String arrival);
}

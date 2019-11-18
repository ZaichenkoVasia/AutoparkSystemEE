package model.dao;

import domain.Route;
import model.exception.DAOException;

import java.sql.Connection;
import java.util.List;

public interface RouteDAO extends GenericDAO<Route, Integer> {

    void setStatusEmpty(Integer idRoute);

    void setStatusWork(Integer idRoute);

    void cancelAll(Integer idRoute);

    List<Route> searchByCriteria(String departure, String arrival);
}

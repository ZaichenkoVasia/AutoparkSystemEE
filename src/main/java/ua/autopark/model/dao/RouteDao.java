package ua.autopark.model.dao;

import ua.autopark.model.domain.Route;

import java.util.List;

public interface RouteDAO extends GenericDAO<Route> {

    List<Route> searchByCriteria(String departure, String arrival);
}

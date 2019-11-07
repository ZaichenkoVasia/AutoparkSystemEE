package project.model.service;

import project.model.domain.Route;

import java.util.List;

public interface RouteService {
    boolean createRoute(Route route);

    List<Route> findAll(int currentPage, int recordsPerPage);

    int getNumberOfRows();
}

package controller.service;

import controller.exception.ServiceLayerException;
import domain.Route;

import java.sql.Connection;
import java.util.List;

public interface RouteService extends GenericService<Route, Integer>{

    void setStatusEmpty(Integer idRoute);

    void setStatusWork(Integer idRoute) ;

    void cancelAll(Integer idRoute);

    List<Route> searchByCriteria(String departure, String arrival);
}

package controller.service.mapper;

import domain.Route;
import model.entity.RouteEntity;

public class RouteMapper {
    public RouteEntity mapRouteToRouteEntity(Route route) {
        return RouteEntity.builder()
                .withId(route.getId())
                .withNumber(route.getNumber())
                .withTitle(route.getTitle())
                .withDistance(route.getDistance())
                .withStatus(route.getStatus())
                .withDeparture(route.getDeparture())
                .withArrival(route.getArrival())
                .build();
    }

    public Route mapRouteEntityToRoute(RouteEntity routeEntity) {
        return Route.builder()
                .withId(routeEntity.getId())
                .withNumber(routeEntity.getNumber())
                .withTitle(routeEntity.getTitle())
                .withDistance(routeEntity.getDistance())
                .withStatus(routeEntity.getStatus())
                .withDeparture(routeEntity.getDeparture())
                .withArrival(routeEntity.getArrival())
                .build();
    }
}

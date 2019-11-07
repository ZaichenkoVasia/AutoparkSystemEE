package project.model.service.mapper;

import project.model.domain.Route;
import project.model.entity.RouteEntity;

public class RouteMapper {
    public RouteEntity mapRouteToRouteEntity(Route domain) {
        return RouteEntity.builder()
                .withId(domain.getId())
                .withArrival(domain.getArrival())
                .withDeparture(domain.getDeparture())
                .withDistance(domain.getDistance())
                .build();
    }

    public Route mapRouteEntityToRoute(RouteEntity entity) {
        return Route.builder()
                .withId(entity.getId())
                .withArrival(entity.getArrival())
                .withDeparture(entity.getDeparture())
                .withDistance(entity.getDistance())
                .build();
    }
}

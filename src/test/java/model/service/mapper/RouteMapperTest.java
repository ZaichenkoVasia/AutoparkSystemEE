package model.service.mapper;

import model.domain.Route;
import model.entity.RouteEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class RouteMapperTest {
    private static final RouteEntity ROUTE_ENTITY = getRouteEntity();

    private static final Route ROUTE = getRoute();

    private RouteMapper routeMapper = new RouteMapper();

    @Test
    public void shouldMapRouteEntityToRoute() {
        Route actual = routeMapper.mapRouteEntityToRoute(ROUTE_ENTITY);

        assertThat(actual.getId(), is(ROUTE.getId()));
        assertThat(actual.getNumber(), is(ROUTE.getNumber()));
        assertThat(actual.getTitle(), is(ROUTE.getTitle()));
        assertThat(actual.getDeparture(), is(ROUTE.getDeparture()));
        assertThat(actual.getDistance(), is(ROUTE.getDistance()));
        assertThat(actual.getStatus(), is(ROUTE.getStatus()));
        assertThat(actual.getArrival(), is(ROUTE.getArrival()));
    }

    @Test
    public void shouldMapRouteToRouteEntity() {
        RouteEntity actual = routeMapper.mapRouteToRouteEntity(ROUTE);

        assertThat(actual.getId(), is(ROUTE_ENTITY.getId()));
        assertThat(actual.getNumber(), is(ROUTE_ENTITY.getNumber()));
        assertThat(actual.getTitle(), is(ROUTE_ENTITY.getTitle()));
        assertThat(actual.getDeparture(), is(ROUTE_ENTITY.getDeparture()));
        assertThat(actual.getDistance(), is(ROUTE_ENTITY.getDistance()));
        assertThat(actual.getStatus(), is(ROUTE_ENTITY.getStatus()));
        assertThat(actual.getArrival(), is(ROUTE_ENTITY.getArrival()));
    }

    private static RouteEntity getRouteEntity() {
        return RouteEntity.builder()
                .withId(1)
                .withNumber("route_number")
                .withTitle("title")
                .withDistance(100)
                .withStatus("status")
                .withDeparture("departure")
                .withArrival("arrival")
                .build();
    }

    private static Route getRoute() {
        return Route.builder()
                .withId(1)
                .withNumber("route_number")
                .withTitle("title")
                .withDistance(100)
                .withStatus("status")
                .withDeparture("departure")
                .withArrival("arrival")
                .build();
    }
}
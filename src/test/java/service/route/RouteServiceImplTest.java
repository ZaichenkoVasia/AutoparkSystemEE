package service.route;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.model.dao.RouteDao;
import project.model.domain.Route;
import project.model.entity.RouteEntity;
import project.model.exception.InvalidEntityCreation;
import project.model.service.impl.RouteServiceImpl;
import project.model.service.mapper.RouteMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceImplTest {
    private static final Route route = Route.builder().withId(1).build();
    private static final List<RouteEntity> entities = Arrays.asList(
            RouteEntity.builder().withId(1).build(),
            RouteEntity.builder().withId(2).build());
    private static final List<Route> routes = Arrays.asList(route,route);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private RouteDao routeDao;

    @Mock
    private RouteMapper mapper;

    @InjectMocks
    private RouteServiceImpl service;

    @After
    public void resetMock() {
        reset(routeDao, mapper);
    }

    @Test
    public void shouldCreateRoute() {
        when(mapper.mapRouteToRouteEntity(any(Route.class))).thenReturn(entities.get(1));
        when(routeDao.save(any(RouteEntity.class))).thenReturn(true);

        assertTrue(service.createRoute(route));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullRoute() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("route is not valid");

        service.createRoute(null);
    }

    @Test
    public void shouldShowAllRoutes() {
        when(routeDao.findAll()).thenReturn(entities);
        when(mapper.mapRouteEntityToRoute(any(RouteEntity.class))).thenReturn(route);

        List<Route> actual = service.findAllRoutes();

        assertEquals(routes, actual);
    }

    @Test
    public void shouldReturnEmptyList() {
        when(routeDao.findAll()).thenReturn(Collections.emptyList());

        List<Route> actual = service.findAllRoutes();

        assertEquals(Collections.emptyList(), actual);
    }
}
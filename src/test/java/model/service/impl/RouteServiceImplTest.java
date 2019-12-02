package model.service.impl;

import model.dao.RouteDAO;
import model.domain.Route;
import model.entity.RouteEntity;
import model.exception.InvalidDataRuntimeException;
import model.service.mapper.RouteMapper;
import model.service.validator.impl.RouteValidator;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RouteServiceImplTest {

    private static final RouteEntity ENTITY = getRouteEntity();

    private static final Route DOMAIN = getRoute();

    private static final List<RouteEntity> ENTITIES = Collections.singletonList(ENTITY);

    private static final List<Route> DOMAINS = Collections.singletonList(DOMAIN);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private RouteDAO repository;

    @Mock
    private RouteMapper mapper;

    @Mock
    private RouteValidator validator;

    @InjectMocks
    private RouteServiceImpl service;

    @After
    public void resetMock() {
        reset(repository, mapper, validator);
    }

    @Test
    public void shouldChangeRouteStatusToNewById() {
        service.setStatusEmpty(DOMAIN.getId());
        verify(repository).setStatusEmpty(anyInt());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdRouteInStatusNew() {
        exception.expect(InvalidDataRuntimeException.class);
        service.setStatusEmpty(null);
    }

    @Test
    public void shouldChangeRouteStatusToWorkById() {
        service.setStatusWork(DOMAIN.getId());
        verify(repository).setStatusWork(anyInt());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdRouteInStatusWork() {
        exception.expect(InvalidDataRuntimeException.class);
        service.setStatusWork(null);
    }

    @Test
    public void shouldCancelAllByRouteId() {
        service.cancelAll(DOMAIN.getId());
        verify(repository).cancelAll(anyInt());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullInCancelAllByRouteId() {
        exception.expect(InvalidDataRuntimeException.class);
        service.cancelAll(null);
    }

    @Test
    public void shouldRoutesSearchByCriteria() {
        when(repository.findByCriteria(anyString(), anyString())).thenReturn(ENTITIES);
        when(mapper.mapRouteEntityToRoute(any(RouteEntity.class))).thenReturn(DOMAIN);
        List<Route> actual = service.searchByCriteria("departure", "arrival");
        assertThat(DOMAINS, equalTo(actual));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdRouteInSearchByCriteria() {
        exception.expect(InvalidDataRuntimeException.class);
        service.searchByCriteria("departure", null);
    }

    @Test
    public void shouldInsertRoute() {
        when(mapper.mapRouteToRouteEntity(any(Route.class))).thenReturn(ENTITY);
        when(repository.insertElement(any(RouteEntity.class))).thenReturn(ENTITY.getId());
        doNothing().when(validator).validate(any(Route.class));
        Integer actual = service.insertElement(DOMAIN);
        assertThat(actual, equalTo(DOMAIN.getId()));
        verify(validator).validate(any(Route.class));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenRouteNullInInsert() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldReturnRouteById() {
        when(mapper.mapRouteEntityToRoute(any(RouteEntity.class))).thenReturn(DOMAIN);
        when(repository.findElementById(any(Integer.class))).thenReturn(ENTITY);
        Route actual = service.findElementById(DOMAIN.getId());
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenRouteNullInFindById() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldDeleteRouteById() {
        service.deleteElement(DOMAIN.getId());
        verify(repository).deleteElement(DOMAIN.getId());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInDelete() {
        exception.expect(InvalidDataRuntimeException.class);
        service.deleteElement(null);
    }

    @Test
    public void shouldUpdateRouteById() {
        when(mapper.mapRouteToRouteEntity(any(Route.class))).thenReturn(ENTITY);
        service.updateElement(DOMAIN);
        verify(repository).updateElement(ENTITY);
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInUpdate() {
        exception.expect(InvalidDataRuntimeException.class);
        service.updateElement(null);
    }

    @Test
    public void shouldReturnPagenationDriver() {
        when(repository.findPaginatedList(anyInt(), anyInt())).thenReturn(ENTITIES);
        when(mapper.mapRouteEntityToRoute(any(RouteEntity.class))).thenReturn(DOMAIN);
        List<Route> actual = service.findPaginatedList(1, 1);
        assertThat(DOMAINS, equalTo(actual));
    }

    private static RouteEntity getRouteEntity() {
        return RouteEntity.builder()
                .withId(1)
                .withArrival("arrival")
                .withDeparture("departure")
                .withDistance(1000)
                .withStatus("free")
                .withNumber("number")
                .withTitle("title")
                .build();
    }

    private static Route getRoute() {
        return Route.builder()
                .withId(1)
                .withArrival("arrival")
                .withDeparture("departure")
                .withDistance(1000)
                .withStatus("free")
                .withNumber("number")
                .withTitle("title")
                .build();
    }
}

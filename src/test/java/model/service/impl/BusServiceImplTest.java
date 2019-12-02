package model.service.impl;

import model.dao.BusDAO;
import model.domain.Bus;
import model.domain.Route;
import model.domain.Schedule;
import model.entity.BusEntity;
import model.entity.RouteEntity;
import model.entity.ScheduleEntity;
import model.exception.InvalidDataRuntimeException;
import model.service.mapper.BusMapper;
import model.service.validator.impl.BusValidator;
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
public class BusServiceImplTest {

    private static final BusEntity ENTITY = getBusEntity();

    private static final Bus DOMAIN = getBus();

    private static final List<BusEntity> ENTITIES = Collections.singletonList(ENTITY);

    private static final List<Bus> DOMAINS = Collections.singletonList(DOMAIN);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private BusDAO repository;

    @Mock
    private BusMapper mapper;

    @Mock
    private BusValidator validator;

    @InjectMocks
    private BusServiceImpl service;

    @After
    public void resetMock() {
        reset(repository, mapper, validator);
    }

    @Test
    public void shouldReturnFreeBuses() {
        when(repository.findFreeBuses()).thenReturn(ENTITIES);
        when(mapper.mapBusEntityToBus(any(BusEntity.class))).thenReturn(DOMAIN);
        List<Bus> actual = service.findFreeBuses();
        assertThat(DOMAINS, equalTo(actual));
    }

    @Test
    public void shouldReturnCountBusesOnRouteById() {
        when(repository.countBusesOnRouteById(anyInt())).thenReturn(1);
        Integer actual = service.countBusesOnRouteById(DOMAIN.getId());
        assertThat(DOMAINS.size(), equalTo(actual));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdRouteInCountBuses() {
        exception.expect(InvalidDataRuntimeException.class);
        service.countBusesOnRouteById(null);
    }

    @Test
    public void shouldCancelBusFromRouteByBusId() {
        service.cancelBusFromRoute(DOMAIN.getId());
        verify(repository).cancelBusFromRoute(anyInt());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdBusInCancelBus() {
        exception.expect(InvalidDataRuntimeException.class);
        service.cancelBusFromRoute(null);
    }

    @Test
    public void shouldAppointBusToRouteByBusId() {
        service.appointBusToRoute(DOMAIN.getId(), DOMAIN.getRoute().getId());
        verify(repository).appointBusToRoute(anyInt(), anyInt());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdBusInAppointBus() {
        exception.expect(InvalidDataRuntimeException.class);
        service.appointBusToRoute(null, null);
    }

    @Test
    public void shouldReturnBusesByIdRoute() {
        when(repository.findBusesByIdRoute(anyInt())).thenReturn(ENTITIES);
        when(mapper.mapBusEntityToBus(any(BusEntity.class))).thenReturn(DOMAIN);
        List<Bus> actual = service.findBusesByIdRoute(DOMAIN.getId());
        assertThat(DOMAINS, equalTo(actual));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenRouteNullInFindBuses() {
        exception.expect(InvalidDataRuntimeException.class);
        service.findBusesByIdRoute(null);
    }

    @Test
    public void shouldInsertBus() {
        when(mapper.mapBusToBusEntity(any(Bus.class))).thenReturn(ENTITY);
        when(repository.insertElement(any(BusEntity.class))).thenReturn(ENTITY.getId());
        doNothing().when(validator).validate(any(Bus.class));
        Integer actual = service.insertElement(DOMAIN);
        assertThat(actual, equalTo(DOMAIN.getId()));
        verify(validator).validate(any(Bus.class));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenBusNullInInsert() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldReturnBusById() {
        when(mapper.mapBusEntityToBus(any(BusEntity.class))).thenReturn(DOMAIN);
        when(repository.findElementById(any(Integer.class))).thenReturn(ENTITY);
        Bus actual = service.findElementById(DOMAIN.getId());
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenBusNullInFindById() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldDeleteBusById() {
        service.deleteElement(DOMAIN.getId());
        verify(repository).deleteElement(DOMAIN.getId());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInDelete() {
        exception.expect(InvalidDataRuntimeException.class);
        service.deleteElement(null);
    }

    @Test
    public void shouldUpdateBusById() {
        when(mapper.mapBusToBusEntity(any(Bus.class))).thenReturn(ENTITY);
        service.updateElement(DOMAIN);
        verify(repository).updateElement(ENTITY);
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInUpdate() {
        exception.expect(InvalidDataRuntimeException.class);
        service.updateElement(null);
    }

    @Test
    public void shouldReturnPagenationBus() {
        when(repository.findPaginatedList(anyInt(), anyInt())).thenReturn(ENTITIES);
        when(mapper.mapBusEntityToBus(any(BusEntity.class))).thenReturn(DOMAIN);
        List<Bus> actual = service.findPaginatedList(1, 1);
        assertThat(DOMAINS, equalTo(actual));
    }

    private static BusEntity getBusEntity() {
        return BusEntity.builder()
                .withId(1)
                .withConsumption(100)
                .withMileage(10)
                .withModel("model")
                .withPlate("plate")
                .withSeats(20)
                .withStatus("free")
                .withSchedule(ScheduleEntity.builder()
                        .withId(1)
                        .build())
                .withRoute(RouteEntity.builder()
                        .withId(1)
                        .build())
                .build();
    }

    private static Bus getBus() {
        return Bus.builder()
                .withId(1)
                .withConsumption(100)
                .withMileage(10)
                .withModel("model")
                .withPlate("plate")
                .withSeats(20)
                .withStatus("free")
                .withSchedule(Schedule.builder()
                        .withId(1)
                        .build())
                .withRoute(Route.builder()
                        .withId(1)
                        .build())
                .build();
    }
}

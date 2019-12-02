package model.service.impl;

import model.dao.DriverDAO;
import model.domain.Bus;
import model.domain.Driver;
import model.domain.User;
import model.entity.BusEntity;
import model.entity.DriverEntity;
import model.entity.UserEntity;
import model.exception.InvalidDataRuntimeException;
import model.service.mapper.DriverMapper;
import model.service.validator.impl.DriverValidator;
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
public class DriverServiceImplTest {

    private static final DriverEntity ENTITY = getDriverEntity();

    private static final Driver DOMAIN = getDriver();

    private static final List<DriverEntity> ENTITIES = Collections.singletonList(ENTITY);

    private static final List<Driver> DOMAINS = Collections.singletonList(DOMAIN);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private DriverDAO repository;

    @Mock
    private DriverMapper mapper;

    @Mock
    private DriverValidator validator;

    @InjectMocks
    private DriverServiceImpl service;

    @After
    public void resetMock() {
        reset(repository, mapper, validator);
    }

    @Test
    public void shouldReturnDriverByUserId() {
        when(mapper.mapDriverEntityToDriver(any(DriverEntity.class))).thenReturn(DOMAIN);
        when(repository.findDriverByUserId(anyInt())).thenReturn(ENTITY);
        Driver actual = service.findDriverByUserId(DOMAIN.getUser().getId());
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdUser() {
        exception.expect(InvalidDataRuntimeException.class);
        service.findDriverByUserId(null);
    }

    @Test
    public void shouldReturnDriverByIdBus() {
        when(mapper.mapDriverEntityToDriver(any(DriverEntity.class))).thenReturn(DOMAIN);
        when(repository.findDriverByBusId(anyInt())).thenReturn(ENTITY);
        Driver actual = service.findDriverByBusId(DOMAIN.getBus().getId());
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdBus() {
        exception.expect(InvalidDataRuntimeException.class);
        service.findDriverByBusId(null);
    }

    @Test
    public void shouldChangeDriverStatusToNewById() {
        service.setStatusNew(DOMAIN.getId());
        verify(repository).setStatusNew(anyInt());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdDriverInStatusNew() {
        exception.expect(InvalidDataRuntimeException.class);
        service.setStatusNew(null);
    }

    @Test
    public void shouldChangeDriverStatusToWorkById() {
        service.setStatusWork(DOMAIN.getId());
        verify(repository).setStatusWork(anyInt());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdDriverInStatusWork() {
        exception.expect(InvalidDataRuntimeException.class);
        service.setStatusWork(null);
    }

    @Test
    public void shouldCancelDriverFromBuByBusId() {
        service.cancelDriverFromBus(DOMAIN.getId());
        verify(repository).cancelDriverFromBus(anyInt());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullIdBusInCancelDriver() {
        exception.expect(InvalidDataRuntimeException.class);
        service.cancelDriverFromBus(null);
    }

    @Test
    public void shouldUpdateBusInfoForDriverIds() {
        service.updateBusInfoForDriver(DOMAIN.getId(), DOMAIN.getBus().getId());
        verify(repository).updateBusInfoForDriver(anyInt(), anyInt());
    }

    @Test
    public void shouldReturnFreeDrivers() {
        when(repository.getFreeDrivers()).thenReturn(ENTITIES);
        when(mapper.mapDriverEntityToDriver(any(DriverEntity.class))).thenReturn(DOMAIN);
        List<Driver> actual = service.findFreeDrivers();
        assertThat(DOMAINS, equalTo(actual));
    }

    @Test
    public void shouldInsertDriver() {
        when(mapper.mapDriverToDriverEntity(any(Driver.class))).thenReturn(ENTITY);
        when(repository.insertElement(any(DriverEntity.class))).thenReturn(ENTITY.getId());
        doNothing().when(validator).validate(any(Driver.class));
        Integer actual = service.insertElement(DOMAIN);
        assertThat(actual, equalTo(DOMAIN.getId()));
        verify(validator).validate(any(Driver.class));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenDriverNullInInsert() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldReturnDriverById() {
        when(mapper.mapDriverEntityToDriver(any(DriverEntity.class))).thenReturn(DOMAIN);
        when(repository.findElementById(any(Integer.class))).thenReturn(ENTITY);
        Driver actual = service.findElementById(DOMAIN.getId());
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenDriverNullInFindById() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldDeleteDriverById() {
        service.deleteElement(DOMAIN.getId());
        verify(repository).deleteElement(DOMAIN.getId());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInDelete() {
        exception.expect(InvalidDataRuntimeException.class);
        service.deleteElement(null);
    }

    @Test
    public void shouldUpdateDriverById() {
        when(mapper.mapDriverToDriverEntity(any(Driver.class))).thenReturn(ENTITY);
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
        when(mapper.mapDriverEntityToDriver(any(DriverEntity.class))).thenReturn(DOMAIN);
        List<Driver> actual = service.findPaginatedList(1, 1);
        assertThat(DOMAINS, equalTo(actual));
    }

    private static DriverEntity getDriverEntity() {
        return DriverEntity.builder()
                .withId(1)
                .withName("name")
                .withSurname("surname")
                .withSalary(1000)
                .withStatus("free")
                .withUser(UserEntity.builder()
                        .withId(1)
                        .withLogin("email@gmail.com ")
                        .withPassword("21232f297a57a5a743894ae4a801fc3")
                        .withRole("driver")
                        .build())
                .withBus(BusEntity.builder()
                        .withId(1)
                        .build())
                .build();
    }

    private static Driver getDriver() {
        return Driver.builder()
                .withId(1)
                .withName("name")
                .withSurname("surname")
                .withSalary(1000)
                .withStatus("free")
                .withUser(User.builder()
                        .withId(1)
                        .withLogin("email@gmail.com ")
                        .withPassword("21232f297a57a5a743894ae4a801fc3")
                        .withRole("driver")
                        .build())
                .withBus(Bus.builder()
                        .withId(1)
                        .build())
                .build();
    }
}

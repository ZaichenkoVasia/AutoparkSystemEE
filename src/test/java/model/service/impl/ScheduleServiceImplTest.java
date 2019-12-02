package model.service.impl;

import model.dao.ScheduleDAO;
import model.domain.Schedule;
import model.entity.ScheduleEntity;
import model.exception.InvalidDataRuntimeException;
import model.service.mapper.ScheduleMapper;
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
public class ScheduleServiceImplTest {

    private static final ScheduleEntity ENTITY = getScheduleEntity();

    private static final Schedule DOMAIN = getSchedule();

    private static final List<ScheduleEntity> ENTITIES = Collections.singletonList(ENTITY);

    private static final List<Schedule> DOMAINS = Collections.singletonList(DOMAIN);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private ScheduleDAO repository;

    @Mock
    private ScheduleMapper mapper;

    @InjectMocks
    private ScheduleServiceImpl service;

    @After
    public void resetMock() {
        reset(repository, mapper);
    }

    @Test
    public void shouldInsertSchedule() {
        when(mapper.mapScheduleToScheduleEntity(any(Schedule.class))).thenReturn(ENTITY);
        when(repository.insertElement(any(ScheduleEntity.class))).thenReturn(ENTITY.getId());
        Integer actual = service.insertElement(DOMAIN);
        assertThat(actual, equalTo(DOMAIN.getId()));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenScheduleNullInInsert() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldReturnScheduleById() {
        when(mapper.mapScheduleEntityToSchedule(any(ScheduleEntity.class))).thenReturn(DOMAIN);
        when(repository.findElementById(any(Integer.class))).thenReturn(ENTITY);
        Schedule actual = service.findElementById(DOMAIN.getId());
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenScheduleNullInFindById() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldDeleteScheduleById() {
        service.deleteElement(DOMAIN.getId());
        verify(repository).deleteElement(DOMAIN.getId());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInDelete() {
        exception.expect(InvalidDataRuntimeException.class);
        service.deleteElement(null);
    }

    @Test
    public void shouldUpdateScheduleById() {
        when(mapper.mapScheduleToScheduleEntity(any(Schedule.class))).thenReturn(ENTITY);
        service.updateElement(DOMAIN);
        verify(repository).updateElement(ENTITY);
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInUpdate() {
        exception.expect(InvalidDataRuntimeException.class);
        service.updateElement(null);
    }

    @Test
    public void shouldReturnPagenationUser() {
        when(repository.findPaginatedList(anyInt(), anyInt())).thenReturn(ENTITIES);
        when(mapper.mapScheduleEntityToSchedule(any(ScheduleEntity.class))).thenReturn(DOMAIN);
        List<Schedule> actual = service.findPaginatedList(1, 1);
        assertThat(DOMAINS, equalTo(actual));
    }

    private static ScheduleEntity getScheduleEntity() {
        return ScheduleEntity.builder()
                .withId(1)
                .withArrival("arrival")
                .withDeparture("departure")
                .build();
    }

    private static Schedule getSchedule() {
        return Schedule.builder()
                .withId(1)
                .withArrival("arrival")
                .withDeparture("departure")
                .build();
    }
}

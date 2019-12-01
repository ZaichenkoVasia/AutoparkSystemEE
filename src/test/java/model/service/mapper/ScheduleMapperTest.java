package model.service.mapper;

import model.domain.Schedule;
import model.entity.ScheduleEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScheduleMapperTest {
    private static final ScheduleEntity SCHEDULE_ENTITY = getScheduleEntity();

    private static final Schedule SCHEDULE = getSchedule();

    private ScheduleMapper scheduleMapper = new ScheduleMapper();

    @Test
    public void shouldMapScheduleEntityToSchedule() {
        Schedule actual = scheduleMapper.mapScheduleEntityToSchedule(SCHEDULE_ENTITY);

        assertThat(actual.getId(), is(SCHEDULE.getId()));
        assertThat(actual.getId(), is(SCHEDULE.getId()));
        assertThat(actual.getArrival(), is(SCHEDULE.getArrival()));
        assertThat(actual.getDeparture(), is(SCHEDULE.getDeparture()));
    }

    @Test
    public void shouldMapScheduleToScheduleEntity() {
        ScheduleEntity actual = scheduleMapper.mapScheduleToScheduleEntity(SCHEDULE);

        assertThat(actual.getId(), is(SCHEDULE_ENTITY.getId()));
        assertThat(actual.getId(), is(SCHEDULE_ENTITY.getId()));
        assertThat(actual.getArrival(), is(SCHEDULE_ENTITY.getArrival()));
        assertThat(actual.getDeparture(), is(SCHEDULE_ENTITY.getDeparture()));
    }

    private static ScheduleEntity getScheduleEntity() {
        return ScheduleEntity.builder()
                .withId(1)
                .withDeparture("schedule.departure")
                .withArrival("schedule.arrival")
                .build();
    }

    private static Schedule getSchedule() {
        return Schedule.builder()
                .withId(1)
                .withDeparture("schedule.departure")
                .withArrival("schedule.arrival")
                .build();
    }
}
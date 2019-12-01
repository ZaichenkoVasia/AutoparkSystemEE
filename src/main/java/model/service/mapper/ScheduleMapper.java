package model.service.mapper;

import model.domain.Schedule;
import model.entity.ScheduleEntity;

public class ScheduleMapper {
    public ScheduleEntity mapScheduleToScheduleEntity(Schedule schedule) {
        return ScheduleEntity.builder()
                .withId(schedule.getId())
                .withDeparture(schedule.getDeparture())
                .withArrival(schedule.getArrival())
                .build();
    }

    public Schedule mapScheduleEntityToSchedule(ScheduleEntity scheduleEntity) {
        return Schedule.builder()
                .withId(scheduleEntity.getId())
                .withDeparture(scheduleEntity.getDeparture())
                .withArrival(scheduleEntity.getArrival())
                .build();
    }
}

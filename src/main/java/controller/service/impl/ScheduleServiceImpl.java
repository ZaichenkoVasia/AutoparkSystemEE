package controller.service.impl;

import controller.service.AbstractGenericService;
import controller.service.ScheduleService;
import domain.Schedule;
import model.dao.ScheduleDAO;

public class ScheduleServiceImpl extends AbstractGenericService<Schedule> implements ScheduleService {

    public ScheduleServiceImpl(ScheduleDAO scheduleDAO) {
        super(scheduleDAO);
    }
}

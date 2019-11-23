package controller.service.impl;

import controller.service.ScheduleService;
import controller.service.mapper.ScheduleMapper;
import domain.Schedule;
import model.dao.ScheduleDAO;
import model.entity.ScheduleEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleServiceImpl implements ScheduleService {
    private static final Logger LOGGER = Logger.getLogger(DriverServiceImpl.class);

    private ScheduleDAO scheduleDAO;
    private ScheduleMapper mapper;

    public ScheduleServiceImpl(ScheduleDAO scheduleDAO, ScheduleMapper mapper) {
        this.scheduleDAO = scheduleDAO;
        this.mapper = mapper;
    }

    @Override
    public Integer insertElement(Schedule element) {
        LOGGER.info("Inserting element");
        ScheduleEntity scheduleEntity = mapper.mapScheduleToUserEntity(element);
        return scheduleDAO.insertElement(scheduleEntity);
    }

    @Override
    public Schedule getElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        ScheduleEntity scheduleEntity = scheduleDAO.getElementById(id);
        return mapper.mapScheduleEntityToSchedule(scheduleEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        scheduleDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Schedule element) {
        LOGGER.info("Updating element");
        ScheduleEntity scheduleEntity = mapper.mapScheduleToUserEntity(element);
        scheduleDAO.updateElement(scheduleEntity);
    }

    @Override
    public Integer getElementsAmount() {
        LOGGER.info("Getting elements amount");
        return scheduleDAO.getElementsCount();
    }

    @Override
    public List<Schedule> getPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<ScheduleEntity> result = scheduleDAO.getPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapScheduleEntityToSchedule)
                .collect(Collectors.toList());
    }

}

package model.service.impl;

import model.dao.ScheduleDAO;
import model.domain.Schedule;
import model.entity.ScheduleEntity;
import model.exception.InvalidDataRuntimeException;
import model.service.ScheduleService;
import model.service.mapper.ScheduleMapper;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect insertElement value");
            throw new InvalidDataRuntimeException("Incorrect insertElement value");
        }
        ScheduleEntity scheduleEntity = mapper.mapScheduleToScheduleEntity(element);
        return scheduleDAO.insertElement(scheduleEntity);
    }

    @Override
    public Schedule findElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect findElementById value");
            throw new InvalidDataRuntimeException("Incorrect findElementById value");
        }
        ScheduleEntity scheduleEntity = scheduleDAO.findElementById(id);
        return mapper.mapScheduleEntityToSchedule(scheduleEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect deleteElement value");
            throw new InvalidDataRuntimeException("Incorrect deleteElement value");
        }
        scheduleDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Schedule element) {
        LOGGER.info("Updating element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect updateElement value");
            throw new InvalidDataRuntimeException("Incorrect updateElement value");
        }
        ScheduleEntity scheduleEntity = mapper.mapScheduleToScheduleEntity(element);
        scheduleDAO.updateElement(scheduleEntity);
    }

    @Override
    public Integer findElementsAmount() {
        LOGGER.info("Getting elements amount");
        return scheduleDAO.count();
    }

    @Override
    public List<Schedule> findPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<ScheduleEntity> result = scheduleDAO.findPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapScheduleEntityToSchedule)
                .collect(Collectors.toList());
    }

}

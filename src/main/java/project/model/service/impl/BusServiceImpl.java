package project.model.service.impl;

import org.apache.log4j.Logger;
import project.model.dao.BusDao;
import project.model.domain.Bus;
import project.model.entity.BusEntity;
import project.model.exception.InvalidCreationRuntimeException;
import project.model.service.BusService;
import project.model.service.mapper.BusMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BusServiceImpl implements BusService {
    private static final Logger LOGGER = Logger.getLogger(BusServiceImpl.class);

    private final BusDao busDao;
    private final BusMapper mapper;

    public BusServiceImpl(BusDao busDao, BusMapper mapper) {
        this.busDao = busDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createBus(Bus bus) {
        if (Objects.isNull(bus)) {
            LOGGER.warn("bus is not valid");
            throw new InvalidCreationRuntimeException("bus is not valid");
        }

        return busDao.save(mapper.mapBusToBusEntity(bus));
    }

    @Override
    public List<Bus> findAll(int currentPage, int recordsPerPage) {
        List<BusEntity> result = busDao.findAll(currentPage, recordsPerPage);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapBusEntityToBus)
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfRows() {
        return busDao.getNumberOfRows();
    }
}

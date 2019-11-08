package project.model.service.impl;

import org.apache.log4j.Logger;
import project.model.dao.AssignmentDao;
import project.model.domain.Assignment;
import project.model.entity.AssignmentEntity;
import project.model.entity.enums.Status;
import project.model.exception.InvalidCreationRuntimeException;
import project.model.service.AssignmentService;
import project.model.service.mapper.AssignmentMapper;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AssignmentServiceImpl implements AssignmentService {
    private static final Logger LOGGER = Logger.getLogger(AssignmentServiceImpl.class);

    private final AssignmentDao assignmentDao;
    private final AssignmentMapper mapper;

    public AssignmentServiceImpl(AssignmentDao assignmentDao, AssignmentMapper mapper) {
        this.assignmentDao = assignmentDao;
        this.mapper = mapper;
    }

    @Override
    public boolean createAssignment(Assignment assignment) {
        if (Objects.isNull(assignment)) {
            LOGGER.warn("assignment is not valid");
            throw new InvalidCreationRuntimeException("assignment is not valid");
        }

        return assignmentDao.save(mapper.mapAssignmentToAssignmentEntity(assignment));
    }

    @Override
    public List<Assignment> findAssignmentsByStatus(Status status) {
        validateParam(status);

        return assignmentDao.findByStatus(status.toString()).stream()
                .map(mapper::mapAssignmentEntityToAssignment)
                .collect(Collectors.toList());
    }

    @Override
    public List<Assignment> findAssignmentsByUser(Integer userId) {
        validateParam(userId);

        return assignmentDao.findByUser(userId).stream()
                .map(mapper::mapAssignmentEntityToAssignment)
                .collect(Collectors.toList());
    }

    private <T> void validateParam(T param) {
        if (Objects.isNull(param)) {
            LOGGER.warn("Parameter is not valid");
            throw new IllegalArgumentException("Parameter is not valid");
        }
    }

    @Override
    public List<Assignment> findAll(int currentPage, int recordsPerPage) {
        List<AssignmentEntity> result = assignmentDao.findAll(currentPage,recordsPerPage);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapAssignmentEntityToAssignment)
                .collect(Collectors.toList());
    }

    @Override
    public int getNumberOfRows() {
        return assignmentDao.getNumberOfRows();
    }
}

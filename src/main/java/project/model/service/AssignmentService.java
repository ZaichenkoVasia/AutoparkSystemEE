package project.model.service;


import project.model.domain.Assignment;
import project.model.entity.enums.Status;

import java.util.List;

public interface AssignmentService {

    boolean createAssignment(Assignment assignment);

    List<Assignment> findAssignmentsByStatus(Status status);

    List<Assignment> findAssignmentsByUser(Integer userId);

    List<Assignment> findAll(int currentPage, int recordsPerPage);

    int getNumberOfRows();
}

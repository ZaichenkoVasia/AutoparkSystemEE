package project.model.dao;

import project.model.entity.AssignmentEntity;

import java.util.List;

public interface AssignmentDao extends CrudDao<Integer, AssignmentEntity> {

    List<AssignmentEntity> findByBus(Integer id);

    List<AssignmentEntity> findByUser(Integer id);

    List<AssignmentEntity> findByRoute(Integer id);

    List<AssignmentEntity> findByStatus(String status);

    void updateAssignmentStatus(AssignmentEntity assignment, String status);

}

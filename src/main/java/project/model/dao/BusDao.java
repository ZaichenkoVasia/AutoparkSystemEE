package project.model.dao;

import project.model.entity.BusEntity;

import java.util.List;

public interface BusDao extends CrudRepository<Integer, BusEntity> {

    List<BusEntity> findByStatus(String status);

    void updateBusStatus(BusEntity bus, String status);

}

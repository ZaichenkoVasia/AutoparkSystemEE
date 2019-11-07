package project.model.service;

import project.model.domain.Bus;

import java.util.List;

public interface BusService {
    boolean createBus(Bus bus);

    List<Bus> findAll(int currentPage, int recordsPerPage);

    int getNumberOfRows();
}

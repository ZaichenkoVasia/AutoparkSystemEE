package controller.service.impl;

import controller.service.AdminService;
import controller.service.mapper.AdminMapper;
import domain.Admin;
import model.dao.AdminDAO;
import model.entity.AdminEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = Logger.getLogger(BusStationServiceImpl.class);

    private AdminDAO adminDAO;
    private AdminMapper mapper;

    public AdminServiceImpl(AdminDAO adminDAO, AdminMapper mapper) {
        this.adminDAO = adminDAO;
        this.mapper = mapper;
    }

    @Override
    public Admin getAdminByUserId(Integer idUser) {
        LOGGER.info("Try to get admin info by user id");
        AdminEntity adminEntity = adminDAO.getAdminByUserId(idUser);
        return mapper.mapAdminEntityToAdmin(adminEntity);
    }

    @Override
    public Integer insertElement(Admin element) {
        LOGGER.info("Inserting element");
        AdminEntity adminEntity = mapper.mapAdminToAdminEntity(element);
        return adminDAO.insertElement(adminEntity);
    }

    @Override
    public Admin getElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        AdminEntity adminEntity = adminDAO.getAdminByUserId(id);
        return mapper.mapAdminEntityToAdmin(adminEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        adminDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Admin element) {
        LOGGER.info("Updating element");
        AdminEntity adminEntity = mapper.mapAdminToAdminEntity(element);
        adminDAO.updateElement(adminEntity);
    }

    @Override
    public Integer getElementsAmount() {
        LOGGER.info("Getting elements amount");
        return adminDAO.getElementsCount();
    }

    @Override
    public List<Admin> getPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<AdminEntity> result = adminDAO.getPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapAdminEntityToAdmin)
                .collect(Collectors.toList());
    }
}

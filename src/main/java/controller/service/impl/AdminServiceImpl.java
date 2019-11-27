package controller.service.impl;

import controller.exception.InvalidDataRuntimeException;
import controller.service.AdminService;
import controller.service.mapper.AdminMapper;
import controller.service.validator.impl.AdminValidator;
import domain.Admin;
import model.dao.AdminDAO;
import model.entity.AdminEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {
    private static final Logger LOGGER = Logger.getLogger(BusStationServiceImpl.class);

    private AdminDAO adminDAO;
    private AdminMapper mapper;
    private AdminValidator adminValidator;

    public AdminServiceImpl(AdminDAO adminDAO, AdminMapper mapper, AdminValidator adminValidator) {
        this.adminDAO = adminDAO;
        this.mapper = mapper;
        this.adminValidator = adminValidator;
    }

    @Override
    public Admin findAdminByUserId(Integer idUser) {
        LOGGER.info("Try to get admin info by user id");
        if (Objects.isNull(idUser)) {
            LOGGER.error("Id user is not exist");
            throw new InvalidDataRuntimeException("Id user is not exist");
        }
        AdminEntity adminEntity = adminDAO.findAdminByUserId(idUser);
        return mapper.mapAdminEntityToAdmin(adminEntity);
    }

    @Override
    public Integer insertElement(Admin element) {
        LOGGER.info("Inserting element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect insert value");
            throw new InvalidDataRuntimeException("Incorrect insert value");
        }
        adminValidator.validate(element);
        AdminEntity adminEntity = mapper.mapAdminToAdminEntity(element);
        return adminDAO.insertElement(adminEntity);
    }

    @Override
    public Admin findElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect findElementById value");
            throw new InvalidDataRuntimeException("Incorrect findElementById value");
        }
        AdminEntity adminEntity = adminDAO.findAdminByUserId(id);
        return mapper.mapAdminEntityToAdmin(adminEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect deleteElement value");
            throw new InvalidDataRuntimeException("Incorrect deleteElement value");
        }
        adminDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Admin element) {
        LOGGER.info("Updating element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect updateElement value");
            throw new InvalidDataRuntimeException("Incorrect updateElement value");
        }
        adminValidator.validate(element);
        AdminEntity adminEntity = mapper.mapAdminToAdminEntity(element);
        adminDAO.updateElement(adminEntity);
    }

    @Override
    public Integer findElementsAmount() {
        LOGGER.info("Getting elements amount");
        return adminDAO.count();
    }

    @Override
    public List<Admin> findPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<AdminEntity> result = adminDAO.findPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapAdminEntityToAdmin)
                .collect(Collectors.toList());
    }
}

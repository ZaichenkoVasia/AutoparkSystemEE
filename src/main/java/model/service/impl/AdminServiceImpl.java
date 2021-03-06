package model.service.impl;

import model.dao.AdminDAO;
import model.domain.Admin;
import model.entity.AdminEntity;
import model.exception.InvalidDataRuntimeException;
import model.service.AdminService;
import model.service.mapper.AdminMapper;
import model.service.validator.impl.AdminValidator;
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
            LOGGER.warn("Id user is not exist");
            throw new InvalidDataRuntimeException("Id user is not exist");
        }
        AdminEntity adminEntity = adminDAO.findAdminByUserId(idUser);
        return mapper.mapAdminEntityToAdmin(adminEntity);
    }

    @Override
    public Integer insertElement(Admin element) {
        LOGGER.info("Inserting element");
        if (Objects.isNull(element)) {
            LOGGER.warn("Incorrect insert value");
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
            LOGGER.warn("Incorrect findElementById value");
            throw new InvalidDataRuntimeException("Incorrect findElementById value");
        }
        AdminEntity adminEntity = adminDAO.findAdminByUserId(id);
        return mapper.mapAdminEntityToAdmin(adminEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        if (Objects.isNull(id)) {
            LOGGER.warn("Incorrect deleteElement value");
            throw new InvalidDataRuntimeException("Incorrect deleteElement value");
        }
        adminDAO.deleteElement(id);
    }

    @Override
    public void updateElement(Admin element) {
        LOGGER.info("Updating element");
        if (Objects.isNull(element)) {
            LOGGER.warn("Incorrect updateElement value");
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

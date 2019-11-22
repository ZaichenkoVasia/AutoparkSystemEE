package controller.service.impl;

import controller.service.AbstractGenericService;
import controller.service.AdminService;
import domain.Admin;
import model.dao.AdminDAO;

public class AdminServiceImpl extends AbstractGenericService<Admin> implements AdminService {

    private AdminDAO adminDAO;

    public AdminServiceImpl(AdminDAO adminDAO) {
        super(adminDAO);
        this.adminDAO = adminDAO;
    }

    @Override
    public Admin getAdminByUserId(Integer idUser) {
        LOGGER.info("Try to get admin info by user id");
        return adminDAO.getAdminByUserId(idUser);
    }
}

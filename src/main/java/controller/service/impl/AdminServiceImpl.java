package controller.service.impl;

import controller.constants.LogMessages;
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
        LOGGER.info(LogMessages.GETTING_ADMIN_BY_USER_ID);
        return adminDAO.getAdminByUserId(idUser);
    }
}

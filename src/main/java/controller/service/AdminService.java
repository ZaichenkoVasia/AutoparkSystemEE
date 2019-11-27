package controller.service;

import domain.Admin;

public interface AdminService extends GenericService<Admin, Integer> {

    Admin findAdminByUserId(Integer idUser);
}

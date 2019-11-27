package model.service;

import model.domain.Admin;

public interface AdminService extends GenericService<Admin, Integer> {

    Admin findAdminByUserId(Integer idUser);
}

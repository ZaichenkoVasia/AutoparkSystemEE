package model.dao;

import domain.Admin;

public interface AdminDAO extends GenericDAO<Admin, Integer> {

    Admin getAdminByUserId(Integer idUser);
}

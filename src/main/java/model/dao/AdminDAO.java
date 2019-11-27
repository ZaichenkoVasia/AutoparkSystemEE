package model.dao;

import model.entity.AdminEntity;

public interface AdminDAO extends GenericDAO<AdminEntity, Integer> {

    AdminEntity findAdminByUserId(Integer idUser);
}

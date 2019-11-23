package model.dao;

import model.entity.AdminEntity;

public interface AdminDAO extends GenericDAO<AdminEntity, Integer> {

    AdminEntity getAdminByUserId(Integer idUser);
}

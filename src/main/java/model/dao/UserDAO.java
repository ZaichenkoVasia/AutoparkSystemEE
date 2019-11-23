package model.dao;

import model.entity.UserEntity;

public interface UserDAO extends GenericDAO<UserEntity, Integer> {

    UserEntity findByLogin(UserEntity user);
}

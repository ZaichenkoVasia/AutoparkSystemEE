package project.model.dao;

import project.model.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserDao extends CrudDao<Integer, UserEntity> {
    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByStatus(String status);

    void updateUserStatus(UserEntity user, String status);
}

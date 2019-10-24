package ua.autopark.model.dao;

import ua.autopark.model.entity.User;

import java.util.Optional;

public interface UserDao extends CrudDao<User, Long> {
    Optional<User> findByEmail(String email);

}

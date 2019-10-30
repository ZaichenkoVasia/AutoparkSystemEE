package ua.autopark.model.dao;

import ua.autopark.model.domain.User;

public interface UserDAO extends GenericDAO<User>{

    User findUserByLoginData(String login);
}

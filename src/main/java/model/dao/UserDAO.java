package model.dao;

import domain.User;
import model.exception.DAOException;

public interface UserDAO extends GenericDAO<User, Integer>{

    User findUserByLoginData(User user);
}

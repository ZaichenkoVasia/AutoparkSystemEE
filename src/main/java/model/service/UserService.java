package model.service;

import model.domain.User;

public interface UserService extends GenericService<User, Integer> {

    User findUserByLoginData(User user);

}

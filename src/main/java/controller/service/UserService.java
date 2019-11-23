package controller.service;

import domain.User;

public interface UserService extends GenericService<User, Integer> {

    User findUserByLoginData(User user);

}

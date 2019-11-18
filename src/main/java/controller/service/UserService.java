package controller.service;

import controller.exception.ServiceLayerException;
import domain.User;

public interface UserService extends GenericService<User, Integer> {

    User findUserByLoginData(User user);

}

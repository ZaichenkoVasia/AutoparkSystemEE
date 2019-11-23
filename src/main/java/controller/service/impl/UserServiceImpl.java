package controller.service.impl;

import controller.exception.ServiceLayerException;
import controller.service.AbstractGenericService;
import controller.service.UserService;
import domain.User;
import model.dao.UserDAO;

public class UserServiceImpl extends AbstractGenericService<User> implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        super(userDAO);
        this.userDAO = userDAO;
    }

    @Override
    public User findUserByLoginData(User user) throws ServiceLayerException {
        LOGGER.info("Try to find user by login data");
            return userDAO.findByLogin(user);
    }
}

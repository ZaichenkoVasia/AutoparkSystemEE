package controller.service.impl;

import controller.exception.ServiceLayerRuntimeException;
import controller.exception.UserNotExistRuntimeException;
import controller.service.UserService;
import controller.service.encoder.EncoderPassword;
import controller.service.mapper.UserMapper;
import domain.User;
import model.dao.UserDAO;
import model.entity.UserEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(DriverServiceImpl.class);

    private UserDAO userDAO;
    private UserMapper mapper;
    private EncoderPassword encoderPassword;

    public UserServiceImpl(UserDAO userDAO, UserMapper mapper, EncoderPassword encoderPassword) {
        this.userDAO = userDAO;
        this.mapper = mapper;
        this.encoderPassword = encoderPassword;
    }

    @Override
    public User findUserByLoginData(User user) throws ServiceLayerRuntimeException {
        String encodedPassword = encoderPassword.encode(user.getPassword());
        LOGGER.info("Try to find user by login data");
        UserEntity userEntity = mapper.mapUserToUserEntity(user);
        userEntity = userDAO.findByLogin(userEntity);
        if (!userEntity.getPassword().equals(encodedPassword)) {
            LOGGER.error("User with this login and password is not exist");
            throw new UserNotExistRuntimeException("User with this login and password is not exist");
        }
        return mapper.mapUserEntityToUser(userEntity);

    }

    @Override
    public Integer insertElement(User element) {
        LOGGER.info("Inserting element");
        UserEntity userEntity = mapper.mapUserToUserEntity(element);
        return userDAO.insertElement(userEntity);
    }

    @Override
    public User getElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        UserEntity userEntity = userDAO.getElementById(id);
        return mapper.mapUserEntityToUser(userEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        userDAO.deleteElement(id);
    }

    @Override
    public void updateElement(User element) {
        LOGGER.info("Updating element");
        UserEntity scheduleEntity = mapper.mapUserToUserEntity(element);
        userDAO.updateElement(scheduleEntity);
    }

    @Override
    public Integer getElementsAmount() {
        LOGGER.info("Getting elements amount");
        return userDAO.getElementsCount();
    }

    @Override
    public List<User> getPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<UserEntity> result = userDAO.getPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }
}

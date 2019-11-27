package model.service.impl;

import model.exception.InvalidDataRuntimeException;
import controller.exception.ServiceLayerRuntimeException;
import model.exception.UserNotExistRuntimeException;
import model.service.UserService;
import model.service.encoder.EncoderPassword;
import model.service.mapper.UserMapper;
import model.domain.User;
import model.dao.UserDAO;
import model.entity.UserEntity;
import org.apache.log4j.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
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
        LOGGER.info("Try to find user by login data");
        if (Objects.isNull(user)) {
            LOGGER.error("Incorrect findUserByLoginData value");
            throw new InvalidDataRuntimeException("Incorrect findUserByLoginData value");
        }
        String encodedPassword = encoderPassword.encode(user.getPassword());
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
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect insertElement value");
            throw new InvalidDataRuntimeException("Incorrect insertElement value");
        }
        UserEntity userEntity = mapper.mapUserToUserEntity(element);
        return userDAO.insertElement(userEntity);
    }

    @Override
    public User findElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect findElementById value");
            throw new InvalidDataRuntimeException("Incorrect findElementById value");
        }
        UserEntity userEntity = userDAO.findElementById(id);
        return mapper.mapUserEntityToUser(userEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        if (Objects.isNull(id)) {
            LOGGER.error("Incorrect deleteElement value");
            throw new InvalidDataRuntimeException("Incorrect deleteElement value");
        }
        userDAO.deleteElement(id);
    }

    @Override
    public void updateElement(User element) {
        LOGGER.info("Updating element");
        if (Objects.isNull(element)) {
            LOGGER.error("Incorrect updateElement value");
            throw new InvalidDataRuntimeException("Incorrect updateElement value");
        }
        UserEntity scheduleEntity = mapper.mapUserToUserEntity(element);
        userDAO.updateElement(scheduleEntity);
    }

    @Override
    public Integer findElementsAmount() {
        LOGGER.info("Getting elements amount");
        return userDAO.count();
    }

    @Override
    public List<User> findPaginatedList(int startIdx, int endIdx) {
        LOGGER.info("Getting paginated list");
        List<UserEntity> result = userDAO.findPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }
}

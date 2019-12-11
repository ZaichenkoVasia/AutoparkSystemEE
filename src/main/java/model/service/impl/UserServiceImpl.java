package model.service.impl;

import controller.exception.ServiceLayerRuntimeException;
import model.dao.UserDAO;
import model.domain.User;
import model.entity.UserEntity;
import model.exception.InvalidDataRuntimeException;
import model.exception.UserNotExistRuntimeException;
import model.service.UserService;
import model.service.encoder.EncoderPassword;
import model.service.mapper.UserMapper;
import model.service.validator.impl.UserValidator;
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
    private UserValidator validator;

    public UserServiceImpl(UserDAO userDAO, UserMapper mapper, EncoderPassword encoderPassword, UserValidator userValidator) {
        this.userDAO = userDAO;
        this.mapper = mapper;
        this.encoderPassword = encoderPassword;
        this.validator = userValidator;
    }

    @Override
    public User findUserByLoginData(User user) throws ServiceLayerRuntimeException {
        if (Objects.isNull(user)) {
            LOGGER.warn("Incorrect findUserByLoginData value");
            throw new InvalidDataRuntimeException("Incorrect findUserByLoginData value");
        }
        String encodedPassword = encoderPassword.encode(user.getPassword());
        UserEntity userEntity = mapper.mapUserToUserEntity(user);
        userEntity = userDAO.findByLogin(userEntity);
        if (!userEntity.getPassword().equals(encodedPassword)) {
            LOGGER.warn("User with this login and password is not exist");
            throw new UserNotExistRuntimeException("User with this login and password is not exist");
        }
        return mapper.mapUserEntityToUser(userEntity);

    }

    @Override
    public Integer insertElement(User element) {
        if (Objects.isNull(element)) {
            LOGGER.warn("Incorrect insertElement value");
            throw new InvalidDataRuntimeException("Incorrect insertElement value");
        }
        validator.validate(element);
        UserEntity userEntity = mapper.mapUserToUserEntity(element);
        return userDAO.insertElement(userEntity);
    }

    @Override
    public User findElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        if (Objects.isNull(id)) {
            LOGGER.warn("Incorrect findElementById value");
            throw new InvalidDataRuntimeException("Incorrect findElementById value");
        }
        UserEntity userEntity = userDAO.findElementById(id);
        return mapper.mapUserEntityToUser(userEntity);
    }

    @Override
    public void deleteElement(Integer id) {
        if (Objects.isNull(id)) {
            LOGGER.warn("Incorrect deleteElement value");
            throw new InvalidDataRuntimeException("Incorrect deleteElement value");
        }
        userDAO.deleteElement(id);
    }

    @Override
    public void updateElement(User element) {
        if (Objects.isNull(element)) {
            LOGGER.warn("Incorrect updateElement value");
            throw new InvalidDataRuntimeException("Incorrect updateElement value");
        }
        validator.validate(element);
        UserEntity userEntity = mapper.mapUserToUserEntity(element);
        userDAO.updateElement(userEntity);
    }

    @Override
    public Integer findElementsAmount() {
        return userDAO.count();
    }

    @Override
    public List<User> findPaginatedList(int startIdx, int endIdx) {
        List<UserEntity> result = userDAO.findPaginatedList(startIdx, endIdx);
        return result.isEmpty() ? Collections.emptyList()
                : result.stream()
                .map(mapper::mapUserEntityToUser)
                .collect(Collectors.toList());
    }
}

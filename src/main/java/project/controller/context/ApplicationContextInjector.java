package project.controller.context;

import project.controller.command.Command;
import project.controller.command.user.LoginCommand;
import project.controller.command.user.LogoutCommand;
import project.controller.command.user.RegisterCommand;
import project.model.dao.AssignmentDao;
import project.model.dao.BusDao;
import project.model.dao.RouteDao;
import project.model.dao.UserDao;
import project.model.dao.connector.ConnectionPool;
import project.model.dao.impl.AssignmentDaoImpl;
import project.model.dao.impl.BusDaoImpl;
import project.model.dao.impl.RouteDaoImpl;
import project.model.dao.impl.UserDaoImpl;
import project.model.domain.User;
import project.model.service.AssignmentService;
import project.model.service.BusService;
import project.model.service.RouteService;
import project.model.service.UserService;
import project.model.service.encoder.PasswordEncoder;
import project.model.service.impl.AssignmentServiceImpl;
import project.model.service.impl.BusServiceImpl;
import project.model.service.impl.RouteServiceImpl;
import project.model.service.impl.UserServiceImpl;
import project.model.service.mapper.AssignmentMapper;
import project.model.service.mapper.BusMapper;
import project.model.service.mapper.RouteMapper;
import project.model.service.mapper.UserMapper;
import project.model.service.validator.UserValidator;
import project.model.service.validator.Validator;

import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {
    private static final ConnectionPool CONNECTOR = new ConnectionPool("database");

    private static final UserDao USER_DAO = new UserDaoImpl(CONNECTOR);
    private static final BusDao BUS_DAO = new BusDaoImpl(CONNECTOR);
    private static final AssignmentDao ASSIGNMENT_DAO = new AssignmentDaoImpl(CONNECTOR);
    private static final RouteDao ROUTE_DAO = new RouteDaoImpl(CONNECTOR);

    private static final UserMapper USER_MAPPER = new UserMapper();
    private static final BusMapper BUS_MAPPER = new BusMapper();
    private static final AssignmentMapper ASSIGNMENT_MAPPER = new AssignmentMapper();
    private static final RouteMapper ROUTE_MAPPER = new RouteMapper();

    private static final PasswordEncoder PASSWORD_ENCODER = new PasswordEncoder();

    private static final Validator<User> USER_VALIDATOR = new UserValidator();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, PASSWORD_ENCODER, USER_MAPPER, USER_VALIDATOR);
    private static final BusService BUS_SERVICE = new BusServiceImpl(BUS_DAO, BUS_MAPPER);
    private static final AssignmentService ASSIGNMENT_SERVICE = new AssignmentServiceImpl(ASSIGNMENT_DAO, ASSIGNMENT_MAPPER);
    private static final RouteService ROUTE_SERVICE = new RouteServiceImpl(ROUTE_DAO, ROUTE_MAPPER);

    private static final Command LOGIN_COMMAND = new LoginCommand(USER_SERVICE);
    private static final Command LOGOUT_COMMAND = new LogoutCommand();
    private static final Command REGISTER_COMMAND = new RegisterCommand(USER_SERVICE);
    private static final Map<String, Command> USER_COMMANDS_NAME_TO_COMMAND = initUserCommand();

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("login", LOGIN_COMMAND);
        userCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        userCommandNameToCommand.put("register", REGISTER_COMMAND);
        return userCommandNameToCommand;
    }

    private static ApplicationContextInjector injector;

    private ApplicationContextInjector() {
    }

    public static ApplicationContextInjector getInstance() {
        if (injector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (injector == null) {
                    injector = new ApplicationContextInjector();
                }
            }
        }
        return injector;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public BusService getBusService() {
        return BUS_SERVICE;
    }

    public AssignmentService getAssignmentService() {
        return ASSIGNMENT_SERVICE;
    }

    public RouteService getRouteService() {
        return ROUTE_SERVICE;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMANDS_NAME_TO_COMMAND;
    }
}

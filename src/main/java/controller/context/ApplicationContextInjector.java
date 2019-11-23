package controller.context;

import controller.command.Command;
import controller.command.impl.*;
import controller.service.*;
import controller.service.impl.*;
import controller.util.collectors.impl.*;
import model.dao.*;
import model.dao.connection.PoolConection;
import model.dao.impl.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContextInjector {
    private static final PoolConection CONNECTOR = new PoolConection();

    private static final AdminDAO ADMIN_DAO = new AdminDAOImpl(CONNECTOR);
    private static final BusDAO BUS_DAO = new BusDAOImpl(CONNECTOR);
    private static final DriverDAO DRIVER_DAO = new DriverDAOImpl(CONNECTOR);
    private static final RouteDAO ROUTE_DAO = new RouteDAOImpl(CONNECTOR);
    private static final ScheduleDAO SCHEDULE_DAO = new ScheduleDAOImpl(CONNECTOR);
    private static final UserDAO USER_DAO = new UserDAOImpl(CONNECTOR);

    private static final AdminService ADMIN_SERVICE = new AdminServiceImpl(ADMIN_DAO);
    private static final BusService BUS_SERVICE = new BusServiceImpl(BUS_DAO);
    private static final DriverService DRIVER_SERVICE = new DriverServiceImpl(DRIVER_DAO);
    private static final RouteService ROUTE_SERVICE = new RouteServiceImpl(ROUTE_DAO);
    private static final ScheduleService SCHEDULE_SERVICE = new ScheduleServiceImpl(SCHEDULE_DAO);
    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO);
    private static final BusStationService BUS_STATION_SERVICE = new BusStationServiceImpl(
            ADMIN_SERVICE, BUS_SERVICE, DRIVER_SERVICE, ROUTE_SERVICE, SCHEDULE_SERVICE, USER_SERVICE);

    private static final AdminDataCollector ADMIN_DATA_COLLECTOR = new AdminDataCollector();
    private static final BusDataCollector BUS_DATA_COLLECTOR = new BusDataCollector();
    private static final DriverDataCollector DRIVER_DATA_COLLECTOR = new DriverDataCollector();
    private static final RouteDataCollector ROUTE_DATA_COLLECTOR = new RouteDataCollector();
    private static final ScheduleDataCollector SCHEDULE_DATA_COLLECTOR = new ScheduleDataCollector();
    private static final UserDataCollector USER_DATA_COLLECTOR = new UserDataCollector();

    private static final Map<String, Command> NAME_COMMAND_TO_COMMANDS = initCommand();

    private static Map<String, Command> initCommand() {
        Map<String, Command> commandNameToCommand = new HashMap<>();
        commandNameToCommand.put("EMPTY", new EmptyCommand());
        commandNameToCommand.put("LOGIN", new LoginCommand(USER_SERVICE, USER_DATA_COLLECTOR));
        commandNameToCommand.put("LOGIN_PAGE", new LoginPageCommand());
        commandNameToCommand.put("LOGOUT_PAGE", new LogOutCommand());
        commandNameToCommand.put("LANGUAGE", new LanguageCommand());
        commandNameToCommand.put("ABOUT", new AboutResourceCommand());
        commandNameToCommand.put("ACCOUNT", new AccountCommand(BUS_STATION_SERVICE, ADMIN_SERVICE));
        commandNameToCommand.put("CONTACTS", new ContactsCommand());
        commandNameToCommand.put("SEARCH", new SearchCommand(ROUTE_SERVICE));

        commandNameToCommand.put("BUSES", new BusesCommand(BUS_SERVICE));
        commandNameToCommand.put("DRIVERS", new DriversCommand(DRIVER_SERVICE));
        commandNameToCommand.put("ROUTES", new RoutesCommand(ROUTE_SERVICE));

        /*Route menu commands*/
        commandNameToCommand.put("BUSES_INFO", new BusesInfoCommand(BUS_SERVICE));
        commandNameToCommand.put("BUSES_TO_APPOINT", new BusesToAppointCommand(BUS_SERVICE));
        commandNameToCommand.put("EDIT_ROUTE", new EditRouteCommand(ROUTE_SERVICE));
        commandNameToCommand.put("DELETE_ROUTE", new DeleteRouteCommand(BUS_STATION_SERVICE));

        commandNameToCommand.put("APPOINT_BUS", new AppointBusCommand(BUS_STATION_SERVICE));

        /*Bus menu commands*/
        commandNameToCommand.put("DRIVER_INFO", new DriverInfoCommand(DRIVER_SERVICE));
        commandNameToCommand.put("FREE_DRIVERS", new FreeDriversCommand(DRIVER_SERVICE));
        commandNameToCommand.put("EDIT_BUS", new EditBusCommand(BUS_SERVICE));
        commandNameToCommand.put("DELETE_BUS", new DeleteBusCommand(BUS_STATION_SERVICE));

        commandNameToCommand.put("APPOINT_DRIVER", new AppointDriverCommand(BUS_STATION_SERVICE));

        /*Driver menu commands*/
        commandNameToCommand.put("EDIT_DRIVER", new EditDriverCommand(DRIVER_SERVICE));
        commandNameToCommand.put("EDIT_DRIVER_ACCOUNT", new EditDriverCommand(DRIVER_SERVICE));
        commandNameToCommand.put("DELETE_DRIVER", new DeleteDriverCommand(BUS_STATION_SERVICE));

        commandNameToCommand.put("ADD_BUS", new AddBusCommand());
        commandNameToCommand.put("ADD_ROUTE", new AddRouteCommand());
        commandNameToCommand.put("ADD_DRIVER", new AddDriverCommand());
        commandNameToCommand.put("SAVE_ADMIN", new SaveAdminCommand(BUS_STATION_SERVICE, ADMIN_DATA_COLLECTOR));
        commandNameToCommand.put("SAVE_BUS", new SaveBusCommand(BUS_STATION_SERVICE, BUS_DATA_COLLECTOR));
        commandNameToCommand.put("SAVE_DRIVER", new SaveDriverCommand(BUS_STATION_SERVICE, DRIVER_DATA_COLLECTOR));
        commandNameToCommand.put("SAVE_ROUTE", new SaveRouteCommand(ROUTE_SERVICE, ROUTE_DATA_COLLECTOR));
        commandNameToCommand.put("CANCEL_BUS", new CancelBusCommand(BUS_STATION_SERVICE));
        commandNameToCommand.put("CANCEL_DRIVER", new CancelDriverCommand(BUS_STATION_SERVICE));
        commandNameToCommand.put("ACCEPT_SCHEDULE", new AcceptSchedule(DRIVER_SERVICE));
        return commandNameToCommand;
    }

    public Map<String, Command> getCommands() {
        return NAME_COMMAND_TO_COMMANDS;
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

    public static AdminService getAdminService() {
        return ADMIN_SERVICE;
    }

    public static BusService getBusService() {
        return BUS_SERVICE;
    }

    public static DriverService getDriverService() {
        return DRIVER_SERVICE;
    }

    public static RouteService getRouteService() {
        return ROUTE_SERVICE;
    }

    public static ScheduleService getScheduleService() {
        return SCHEDULE_SERVICE;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public static BusStationService getBusStationService() {
        return BUS_STATION_SERVICE;
    }
}


package model.service.impl;

import controller.exception.ServiceLayerRuntimeException;
import model.service.*;
import model.service.encoder.EncoderPassword;
import model.domain.*;
import org.apache.log4j.Logger;

public class BusStationServiceImpl implements BusStationService {
    private static final Logger LOGGER = Logger.getLogger(BusStationServiceImpl.class);

    private AdminService adminService;
    private BusService busService;
    private DriverService driverService;
    private RouteService routeService;
    private ScheduleService scheduleService;
    private UserService userService;
    private EncoderPassword encoderPassword;

    public BusStationServiceImpl(AdminService adminService, BusService busService,
                                 DriverService driverService, RouteService routeService,
                                 ScheduleService scheduleService, UserService userService,
                                 EncoderPassword encoderPassword) {
        this.adminService = adminService;
        this.busService = busService;
        this.driverService = driverService;
        this.routeService = routeService;
        this.scheduleService = scheduleService;
        this.userService = userService;
        this.encoderPassword = encoderPassword;
    }

    @Override
    public Driver findDriverAccountDataByUserId(Integer idUser) {
        LOGGER.info("Getting driver account info");
        Driver driver = driverService.findDriverByUserId(idUser);
        if (driver.getBus().getId() != 0) {
            Bus bus = busService.findElementById(driver.getBus().getId());
            driver = new Driver(driver, bus);
            if (bus.getStatus().equals("work")) {
                Route route = routeService.findElementById(bus.getRoute().getId());
                bus = new Bus(bus, route);
            }
        }
        return driver;
    }

    @Override
    public void deleteRoute(Integer idRoute) {
        LOGGER.info("Deleting route from the system");
        Integer busCounter = busService.countBusesOnRouteById(idRoute);
        if (busCounter > 0) {
            routeService.cancelAll(idRoute);
        }
        routeService.deleteElement(idRoute);
    }

    @Override
    public Boolean appointBusToRoute(Integer idRoute, Integer idBus) {
        LOGGER.info("Assigning bus to the route");
        Driver driver = driverService.findDriverByBusId(idBus);
        if (driver == null) {
            return false;
        }
        Integer busCounter = busService.countBusesOnRouteById(idRoute);
        if (busCounter == 0) {
            routeService.setStatusWork(idRoute);
        }
        busService.appointBusToRoute(idRoute, idBus);
        return true;
    }

    @Override
    public void deleteBus(Integer idBus) {
        LOGGER.info("Deleting bus from the system");
        Bus bus = busService.findElementById(idBus);
        if (bus.getRoute() != null) {
            Integer idRoute = bus.getRoute().getId();
            if (bus.getStatus().equals("work")) {
                Integer busCounter = busService.countBusesOnRouteById(idRoute);
                if (busCounter == 1) {
                    routeService.setStatusEmpty(idRoute);
                }
                busService.cancelBusFromRoute(idBus);
            }
            driverService.cancelDriverFromBus(idBus);
        }
        busService.deleteElement(idBus);
    }

    @Override
    public Boolean appointDriverToBus(Integer idBus, Integer idDriver) {
        LOGGER.info("Assigning driver to the bus");
        Driver existingDriver = driverService.findDriverByBusId(idBus);
        if (existingDriver == null) {
            driverService.updateBusInfoForDriver(idBus, idDriver);
            return true;
        } else if (existingDriver.getStatus().equals("free")) {
            driverService.cancelDriverFromBus(idBus);
            driverService.updateBusInfoForDriver(idBus, idDriver);
        } else if (existingDriver.getStatus().equals("work")) {
            driverService.cancelDriverFromBus(idBus);
            driverService.updateBusInfoForDriver(idBus, idDriver);
            driverService.setStatusNew(idDriver);
        }
        return false;
    }

    @Override
    public void deleteDriver(Integer idDriver) throws ServiceLayerRuntimeException {
        LOGGER.info("Deleting bus from the system");
        Driver driver = driverService.findElementById(idDriver);
        if (driver.getStatus().equals("work") || driver.getStatus().equals("new")) {
            Bus bus = busService.findElementById(driver.getBus().getId());
            Integer idRoute = bus.getRoute().getId();
            Integer busCount = busService.countBusesOnRouteById(idRoute);
            if (busCount == 1) {
                routeService.setStatusEmpty(idRoute);
            }
            busService.cancelBusFromRoute(driver.getBus().getId());
        }
        driverService.deleteElement(idDriver);
        userService.deleteElement(driver.getUser().getId());
    }

    @Override
    public Boolean saveAdmin(Admin admin, User user, String idAdmin, String idUser) throws ServiceLayerRuntimeException {
        LOGGER.info("Saving admin");
        if (idUser == null || idUser.isEmpty()) {
            String encodedPassword = encoderPassword.encode(user.getPassword());
            user = new User(user, encodedPassword);
            user = new User(user, userService.insertElement(user));
            admin = new Admin(admin, user);
            adminService.insertElement(admin);
            return true;
        } else {
            user = new User(user, Integer.valueOf(idUser));
            admin = new Admin(admin, Integer.valueOf(idAdmin));
            userService.updateElement(user);
            adminService.updateElement(admin);
            return false;
        }
    }

    @Override
    public Boolean saveDriver(Driver driver, User user, String idDriver, String idUser) throws ServiceLayerRuntimeException {
        LOGGER.info("Saving driver");
        if (idUser == null || idUser.isEmpty()) {
            String encodedPassword = encoderPassword.encode(user.getPassword());
            user = new User(user, encodedPassword);
            user = new User(user, userService.insertElement(user));
            driver = new Driver(driver, user);
            driverService.insertElement(driver);
            return true;
        } else {
            user = new User(user, Integer.valueOf(idUser));
            userService.updateElement(user);
            driver = new Driver(driver, user);
            driver = new Driver(driver, Integer.valueOf(idDriver));
            driverService.updateElement(driver);
            return false;
        }
    }

    @Override
    public Boolean saveBus(Bus bus, Schedule schedule, String idBus, String idSchedule) throws ServiceLayerRuntimeException {
        LOGGER.info("Saving bus");
        if (idBus == null || idBus.isEmpty()) {
            schedule = new Schedule(schedule, scheduleService.insertElement(schedule));
            bus = new Bus(bus, schedule);
            busService.insertElement(bus);
            return true;
        } else {
            bus = new Bus(bus, Integer.valueOf(idBus));
            schedule = new Schedule(schedule, Integer.valueOf(idSchedule));
            bus = new Bus(bus, schedule);
            busService.updateElement(bus);
            scheduleService.updateElement(schedule);
            return false;
        }
    }

    @Override
    public void cancelBus(Integer idBus, Integer idRoute) throws ServiceLayerRuntimeException {
        LOGGER.info("Cancel bus from the route");
        busService.cancelBusFromRoute(idBus);
        Integer busCounter = busService.countBusesOnRouteById(idRoute);
        if (busCounter == 0) {
            routeService.setStatusEmpty(idRoute);
        }
    }

    @Override
    public Boolean cancelDriver(Integer idBus) throws ServiceLayerRuntimeException {
        LOGGER.info("Cancel driver from bus");
        Driver driver = driverService.findDriverByBusId(idBus);
        if (driver == null) {
            return false;
        }
        if (driver.getStatus().equals("free")) {
            driverService.cancelDriverFromBus(idBus);
        } else if (driver.getStatus().equals("work")) {
            Bus bus = busService.findElementById(idBus);
            Integer busCounter = busService.countBusesOnRouteById(bus.getRoute().getId());
            if (busCounter == 1) {
                routeService.cancelAll(bus.getRoute().getId());
            } else {
                busService.cancelBusFromRoute(idBus);
            }
            driverService.cancelDriverFromBus(idBus);
        }
        return true;
    }
}

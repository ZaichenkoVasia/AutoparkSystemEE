package controller.service.impl;

import controller.exception.ServiceLayerException;
import controller.service.*;
import domain.*;
import org.apache.log4j.Logger;

public class BusStationServiceImpl implements BusStationService {

    private AdminService adminService;
    private BusService busService;
    private DriverService driverService;
    private RouteService routeService;
    private ScheduleService scheduleService;
    private UserService userService;
    private static final Logger LOGGER = Logger.getLogger(BusStationServiceImpl.class);

    public BusStationServiceImpl(AdminService adminService, BusService busService,
                                 DriverService driverService, RouteService routeService,
                                 ScheduleService scheduleService, UserService userService) {
        this.adminService = adminService;
        this.busService = busService;
        this.driverService = driverService;
        this.routeService = routeService;
        this.scheduleService = scheduleService;
        this.userService = userService;
    }

    @Override
    public Driver getDriverAccountDataByUserId(Integer idUser) {
        LOGGER.info("Getting driver account info");
        Driver driver = driverService.getDriverByUserId(idUser);
        if (driver.getBus().getId() != 0) {
            Bus bus = busService.getElementById(driver.getBus().getId());
            driver.setBus(bus);
            if (bus.getStatus().equals("work")) {
                Route route = routeService.getElementById(bus.getRoute().getId());
                bus.setRoute(route);
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
        Driver driver = driverService.getDriverByBusId(idBus);
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
        Bus bus = busService.getElementById(idBus);
        Integer idRoute = bus.getRoute().getId();
        if (bus.getStatus().equals("work")) {
            Integer busCounter = busService.countBusesOnRouteById(idRoute);
            if (busCounter == 1) {
                routeService.setStatusEmpty(idRoute);
            }
            busService.cancelBusFromRoute(idBus);
        }
        driverService.cancelDriverFromBus(idBus);
        busService.deleteElement(idBus);
    }

    @Override
    public Boolean appointDriverToBus(Integer idBus, Integer idDriver) {
        LOGGER.info("Assigning driver to the bus");
        Driver existingDriver = driverService.getDriverByBusId(idBus);
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
    public void deleteDriver(Integer idDriver) throws ServiceLayerException {
        LOGGER.info("Deleting bus from the system");
        Driver driver = driverService.getElementById(idDriver);
        if (driver.getStatus().equals("work") || driver.getStatus().equals("new")) {
            Bus bus = busService.getElementById(driver.getBus().getId());
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
    public Boolean saveAdmin(Admin admin, User user, String idAdmin, String idUser) throws ServiceLayerException {
        LOGGER.info("Saving admin");
        if (idUser == null || idUser.isEmpty()) {
            user.setId(userService.insertElement(user));
            adminService.insertElement(admin);
            return true;
        } else {
            user.setId(Integer.valueOf(idUser));
            admin.setId(Integer.valueOf(idAdmin));
            userService.updateElement(user);
            adminService.updateElement(admin);
            return false;
        }
    }

    @Override
    public Boolean saveDriver(Driver driver, User user, String idDriver, String idUser) throws ServiceLayerException {
        LOGGER.info("Saving driver");
        if (idUser == null || idUser.isEmpty()) {
            user.setId(userService.insertElement(user));
            driverService.insertElement(driver);
            return true;
        } else {
            user.setId(Integer.valueOf(idUser));
            userService.updateElement(user);
            driver.setId(Integer.valueOf(idDriver));
            driverService.updateElement(driver);
            return false;
        }
    }

    @Override
    public Boolean saveBus(Bus bus, Schedule schedule, String idBus, String idSchedule) throws ServiceLayerException {
        LOGGER.info("Saving bus");
        if (idBus == null || idBus.isEmpty()) {
            schedule.setId(scheduleService.insertElement(schedule));
            busService.insertElement(bus);
            return true;
        } else {
            bus.setId(Integer.valueOf(idBus));
            busService.updateElement(bus);
            schedule.setId(Integer.valueOf(idSchedule));
            scheduleService.updateElement(schedule);
            return false;
        }
    }

    @Override
    public void cancelBus(Integer idBus, Integer idRoute) throws ServiceLayerException {
        LOGGER.info("Cancel bus from the route");
        busService.cancelBusFromRoute(idBus);
        Integer busCounter = busService.countBusesOnRouteById(idRoute);
        if (busCounter == 0) {
            routeService.setStatusEmpty(idRoute);
        }
    }

    @Override
    public Boolean cancelDriver(Integer idBus) throws ServiceLayerException {
        LOGGER.info("Cancel driver from bus");
        Driver driver = driverService.getDriverByBusId(idBus);
        if (driver == null) {
            return false;
        }
        if (driver.getStatus().equals("free")) {
            driverService.cancelDriverFromBus(idBus);
        } else if (driver.getStatus().equals("work")) {
            Bus bus = busService.getElementById(idBus);
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

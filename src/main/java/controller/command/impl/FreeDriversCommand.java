package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.context.ApplicationContextInjector;
import controller.exception.ServiceLayerException;
import controller.service.DriverService;
import controller.service.ServiceFactory;
import domain.Driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class FreeDriversCommand implements Command {

    private DriverService driverService;

    public FreeDriversCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idBus = request.getParameter(FrontConstants.BUS_ID);
        List<Driver> drivers = driverService.getFreeDrivers();
        if (drivers.size() == 0) {
            request.setAttribute(FrontConstants.MESSAGE, Messages.NO_DRIVERS_TO_APPOINT);
            return PathJSP.INDEX_PAGE;
        }
        request.setAttribute(FrontConstants.LIST, drivers);
        request.setAttribute(FrontConstants.BUS_ID, idBus);
        return PathJSP.FREE_DRIVERS_PAGE;
    }
}

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

public class DriverInfoCommand implements Command {

    private DriverService driverService;

    public DriverInfoCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idBus = request.getParameter(FrontConstants.BUS_ID);
        Driver driver = driverService.getDriverByBusId(Integer.parseInt(idBus));
        if (driver == null) {
            request.setAttribute(FrontConstants.MESSAGE, Messages.DRIVER_NOT_ASSIGNED);
            return PathJSP.INDEX_PAGE;
        }
        request.setAttribute(FrontConstants.DRIVER, driver);
        return PathJSP.ADD_EDIT_DRIVER_PAGE;
    }
}

package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.service.DriverService;
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
        String idBus = request.getParameter("idBus");
        Driver driver = driverService.getDriverByBusId(Integer.parseInt(idBus));
        if (driver == null) {
            request.setAttribute("message", Messages.DRIVER_NOT_ASSIGNED);
            return PathJSP.INDEX_PAGE;
        }
        request.setAttribute("driver", driver);
        return PathJSP.ADD_EDIT_DRIVER_PAGE;
    }
}

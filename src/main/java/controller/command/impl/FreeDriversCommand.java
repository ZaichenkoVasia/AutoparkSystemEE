package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.service.DriverService;
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
        String idBus = request.getParameter("idBus");
        List<Driver> drivers = driverService.getFreeDrivers();
        if (drivers.size() == 0) {
            request.setAttribute("message", Messages.NO_DRIVERS_TO_APPOINT);
            return PathJSP.INDEX_PAGE;
        }
        request.setAttribute("list", drivers);
        request.setAttribute("idBus", idBus);
        return PathJSP.FREE_DRIVERS_PAGE;
    }
}

package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.service.DriverService;
import controller.constants.PathJSP;
import domain.Driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditDriverCommand implements Command {

    private DriverService driverService;

    public EditDriverCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        Integer idDriver = Integer.valueOf(request.getParameter("idDriver"));
        Driver driver = driverService.getElementById(idDriver);
        request.setAttribute("driver", driver);
        return PathJSP.ADD_EDIT_DRIVER_PAGE;
    }
}

package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import controller.service.DriverService;
import domain.Driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditDriverCommand implements Command {

    private DriverService driverService;

    public EditDriverCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        Integer idDriver = Integer.valueOf(request.getParameter("idDriver"));
        Driver driver = driverService.findElementById(idDriver);
        request.setAttribute("driver", driver);
        return "WEB-INF/jsp/editing_pages/add_driver.jsp";
    }
}

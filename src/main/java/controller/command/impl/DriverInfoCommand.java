package controller.command.impl;

import controller.command.Command;
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
            request.setAttribute("message","driver.not.assigned");
            return "index.jsp";
        }
        request.setAttribute("driver", driver);
        return "WEB-INF/jsp/editing_pages/add_driver.jsp";
    }
}

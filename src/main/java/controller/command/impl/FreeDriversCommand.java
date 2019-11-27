package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        String idBus = request.getParameter("idBus");
        List<Driver> drivers = driverService.findFreeDrivers();
        if (drivers.size() == 0) {
            request.setAttribute("message", "no.drivers.to.appoint");
            return "index.jsp";
        }
        request.setAttribute("list", drivers);
        request.setAttribute("idBus", idBus);
        return "WEB-INF/jsp/admin/free_drivers.jsp";
    }
}

package controller.command.impl.driver;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.domain.Driver;
import model.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DriverInfoCommand implements Command {

    private DriverService driverService;

    public DriverInfoCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        String idBus = request.getParameter("idBus");
        Driver driver = driverService.findDriverByBusId(Integer.parseInt(idBus));
        if (driver == null) {
            request.setAttribute("message", "driver.not.assigned");
            return "index.jsp";
        }
        request.setAttribute("driver", driver);
        return "WEB-INF/jsp/editing_pages/add_driver.jsp";
    }
}

package controller.command.impl.driver;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.domain.Driver;
import model.service.DriverService;

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

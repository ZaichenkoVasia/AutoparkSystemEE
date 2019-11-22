package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.service.DriverService;
import controller.service.pagination.Carriage;
import controller.service.pagination.PaginationManager;
import controller.constants.PathJSP;
import domain.Driver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DriversCommand implements Command {

    private DriverService driverService;

    public DriversCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String page = request.getParameter("page");
        String lastPage = request.getParameter("lastPage");
        PaginationManager<Driver> paginationManager = new PaginationManager<>();
        Carriage carriage = paginationManager.getCarriage(page, lastPage, "5", driverService);
        List<Driver> drivers = driverService.getPaginatedList(carriage.getStartIdx(), carriage.getEntityAmount());
        request.setAttribute("lastPage", carriage.getLastPage());
        request.setAttribute("currentPage", carriage.getCurrentPage());
        request.setAttribute("list", drivers);
        return PathJSP.DRIVERS_PAGE;
    }
}

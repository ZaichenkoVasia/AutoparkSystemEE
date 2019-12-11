package controller.command.impl.driver;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.domain.Driver;
import model.service.DriverService;
import model.service.pagination.Carriage;
import model.service.pagination.PaginationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DriversCommand implements Command {

    private DriverService driverService;

    public DriversCommand(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        String page = request.getParameter("page");
        String lastPage = request.getParameter("lastPage");
        PaginationManager<Driver> paginationManager = new PaginationManager<>();
        Carriage carriage = paginationManager.getCarriage(page, lastPage, "5", driverService);
        List<Driver> drivers = driverService.findPaginatedList(carriage.getStartIdx(), carriage.getEntityAmount());
        request.setAttribute("lastPage", carriage.getLastPage());
        request.setAttribute("currentPage", carriage.getCurrentPage());
        request.setAttribute("list", drivers);
        return "WEB-INF/jsp/driver.jsp";
    }
}

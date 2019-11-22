package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.service.BusService;
import controller.service.pagination.Carriage;
import controller.service.pagination.PaginationManager;
import domain.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BusesCommand implements Command {

    private BusService busService;

    public BusesCommand(BusService busService) {
        this.busService = busService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String page = request.getParameter("page");
        String lastPage = request.getParameter("lastPage");
        PaginationManager<Bus> paginationManager = new PaginationManager<>();
        Carriage carriage = paginationManager.getCarriage(page, lastPage, "5", busService);
        List<Bus> buses = busService.getPaginatedList(carriage.getStartIdx(), carriage.getEntityAmount());
        request.setAttribute("lastPage", carriage.getLastPage());
        request.setAttribute("currentPage", carriage.getCurrentPage());
        request.setAttribute("list", buses);
        return "WEB-INF/jsp/bus.jsp";
    }
}

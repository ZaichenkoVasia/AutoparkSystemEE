package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import controller.service.RouteService;
import controller.service.pagination.Carriage;
import controller.service.pagination.PaginationManager;
import domain.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RoutesCommand implements Command {

    private RouteService routeService;

    public RoutesCommand(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        String page = request.getParameter("page");
        String lastPage = request.getParameter("lastPage");
        PaginationManager<Route> paginationManager = new PaginationManager<>();
        Carriage carriage = paginationManager.getCarriage(page, lastPage, "5", routeService);
        List<Route> routes = routeService.findPaginatedList(carriage.getStartIdx(), carriage.getEntityAmount());
        request.setAttribute("lastPage", carriage.getLastPage());
        request.setAttribute("currentPage", carriage.getCurrentPage());
        request.setAttribute("list", routes);
        return "WEB-INF/jsp/route.jsp";
    }
}

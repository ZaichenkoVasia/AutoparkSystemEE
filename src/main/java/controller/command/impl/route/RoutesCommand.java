package controller.command.impl.route;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.service.RouteService;
import model.service.pagination.Carriage;
import model.service.pagination.PaginationManager;
import model.domain.Route;

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

package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import controller.service.RouteService;
import domain.Route;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchCommand implements Command {

    private RouteService routeService;

    public SearchCommand(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        if (!departure.isEmpty() && !arrival.isEmpty()) {
            List<Route> routes = routeService.searchByCriteria(departure, arrival);
            if (routes.isEmpty()) {
                request.setAttribute("message", "no.results.for.criteria");
                return "index.jsp";
            } else {
                request.setAttribute("list", routes);
                return "WEB-INF/jsp/route.jsp";
            }
        }
        request.setAttribute("message", "incorrect.input");
        return "index.jsp";
    }
}

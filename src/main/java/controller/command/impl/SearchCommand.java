package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        if (!departure.isEmpty() && !arrival.isEmpty()) {
            List<Route> routes = routeService.searchByCriteria(departure, arrival);
            if (routes.isEmpty()) {
                request.setAttribute("message", Messages.NO_RESULTS_FOR_CRITERIA);
                return PathJSP.INDEX_PAGE;
            } else {
                request.setAttribute("list", routes);
                return PathJSP.ROUTES_PAGE;
            }
        }
        request.setAttribute("message", Messages.INCORRECT_INPUT);
        return PathJSP.INDEX_PAGE;
    }
}

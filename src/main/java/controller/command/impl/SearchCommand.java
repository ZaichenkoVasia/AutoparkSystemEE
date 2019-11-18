package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.context.ApplicationContextInjector;
import controller.exception.ServiceLayerException;
import controller.service.RouteService;
import controller.service.ServiceFactory;
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
        String departure = request.getParameter(FrontConstants.DEPARTURE_CITY);
        String arrival = request.getParameter(FrontConstants.ARRIVAL_CITY);
        if (!departure.isEmpty() && !arrival.isEmpty()) {
            List<Route> routes = routeService.searchByCriteria(departure, arrival);
            if (routes.isEmpty()) {
                request.setAttribute(FrontConstants.MESSAGE, Messages.NO_RESULTS_FOR_CRITERIA);
                return PathJSP.INDEX_PAGE;
            } else {
                request.setAttribute(FrontConstants.LIST, routes);
                return PathJSP.ROUTES_PAGE;
            }
        }
        request.setAttribute(FrontConstants.MESSAGE, Messages.INCORRECT_INPUT);
        return PathJSP.INDEX_PAGE;
    }
}

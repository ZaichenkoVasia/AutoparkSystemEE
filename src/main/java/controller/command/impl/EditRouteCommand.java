package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.context.ApplicationContextInjector;
import controller.exception.ServiceLayerException;
import controller.service.RouteService;
import controller.service.ServiceFactory;
import controller.constants.PathJSP;
import domain.Route;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditRouteCommand implements Command {

    private RouteService routeService;

    public EditRouteCommand(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idRoute = request.getParameter(FrontConstants.ROUTE_ID);
        Route route = routeService.getElementById(Integer.parseInt(idRoute));
        request.setAttribute(FrontConstants.ROUTE, route);
        return PathJSP.ADD_EDIT_ROUTE_PAGE;
    }
}

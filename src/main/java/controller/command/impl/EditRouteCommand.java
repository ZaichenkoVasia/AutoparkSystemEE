package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.service.RouteService;
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
        String idRoute = request.getParameter("idRoute");
        Route route = routeService.getElementById(Integer.parseInt(idRoute));
        request.setAttribute("route", route);
        return "WEB-INF/jsp/editing_pages/add_route.jsp";
    }
}

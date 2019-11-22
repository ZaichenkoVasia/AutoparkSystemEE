package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteRouteCommand implements Command {

    private BusStationService busStationService;

    public DeleteRouteCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        Integer idRoute = Integer.valueOf(request.getParameter("idRoute"));
        busStationService.deleteRoute(idRoute);
        request.setAttribute("message", "route.deleted");
        return "index.jsp";
    }
}

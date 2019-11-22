package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppointBusCommand implements Command {

    private BusStationService busStationService;

    public AppointBusCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException{
        Integer idRoute = Integer.valueOf(request.getParameter("idRoute"));
        Integer idBus = Integer.valueOf(request.getParameter("idBus"));
        if (busStationService.appointBusToRoute(idRoute, idBus)) {
            request.setAttribute("message", Messages.BUS_ASSIGNED_TO_ROUTE);
        } else {
            request.setAttribute("message", Messages.NO_CONNECTED_DRIVER);
        }
        return PathJSP.INDEX_PAGE;
    }
}

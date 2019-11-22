package controller.command.impl;

import controller.command.Command;
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
            request.setAttribute("message", "bus.assigned.to.route");
        } else {
            request.setAttribute("message", "no.connected.driver");
        }
        return "index.jsp";
    }
}

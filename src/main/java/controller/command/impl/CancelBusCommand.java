package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import controller.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelBusCommand implements Command {

    private BusStationService busStationService;

    public CancelBusCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        Integer idRoute = Integer.valueOf(request.getParameter("idRoute"));
        Integer idBus = Integer.valueOf(request.getParameter("idBus"));
        busStationService.cancelBus(idBus, idRoute);
        request.setAttribute("message", "bus.canceled");
        return "index.jsp";
    }
}

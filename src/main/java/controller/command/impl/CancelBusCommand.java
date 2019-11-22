package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelBusCommand implements Command {

    private BusStationService busStationService;

    public CancelBusCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        Integer idRoute = Integer.valueOf(request.getParameter("idRoute"));
        Integer idBus = Integer.valueOf(request.getParameter("idBus"));
        busStationService.cancelBus(idBus, idRoute);
        request.setAttribute("message", Messages.BUS_CANCELED);
        return PathJSP.INDEX_PAGE;
    }
}

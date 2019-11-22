package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelDriverCommand implements Command {

    private BusStationService busStationService;

    public CancelDriverCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException{
        Integer idBus = Integer.valueOf(request.getParameter("idBus"));
            if (busStationService.cancelDriver(idBus)){
                request.setAttribute("message", Messages.DRIVER_CANCELED);
            }else {
                request.setAttribute("message", Messages.DRIVER_NOT_ASSIGNED);
            }
        return PathJSP.INDEX_PAGE;
    }
}

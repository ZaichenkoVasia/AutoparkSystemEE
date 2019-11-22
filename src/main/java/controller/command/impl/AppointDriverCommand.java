package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppointDriverCommand implements Command {

    private BusStationService busStationService;

    public AppointDriverCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException{
        Integer idDriver = Integer.valueOf(request.getParameter("idDriver"));
        Integer idBus = Integer.valueOf(request.getParameter("idBus"));
        if (busStationService.appointDriverToBus(idBus, idDriver)) {
            request.setAttribute("message", Messages.DRIVER_ASSIGNED);
        } else {
            request.setAttribute("message", Messages.DRIVER_SWAPPED);
        }
        return PathJSP.INDEX_PAGE;
    }
}

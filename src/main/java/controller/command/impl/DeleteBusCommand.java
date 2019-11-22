package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBusCommand implements Command {

    private BusStationService busStationService;

    public DeleteBusCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        Integer idBus = Integer.valueOf(request.getParameter("idBus"));
        busStationService.deleteBus(idBus);
        request.setAttribute("message", Messages.BUS_HAS_BEEN_DELETED);
        return PathJSP.INDEX_PAGE;
    }
}

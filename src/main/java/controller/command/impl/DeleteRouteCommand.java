package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.context.ApplicationContextInjector;
import controller.exception.ServiceLayerException;
import controller.service.BusStationService;
import controller.service.impl.BusStationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteRouteCommand implements Command {

    private BusStationService busStationService;

    public DeleteRouteCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        Integer idRoute = Integer.valueOf(request.getParameter(FrontConstants.ROUTE_ID));
        busStationService.deleteRoute(idRoute);
        request.setAttribute(FrontConstants.MESSAGE, Messages.ROUTE_HAS_BEEN_DELETED);
        return PathJSP.INDEX_PAGE;
    }
}

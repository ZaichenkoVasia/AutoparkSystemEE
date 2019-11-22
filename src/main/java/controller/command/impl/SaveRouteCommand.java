package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.RouteService;
import controller.util.collectors.impl.RouteDataCollector;
import domain.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveRouteCommand implements Command {

    private RouteService routeService;
    private static final Logger logger = Logger.getLogger(SaveRouteCommand.class);

    public SaveRouteCommand(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        logger.info("Executing SaveRouteCommand");
        String idRoute = request.getParameter("idRoute");
        try {
            Route route = new RouteDataCollector().retrieveData(request);
            if (idRoute == null || idRoute.isEmpty()){
                routeService.insertElement(route);
                request.setAttribute("message", Messages.ROUTE_SAVED);
            }else {
                route.setId(Integer.valueOf(idRoute));
                routeService.updateElement(route);
                request.setAttribute("message", Messages.ROUTE_UPDATED);
            }
        } catch (WrongInputDataException e) {
            logger.warn("Incorrect input data.", e);
            request.setAttribute("message", Messages.INPUT_ERROR);
            return PathJSP.ADD_EDIT_ROUTE_PAGE;
        }
        return PathJSP.INDEX_PAGE;
    }
}

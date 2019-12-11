package controller.command.impl.route;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.impl.RouteDataCollector;
import model.domain.Route;
import model.service.RouteService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveRouteCommand implements Command {

    private static final Logger logger = Logger.getLogger(SaveRouteCommand.class);
    private RouteService routeService;
    private RouteDataCollector routeDataCollector;

    public SaveRouteCommand(RouteService routeService, RouteDataCollector routeDataCollector) {
        this.routeService = routeService;
        this.routeDataCollector = routeDataCollector;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        logger.info("Executing SaveRouteCommand");
        String idRoute = request.getParameter("idRoute");
        try {
            Route route = routeDataCollector.retrieveData(request);
            if (idRoute == null || idRoute.isEmpty()) {
                routeService.insertElement(route);
                request.setAttribute("message", "route.saved");
            } else {
                route = new Route(route, Integer.valueOf(idRoute));
                routeService.updateElement(route);
                request.setAttribute("message", "route.updated");
            }
        } catch (WrongInputDataRuntimeException e) {
            logger.warn("Incorrect input data.", e);
            request.setAttribute("message", "input.error");
            return "WEB-INF/jsp/editing_pages/add_route.jsp";
        }
        return "index.jsp";
    }
}

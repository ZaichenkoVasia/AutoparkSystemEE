package controller.util.collectors.impl;

import controller.constants.FrontConstants;
import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RouteDataCollector extends DataCollector<Route> {

    private static final Logger logger = Logger.getLogger(RouteDataCollector.class);

    @Override
    public Route retrieveData(HttpServletRequest request) throws WrongInputDataException {
        logger.info("Retrieving route data from request");
        int counter = 5;
        String number = request.getParameter(FrontConstants.NUMBER);
        String title = request.getParameter(FrontConstants.TITLE);
        String distance = request.getParameter(FrontConstants.DISTANCE);
        String departure = request.getParameter(FrontConstants.DEPARTURE_CITY);
        String arrival = request.getParameter(FrontConstants.ARRIVAL_CITY);
        Route route = new Route();
        if (number != null){
            route.setNumber(number);
            counter--;
        }
        if (title != null){
            route.setTitle(title);
            counter--;
        }
        if (distance != null ){
            route.setDistance(Integer.valueOf(distance));
            counter--;
        }
        if (departure != null){
            route.setDeparture(departure);
            counter--;
        }
        if (arrival != null){
            route.setArrival(arrival);
            counter--;
        }
        if (counter != 0){
            logger.warn("Not all input forms filled correctly");
            request.setAttribute(FrontConstants.ROUTE, route);
            throw new WrongInputDataException("Not all input form filled correctly");
        }
        return route;
    }
}
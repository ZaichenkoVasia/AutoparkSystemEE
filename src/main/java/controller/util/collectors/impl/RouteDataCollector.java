package controller.util.collectors.impl;

import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.DataCollector;
import domain.Route;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class RouteDataCollector extends DataCollector<Route> {

    private static final Logger LOGGER = Logger.getLogger(RouteDataCollector.class);

    @Override
    public Route retrieveData(HttpServletRequest request) throws WrongInputDataRuntimeException {
        LOGGER.info("Retrieving route data from request");
        int counter = 5;
        String number = request.getParameter("number");
        String title = request.getParameter("title");
        String distance = request.getParameter("distance");
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        Route route;
        // if (number != null){
        route = Route.builder()
                .withNumber(number)
                .withTitle(title)
                .withDistance(Integer.valueOf(distance))
                .withStatus("free")
                .withDeparture(departure)
                .withArrival(arrival)
                .build();
//        if (counter != 0){
//            LOGGER.warn("Not all input forms filled correctly");
//            request.setAttribute("route", route);
//            throw new WrongInputDataRuntimeException("Not all input form filled correctly");
        //    }
        return route;
    }
}
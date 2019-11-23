package controller.util.collectors.impl;

import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Schedule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ScheduleDataCollector extends DataCollector<Schedule> {

    private static final Logger LOGGER = Logger.getLogger(ScheduleDataCollector.class);

    @Override
    public Schedule retrieveData(HttpServletRequest request) throws WrongInputDataException {
        LOGGER.info("Retrieving schedule data from request");
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        Schedule schedule;
        //if (departure != null) {
        schedule = Schedule.builder()
                .withDeparture(departure)
                .withArrival(arrival)
                .build();
//        if (counter != 0) {
//            LOGGER.warn("Not all input forms filled correctly");
//            request.setAttribute("schedule", schedule);
//            throw new WrongInputDataException("Not all input form filled correctly");
//        }
        return schedule;
    }
}

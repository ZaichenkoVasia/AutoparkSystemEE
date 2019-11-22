package controller.util.collectors.impl;

import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Schedule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ScheduleDataCollector extends DataCollector<Schedule> {

    private static final Logger logger = Logger.getLogger(ScheduleDataCollector.class);

    @Override
    public Schedule retrieveData(HttpServletRequest request) throws WrongInputDataException {
        logger.info("Retrieving schedule data from request");
        int counter = 2;
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        Schedule schedule = new Schedule();
        if (departure != null) {
            schedule.setDeparture(departure);
            counter--;
        }
        if (arrival != null) {
            schedule.setArrival(arrival);
            counter--;
        }
        if (counter != 0) {
            logger.warn("Not all input forms filled correctly");
            request.setAttribute("schedule", schedule);
            throw new WrongInputDataException("Not all input form filled correctly");
        }
        return schedule;
    }
}

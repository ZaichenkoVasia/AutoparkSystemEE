package controller.util.collectors.impl;

import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.DataCollector;
import domain.Schedule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class ScheduleDataCollector extends DataCollector<Schedule> {

    private static final Logger LOGGER = Logger.getLogger(ScheduleDataCollector.class);

    @Override
    public Schedule retrieveData(HttpServletRequest request) throws WrongInputDataRuntimeException {
        LOGGER.info("Retrieving schedule data from request");
        String departure = request.getParameter("departure");
        String arrival = request.getParameter("arrival");
        return Schedule.builder()
                .withDeparture(departure)
                .withArrival(arrival)
                .build();
    }
}

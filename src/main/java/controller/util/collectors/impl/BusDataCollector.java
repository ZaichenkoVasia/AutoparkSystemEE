package controller.util.collectors.impl;

import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.DataCollector;
import model.domain.Bus;
import model.domain.Schedule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class BusDataCollector extends DataCollector<Bus> {

    private static final Logger LOGGER = Logger.getLogger(BusDataCollector.class);

    @Override
    public Bus retrieveData(HttpServletRequest request) throws WrongInputDataRuntimeException {
        LOGGER.info("Retrieving bus data from request");
        String plate = request.getParameter("plate");
        String model = request.getParameter("model");
        String mileage = request.getParameter("mileage");
        String inspection = request.getParameter("inspection");
        String consumption = request.getParameter("consumption");
        String release = request.getParameter("release");
        String seats = request.getParameter("seats");
        Schedule schedule = new ScheduleDataCollector().retrieveData(request);
        return Bus.builder()
                .withPlate(plate)
                .withModel(model)
                .withMileage(Integer.valueOf(mileage))
                .withInspection(Date.valueOf(inspection))
                .withConsumption(Integer.valueOf(consumption))
                .withRelease(Date.valueOf(release))
                .withSeats(Integer.valueOf(seats))
                .withStatus("free")
                .withSchedule(schedule)
                .build();
    }
}

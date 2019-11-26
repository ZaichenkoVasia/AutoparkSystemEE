package controller.util.collectors.impl;

import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.DataCollector;
import domain.Bus;
import domain.Schedule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class BusDataCollector extends DataCollector<Bus> {

    private static final Logger LOGGER = Logger.getLogger(BusDataCollector.class);

    @Override
    public Bus retrieveData(HttpServletRequest request) throws WrongInputDataRuntimeException {
        LOGGER.info("Retrieving bus data from request");
        int counter = 7;
        String plate = request.getParameter("plate");
        String model = request.getParameter("model");
        String mileage = request.getParameter("mileage");
        String inspection = request.getParameter("inspection");
        String consumption = request.getParameter("consumption");
        String release = request.getParameter("release");
        String seats = request.getParameter("seats");
        Schedule schedule = new ScheduleDataCollector().retrieveData(request);
        Bus bus;
        //if (plate != null) {
        bus = Bus.builder()
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
//        if (counter != 0) {
//            LOGGER.warn("Not all input forms filled correctly");
//            request.setAttribute("bus", bus);
//            throw new WrongInputDataRuntimeException("Not all input form filled correctly");
//        }
        return bus;
    }
}

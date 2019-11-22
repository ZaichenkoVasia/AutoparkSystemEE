package controller.util.collectors.impl;

import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Bus;
import domain.Schedule;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class BusDataCollector extends DataCollector<Bus> {

    private static final Logger logger = Logger.getLogger(BusDataCollector.class);

    @Override
    public Bus retrieveData(HttpServletRequest request) throws WrongInputDataException {
        logger.info("Retrieving bus data from request");
        int counter = 7;
        String plate = request.getParameter("plate");
        String model = request.getParameter("model");
        String mileage = request.getParameter("mileage");
        String inspection = request.getParameter("inspection");
        String consumption = request.getParameter("consumption");
        String release = request.getParameter("release");
        String seats = request.getParameter("seats");
        Bus bus = new Bus();
        if (plate != null) {
            bus.setPlate(plate);
            counter--;
        }
        if (model != null) {
            bus.setModel(model);
            counter--;
        }
        if (mileage != null) {
            bus.setMileage(Integer.valueOf(mileage));
            counter--;
        }
        if (inspection != null) {
            bus.setInspection(Date.valueOf(inspection));
            counter--;
        }
        if (consumption != null) {
            bus.setConsumption(Integer.valueOf(consumption));
            counter--;
        }
        if (release != null) {
            bus.setRelease(Date.valueOf(release));
            counter--;
        }
        if (seats != null) {
            bus.setSeats(Integer.valueOf(seats));
            counter--;
        }
        try {
            Schedule schedule = new ScheduleDataCollector().retrieveData(request);
            bus.setSchedule(schedule);
        } catch (WrongInputDataException e) {
            bus.setSchedule((Schedule) request.getAttribute("schedule"));
            request.setAttribute("bus", bus);
            throw new WrongInputDataException(e);
        }
        if (counter != 0) {
            logger.warn("Not all input forms filled correctly");
            request.setAttribute("bus", bus);
            throw new WrongInputDataException("Not all input form filled correctly");
        }
        return bus;
    }
}

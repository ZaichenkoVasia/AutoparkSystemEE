package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.BusStationService;
import controller.util.collectors.impl.BusDataCollector;
import domain.Bus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveBusCommand implements Command {

    private BusStationService busStationService;
    private static final Logger logger = Logger.getLogger(SaveBusCommand.class);

    public SaveBusCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        logger.info("Executing SaveBusCommand");
        String idBus = request.getParameter("idBus");
        String idSchedule = request.getParameter("idSchedule");
        try {
            Bus bus = new BusDataCollector().retrieveData(request);
            if (busStationService.saveBus(bus, bus.getSchedule(), idBus, idSchedule)){
                request.setAttribute("message", "bus.saved");
            }else {
                request.setAttribute("message", "bus.updated");
            }
        } catch (WrongInputDataException e) {
            logger.warn("Incorrect input data", e);
            request.setAttribute("message", "input.error");
            return "WEB-INF/jsp/editing_pages/add_bus.jsp";
        }
        return "index.jsp";
    }
}

package controller.command.impl.bus;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import controller.exception.WrongInputDataRuntimeException;
import model.service.BusStationService;
import controller.util.collectors.impl.BusDataCollector;
import model.domain.Bus;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveBusCommand implements Command {

    private static final Logger logger = Logger.getLogger(SaveBusCommand.class);
    private BusStationService busStationService;
    private BusDataCollector busDataCollector;

    public SaveBusCommand(BusStationService busStationService, BusDataCollector busDataCollector) {
        this.busStationService = busStationService;
        this.busDataCollector = busDataCollector;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        logger.info("Executing SaveBusCommand");
        String idBus = request.getParameter("idBus");
        String idSchedule = request.getParameter("idSchedule");
        try {
            Bus bus = busDataCollector.retrieveData(request);
            if (busStationService.saveBus(bus, bus.getSchedule(), idBus, idSchedule)){
                request.setAttribute("message", "bus.saved");
            }else {
                request.setAttribute("message", "bus.updated");
            }
        } catch (WrongInputDataRuntimeException e) {
            logger.warn("Incorrect input data", e);
            request.setAttribute("message", "input.error");
            return "WEB-INF/jsp/editing_pages/add_bus.jsp";
        }
        return "index.jsp";
    }
}

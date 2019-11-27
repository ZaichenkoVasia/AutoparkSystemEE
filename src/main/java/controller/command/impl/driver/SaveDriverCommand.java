package controller.command.impl.driver;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import controller.exception.WrongInputDataRuntimeException;
import model.service.BusStationService;
import controller.util.collectors.impl.DriverDataCollector;
import model.domain.Driver;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveDriverCommand implements Command {

    private static final Logger logger = Logger.getLogger(SaveDriverCommand.class);
    private BusStationService busStationService;
    private DriverDataCollector driverDataCollector;

    public SaveDriverCommand(BusStationService busStationService, DriverDataCollector driverDataCollector) {
        this.busStationService = busStationService;
        this.driverDataCollector = driverDataCollector;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        logger.info("Executing SaveDriverCommand");
        String idDriver = request.getParameter("idDriver");
        String idUser = request.getParameter("idUser");
        try {
            Driver driver = driverDataCollector.retrieveData(request);
            if (busStationService.saveDriver(driver, driver.getUser(), idDriver, idUser)) {
                request.setAttribute("message", "driver.saved");
            } else {
                request.setAttribute("message", "driver.updated");
            }
        } catch (WrongInputDataRuntimeException e) {
            logger.warn("Incorrect input data", e);
            request.setAttribute("message", "input.error");
            return "WEB-INF/jsp/editing_pages/add_driver.jsp";
        }
        return "index.jsp";
    }
}

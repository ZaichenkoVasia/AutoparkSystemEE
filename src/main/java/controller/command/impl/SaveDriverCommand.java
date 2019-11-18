package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.context.ApplicationContextInjector;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.BusStationService;
import controller.util.collectors.impl.DriverDataCollector;
import domain.Driver;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveDriverCommand implements Command {

    private static final Logger logger = Logger.getLogger(SaveDriverCommand.class);
    private BusStationService busStationService;

    public SaveDriverCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        logger.info("Executing SaveDriverCommand");
        String idDriver = request.getParameter(FrontConstants.DRIVER_ID);
        String idUser = request.getParameter(FrontConstants.USER_ID);
        try {
            Driver driver = new DriverDataCollector().retrieveData(request);
            if (busStationService.saveDriver(driver, driver.getUser(), idDriver, idUser)) {
                request.setAttribute(FrontConstants.MESSAGE, Messages.DRIVER_SAVED);
            } else {
                request.setAttribute(FrontConstants.MESSAGE, Messages.DRIVER_UPDATED);
            }
        } catch (WrongInputDataException e) {
            logger.warn("Incorrect input data", e);
            request.setAttribute(FrontConstants.MESSAGE, Messages.INPUT_ERROR);
            return PathJSP.ADD_EDIT_DRIVER_PAGE;
        }
        return PathJSP.INDEX_PAGE;
    }
}

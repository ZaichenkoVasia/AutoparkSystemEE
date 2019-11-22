package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.BusStationService;
import controller.util.collectors.impl.AdminDataCollector;
import domain.Admin;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveAdminCommand implements Command {

    private BusStationService busStationService;
    private static final Logger logger = Logger.getLogger(SaveAdminCommand.class);

    public SaveAdminCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        logger.info("Executing SaveAdminCommand");
        String idAdmin = request.getParameter("idAdmin");
        String idUser = request.getParameter("idUser");
        try {
            Admin admin = new AdminDataCollector().retrieveData(request);
            if (busStationService.saveAdmin(admin, admin.getUser(), idAdmin, idUser)){
                request.setAttribute("message", Messages.ADMIN_SAVED);
            }else {
                request.setAttribute("message", Messages.ADMIN_UPDATED);
            }
        } catch (WrongInputDataException e) {
            logger.warn("Incorrect input data", e);
            request.setAttribute("message", Messages.INPUT_ERROR);
            return PathJSP.ADMIN_ACCOUNT_PAGE;
        }
        return PathJSP.INDEX_PAGE;
    }
}

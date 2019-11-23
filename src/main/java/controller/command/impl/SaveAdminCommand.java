package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;
import controller.service.BusStationService;
import controller.util.collectors.impl.AdminDataCollector;
import domain.Admin;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveAdminCommand implements Command {

    private static final Logger logger = Logger.getLogger(SaveAdminCommand.class);
    private BusStationService busStationService;
    private AdminDataCollector adminDataCollector;

    public SaveAdminCommand(BusStationService busStationService, AdminDataCollector adminDataCollector) {
        this.busStationService = busStationService;
        this.adminDataCollector = adminDataCollector;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        logger.info("Executing SaveAdminCommand");
        String idAdmin = request.getParameter("idAdmin");
        String idUser = request.getParameter("idUser");
        try {
            Admin admin = adminDataCollector.retrieveData(request);
            if (busStationService.saveAdmin(admin, admin.getUser(), idAdmin, idUser)){
                request.setAttribute("message", "admin.saved");
            }else {
                request.setAttribute("message", "admin.updated");
            }
        } catch (WrongInputDataException e) {
            logger.warn("Incorrect input data", e);
            request.setAttribute("message", "input.error");
            return "WEB-INF/jsp/admin/account.jsp";
        }
        return "index.jsp";
    }
}

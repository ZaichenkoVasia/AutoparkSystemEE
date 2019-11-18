package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.constants.Messages;
import controller.constants.PathJSP;
import controller.context.ApplicationContextInjector;
import controller.exception.ServiceLayerException;
import controller.service.DriverService;
import controller.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcceptSchedule implements Command {

    private DriverService driverService;

    public AcceptSchedule(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idDriver = request.getParameter(FrontConstants.DRIVER_ID);
        String status = request.getParameter(FrontConstants.STATUS);
        if (!status.equals(FrontConstants.STATUS_NEW)){
            request.setAttribute(FrontConstants.MESSAGE, Messages.SCHEDULE_ALREADY_CONFIRMED);
            return PathJSP.INDEX_PAGE;
        }
        driverService.setStatusWork(Integer.parseInt(idDriver));
        request.setAttribute(FrontConstants.MESSAGE, Messages.SCHEDULE_CONFIRMED);
        return PathJSP.INDEX_PAGE;
    }
}

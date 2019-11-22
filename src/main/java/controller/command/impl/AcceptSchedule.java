package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcceptSchedule implements Command {

    private DriverService driverService;

    public AcceptSchedule(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idDriver = request.getParameter("idDriver");
        String status = request.getParameter("status");
        if (!status.equals("new")){
            request.setAttribute("message", "schedule.already.confirmed");
            return "index.jsp";
        }
        driverService.setStatusWork(Integer.parseInt(idDriver));
        request.setAttribute("message", "schedule.confirmed");
        return "index.jsp";
    }
}

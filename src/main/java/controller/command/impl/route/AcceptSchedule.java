package controller.command.impl.route;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.service.DriverService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcceptSchedule implements Command {

    private DriverService driverService;

    public AcceptSchedule(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        String idDriver = request.getParameter("idDriver");
        String status = request.getParameter("status");
        if (status.equals("new") || status.equals("free")){
            driverService.setStatusWork(Integer.parseInt(idDriver));
            request.setAttribute("message", "schedule.confirmed");
            return "index.jsp";
        }
        request.setAttribute("message", "schedule.already.confirmed");
        return "index.jsp";

    }
}

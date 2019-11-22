package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.service.BusService;
import domain.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BusesToAppointCommand implements Command {

    private BusService busService;

    public BusesToAppointCommand(BusService busService) {
        this.busService = busService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idRoute = request.getParameter("idRoute");
        List<Bus> buses = busService.getFreeBuses();
        if (buses.size() == 0){
            request.setAttribute("message","no.buses.to.appoint");
            return "index.jsp";
        }
        request.setAttribute("list", buses);
        request.setAttribute("idRoute", idRoute);
        return "WEB-INF/jsp/admin/free_buses.jsp";
    }
}

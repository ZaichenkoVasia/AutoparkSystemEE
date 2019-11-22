package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.service.BusService;
import domain.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BusesInfoCommand implements Command {

    private BusService busService;

    public BusesInfoCommand(BusService busService) {
        this.busService = busService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idRoute = request.getParameter("idRoute");
        List<Bus> buses = busService.getBusesByIdRoute(Integer.valueOf(idRoute));
        if (buses.size() == 0){
            request.setAttribute("message", "route.is.empty");
            return "index.jsp";
        }
        request.setAttribute("list", buses);
        request.setAttribute("idRoute", idRoute);
        return "WEB-INF/jsp/admin/buses_on_route.jsp";
    }
}

package controller.command.impl.bus;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.domain.Bus;
import model.service.BusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BusesToAppointCommand implements Command {

    private BusService busService;

    public BusesToAppointCommand(BusService busService) {
        this.busService = busService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        String idRoute = request.getParameter("idRoute");
        List<Bus> buses = busService.findFreeBuses();
        if (buses.size() == 0) {
            request.setAttribute("message", "no.buses.to.appoint");
            return "index.jsp";
        }
        request.setAttribute("list", buses);
        request.setAttribute("idRoute", idRoute);
        return "WEB-INF/jsp/admin/free_buses.jsp";
    }
}

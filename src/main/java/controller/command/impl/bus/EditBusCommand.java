package controller.command.impl.bus;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.domain.Bus;
import model.service.BusService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBusCommand implements Command {

    private BusService busService;

    public EditBusCommand(BusService busService) {
        this.busService = busService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        String idBus = request.getParameter("idBus");
        Bus bus = busService.findElementById(Integer.parseInt(idBus));
        request.setAttribute("bus", bus);
        return "WEB-INF/jsp/editing_pages/add_bus.jsp";
    }
}

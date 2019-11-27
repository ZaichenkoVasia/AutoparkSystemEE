package controller.command.impl.bus;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBusCommand implements Command {

    private BusStationService busStationService;

    public DeleteBusCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        Integer idBus = Integer.valueOf(request.getParameter("idBus"));
        busStationService.deleteBus(idBus);
        request.setAttribute("message", "bus.deleted");
        return "index.jsp";
    }
}

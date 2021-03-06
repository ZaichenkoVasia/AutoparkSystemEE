package controller.command.impl.driver;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppointDriverCommand implements Command {

    private BusStationService busStationService;

    public AppointDriverCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        Integer idDriver = Integer.valueOf(request.getParameter("idDriver"));
        Integer idBus = Integer.valueOf(request.getParameter("idBus"));
        if (busStationService.appointDriverToBus(idBus, idDriver)) {
            request.setAttribute("message", "driver.assigned");
        } else {
            request.setAttribute("message", "drivers.swapped");
        }
        return "index.jsp";
    }
}

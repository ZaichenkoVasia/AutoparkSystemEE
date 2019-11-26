package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import controller.service.BusStationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CancelDriverCommand implements Command {

    private BusStationService busStationService;

    public CancelDriverCommand(BusStationService busStationService) {
        this.busStationService = busStationService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        Integer idBus = Integer.valueOf(request.getParameter("idBus"));
            if (busStationService.cancelDriver(idBus)){
                request.setAttribute("message", "driver.canceled");
            }else {
                request.setAttribute("message", "driver.not.assigned");
            }
        return "index.jsp";
    }
}

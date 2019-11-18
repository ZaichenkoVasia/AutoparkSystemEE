package controller.command.impl;

import controller.command.Command;
import controller.constants.FrontConstants;
import controller.context.ApplicationContextInjector;
import controller.exception.ServiceLayerException;
import controller.service.BusService;
import controller.service.ServiceFactory;
import controller.constants.PathJSP;
import domain.Bus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBusCommand implements Command {

    private BusService busService;

    public EditBusCommand(BusService busService) {
        this.busService = busService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        String idBus = request.getParameter(FrontConstants.BUS_ID);
        Bus bus = busService.getElementById(Integer.parseInt(idBus));
        request.setAttribute(FrontConstants.BUS, bus);
        return PathJSP.ADD_EDIT_BUS_PAGE;
    }
}

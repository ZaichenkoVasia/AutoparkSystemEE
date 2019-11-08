package project.controller.command.show;

import project.controller.command.Command;
import project.model.domain.Bus;
import project.model.service.BusService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class BusShowCommand implements Command {
    private BusService busService;

    public BusShowCommand(BusService busService) {
        this.busService = busService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

        List<Bus> buses = busService.findAll(currentPage, recordsPerPage);

        request.setAttribute("buses", buses);

        int rows = busService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        final String command = request.getParameter("command");
        request.setAttribute("showBuses", command);

        return "listBuses.jsp";
    }
}

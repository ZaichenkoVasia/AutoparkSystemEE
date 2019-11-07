package project.controller.command.show;

import project.controller.command.Command;
import project.model.domain.Route;
import project.model.service.RouteService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class RouteShowCommand implements Command {
    private RouteService routeService;

    public RouteShowCommand(RouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    public String execute(HttpServletRequest request) {
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));

        List<Route> routes = routeService.findAll(currentPage, recordsPerPage);

        request.setAttribute("routes", routes);

        int rows = routeService.getNumberOfRows();

        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {
            nOfPages++;
        }

        request.setAttribute("noOfPages", nOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage", recordsPerPage);
        final String command = request.getParameter("commandShow");
        request.setAttribute("showRoutes", command);

        return "listRoutes.jsp";
    }
}

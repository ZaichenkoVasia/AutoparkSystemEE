package controller.command.impl.route;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddRouteCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "WEB-INF/jsp/editing_pages/add_route.jsp";
    }
}

package controller.command.impl.bus;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddBusCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return "WEB-INF/jsp/editing_pages/add_bus.jsp";
    }
}

package project.controller.servlet.user;

import project.controller.command.Command;
import project.controller.command.user.RegisterCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    private static final Map<String, Command> nameToCommand = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = null;
        String action = req.getParameter("command");

        if (action == null || action.isEmpty() ) {
            throw new RuntimeException();
        }

        Command currentCommand = nameToCommand.get(action);

        if ( currentCommand == null ) {
            throw new RuntimeException();
        }

        page = currentCommand.execute(req);

        if ( page != null ) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(page);
            dispatcher.forward(req, resp);
        }
    }
}

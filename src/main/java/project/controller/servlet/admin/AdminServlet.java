package project.controller.servlet.admin;

import project.controller.command.Command;
import project.controller.context.ApplicationContextInjector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AdminServlet extends HttpServlet {
    private final Map<String, Command> commandNameToCommand;
    private final Command defaultCommand;

    public AdminServlet() {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        this.commandNameToCommand = injector.getUserCommands();
        this.defaultCommand = request -> "view/problem";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String command = req.getParameter("command");

        final String page = commandNameToCommand.getOrDefault(command, defaultCommand).execute(req);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}

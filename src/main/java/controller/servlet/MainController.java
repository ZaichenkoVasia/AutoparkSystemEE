package controller.servlet;

import controller.command.Command;
import controller.command.impl.menu.EmptyCommand;
import controller.context.ApplicationContextInjector;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class MainController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);

    private final Map<String, Command> commandNameToCommand;
    private final Command defaultCommand;

    public MainController() {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        this.commandNameToCommand = injector.getCommands();
        this.defaultCommand = new EmptyCommand();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Main controller, processing request.");
        final String command = request.getParameter("command");
        String message = (String) request.getAttribute("message");
        String page = null;
        if (message != null) {
            page = defaultCommand.execute(request, response);
            request.getRequestDispatcher(page).forward(request, response);
        } else {
            LOGGER.info("Command is: " + command.toUpperCase());
            page = commandNameToCommand.getOrDefault(command.toUpperCase(), defaultCommand).execute(request, response);
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}

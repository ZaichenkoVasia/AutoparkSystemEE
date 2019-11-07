package project.controller.servlet.user;

import project.controller.command.Command;
import project.controller.context.ApplicationContextInjector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final Map<String, Command> commandNameToCommand;
    private final Command defaultCommand;

    public LoginServlet() {
        ApplicationContextInjector injector = ApplicationContextInjector.getInstance();
        this.commandNameToCommand = injector.getUserCommands();
        this.defaultCommand = request -> "problem.jsp";
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        final String command = request.getParameter("commandSecurity");

        final String page = commandNameToCommand.getOrDefault(command, defaultCommand).execute(request);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}

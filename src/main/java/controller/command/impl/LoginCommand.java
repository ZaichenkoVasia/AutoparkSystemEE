package controller.command.impl;

import controller.command.Command;
import controller.exception.ServiceLayerException;
import controller.service.UserService;
import domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerException {
        HttpSession session = request.getSession();
        User user = new User.UserBuilder()
                .setLogin(request.getParameter("login"))
                .setPassword(request.getParameter("password"))
                .createUser();
        User existingUser = userService.findUserByLoginData(user);
        if (user.equals(existingUser)) {
            session.setAttribute("user", existingUser);
            return "index.jsp";
        }
        request.setAttribute("message", "user.not.registered");
        request.setAttribute("user", user);
        return "WEB-INF/jsp/login.jsp";
    }
}

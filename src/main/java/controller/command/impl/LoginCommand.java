package controller.command.impl;

import controller.command.Command;
import controller.constants.Messages;
import controller.exception.ServiceLayerException;
import controller.service.UserService;
import controller.constants.PathJSP;
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
            return PathJSP.INDEX_PAGE;
        }
        request.setAttribute("message", Messages.USER_NOT_REGISTERED);
        request.setAttribute("user", user);
        return PathJSP.LOGIN_PAGE;
    }
}

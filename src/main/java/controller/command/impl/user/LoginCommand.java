package controller.command.impl.user;

import controller.command.Command;
import controller.exception.ServiceLayerRuntimeException;
import model.service.UserService;
import model.service.encoder.EncoderPassword;
import controller.util.collectors.impl.UserDataCollector;
import model.domain.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private UserService userService;
    private UserDataCollector userDataCollector;
    private EncoderPassword encoderPassword;

    public LoginCommand(UserService userService, UserDataCollector userDataCollector,
                        EncoderPassword encoderPassword) {
        this.userService = userService;
        this.userDataCollector = userDataCollector;
        this.encoderPassword = encoderPassword;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException {
        HttpSession session = request.getSession();
        User user = userDataCollector.retrieveData(request);
        User existingUser = userService.findUserByLoginData(user);
        String encodedPassword = encoderPassword.encode(user.getPassword());
        user = new User(user, encodedPassword);
        if (user.equals(existingUser)) {
            session.setAttribute("user", existingUser);
            return "index.jsp";
        }
        request.setAttribute("message", "user.not.registered");
        request.setAttribute("user", user);
        return "WEB-INF/jsp/login.jsp";
    }
}

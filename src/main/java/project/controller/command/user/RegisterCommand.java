package project.controller.command.user;

import project.controller.command.Command;
import project.model.domain.User;
import project.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

public class RegisterCommand implements Command {
    private final UserService userService;

    public RegisterCommand(UserService userService) {
        this.userService = userService;
    }
    @Override
    public String execute(HttpServletRequest request) {
        //to session
        final String name = (String) request.getAttribute("name");
        final String surname = (String) request.getAttribute("surname");
        final String email = (String) request.getAttribute("email");

        final String password = (String) request.getAttribute("password");
        final String passwordConfirm = (String) request.getAttribute("passwordConfirm");
        if(!Objects.equals(password, passwordConfirm)){
            return "view/register.jsp";
        }

        User user = User.builder()
                .withEmail(email)
                .withName(name)
                .withSurname(surname)
                .withPassword(password)
                .build();
        userService.register(user);
        return "view/login.jsp";
    }
}

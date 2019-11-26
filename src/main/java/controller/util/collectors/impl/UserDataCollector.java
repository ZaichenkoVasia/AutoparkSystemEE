package controller.util.collectors.impl;

import controller.exception.WrongInputDataRuntimeException;
import controller.util.collectors.DataCollector;
import domain.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserDataCollector extends DataCollector<User> {

    private static final Logger LOGGER = Logger.getLogger(UserDataCollector.class);

    @Override
    public User retrieveData(HttpServletRequest request) throws WrongInputDataRuntimeException {
        LOGGER.info("Retrieving user data from request");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user;
        user = User.builder()
                .withLogin(login)
                .withPassword(password)
                .build();
//        if (counter != 0) {
//            LOGGER.warn("Not all input forms filled correctly");
//            request.setAttribute("user", user);
//            throw new WrongInputDataRuntimeException("Not all input form filled correctly");
//        }
        return user;
    }
}

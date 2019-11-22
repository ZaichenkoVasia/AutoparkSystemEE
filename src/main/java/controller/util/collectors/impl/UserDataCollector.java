package controller.util.collectors.impl;

import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class UserDataCollector extends DataCollector<User> {

    private static final Logger logger = Logger.getLogger(UserDataCollector.class);

    @Override
    public User retrieveData(HttpServletRequest request) throws WrongInputDataException {
        logger.info("Retrieving user data from request");
        int counter = 2;
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User();
        if (login != null) {
            user.setLogin(login);
            counter--;
        }
        if (password != null) {
            user.setPassword(password);
            counter--;
    }
        if (counter != 0) {
            logger.warn("Not all input forms filled correctly");
            request.setAttribute("user", user);
            throw new WrongInputDataException("Not all input form filled correctly");
        }
        return user;
    }
}
package controller.util.collectors.impl;

import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Admin;
import domain.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class AdminDataCollector extends DataCollector<Admin> {

    private static final Logger logger = Logger.getLogger(AdminDataCollector.class);

    @Override
    public Admin retrieveData(HttpServletRequest request) throws WrongInputDataException {
        logger.info("Retrieving bus data from request");
        int counter = 5;
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birth = request.getParameter("birth");
        String degree = request.getParameter("degree");
        String graduation = request.getParameter("graduation");
        Admin admin = new Admin();
        if (name != null) {
            admin.setName(name);
            counter--;
        }
        if (surname != null) {
            admin.setSurname(surname);
            counter--;
        }
        if (birth != null) {
            admin.setBirth(Date.valueOf(birth));
            counter--;
        }
        if (degree != null) {
            admin.setDegree(degree);
            counter--;
        }
        if (graduation != null) {
            admin.setGraduation(Date.valueOf(graduation));
            counter--;
        }
        try {
            User user = new UserDataCollector().retrieveData(request);
            admin.setUser(user);
        } catch (WrongInputDataException e) {
            admin.setUser((User) request.getAttribute("user"));
            request.setAttribute("admin", admin);
            throw new WrongInputDataException(e);
        }
        if (counter != 0) {
            logger.warn("Not all input forms filled correctly");
            request.setAttribute("admin", admin);
            throw new WrongInputDataException("Not all input form filled correctly");
        }
        return admin;
    }
}

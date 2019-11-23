package controller.util.collectors.impl;

import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Admin;
import domain.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class AdminDataCollector extends DataCollector<Admin> {

    private static final Logger LOGGER = Logger.getLogger(AdminDataCollector.class);

    @Override
    public Admin retrieveData(HttpServletRequest request) throws WrongInputDataException {
        LOGGER.info("Retrieving admin data from request");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birth = request.getParameter("birth");
        String degree = request.getParameter("degree");
        String graduation = request.getParameter("graduation");
        User user = new UserDataCollector().retrieveData(request);
        Admin admin;
        // if (name != null) {
        admin = Admin.builder()
                .withName(name)
                .withSurname(surname)
                .withBirth(Date.valueOf(birth))
                .withDegree(degree)
                .withGraduation(Date.valueOf(graduation))
                .withUser(user)
                .build();

//        if (counter != 0) {
//            LOGGER.warn("Not all input forms filled correctly");
//            request.setAttribute("admin", admin);
//            throw new WrongInputDataException("Not all input form filled correctly");
//        }
        return admin;
    }
}

package controller.util.collectors.impl;

import controller.util.collectors.DataCollector;
import model.domain.Admin;
import model.domain.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class AdminDataCollector extends DataCollector<Admin> {

    private static final Logger LOGGER = Logger.getLogger(AdminDataCollector.class);

    @Override
    public Admin retrieveData(HttpServletRequest request) {
        LOGGER.info("Retrieving admin data from request");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birth = request.getParameter("birth");
        String degree = request.getParameter("degree");
        String graduation = request.getParameter("graduation");
        User user = new UserDataCollector().retrieveData(request);
        return Admin.builder()
                .withName(name)
                .withSurname(surname)
                .withBirth(Date.valueOf(birth))
                .withDegree(degree)
                .withGraduation(Date.valueOf(graduation))
                .withUser(user)
                .build();
    }
}

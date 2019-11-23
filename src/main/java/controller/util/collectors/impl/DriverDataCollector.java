package controller.util.collectors.impl;

import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Driver;
import domain.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class DriverDataCollector extends DataCollector<Driver> {

    private static final Logger LOGGER = Logger.getLogger(DriverDataCollector.class);

    @Override
    public Driver retrieveData(HttpServletRequest request) throws WrongInputDataException {
        LOGGER.info("Retrieving driver data from request");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birth = request.getParameter("birth");
        String licenseTest = request.getParameter("test");
        String salary = request.getParameter("salary");
        User user = new UserDataCollector().retrieveData(request);
        Driver driver;
        //if (name != null) {
        driver = Driver.builder()
                .withName(name)
                .withSurname(surname)
                .withBirth(Date.valueOf(birth))
                .withLicenseTest(Date.valueOf(licenseTest))
                .withSalary(Integer.valueOf(salary))
                .withStatus("free")
                .withUser(user)
                .build();
//        if (counter != 0) {
//            LOGGER.warn("Not all input forms filled correctly");
//            request.setAttribute("driver", driver);
//            throw new WrongInputDataException("Not all input form filled correctly");
//        }
        return driver;
    }
}

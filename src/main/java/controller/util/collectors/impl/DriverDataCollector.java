package controller.util.collectors.impl;

import controller.exception.WrongInputDataException;
import controller.util.collectors.DataCollector;
import domain.Driver;
import domain.User;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class DriverDataCollector extends DataCollector<Driver> {

    private static final Logger logger = Logger.getLogger(DriverDataCollector.class);

    @Override
    public Driver retrieveData(HttpServletRequest request) throws WrongInputDataException {
        logger.info("Retrieving driver data from request");
        int counter = 5;
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String birth = request.getParameter("birth");
        String licenseTest = request.getParameter("test");
        String salary = request.getParameter("salary");
        Driver driver = new Driver();
        if (name != null) {
            driver.setName(name);
            counter--;
        }
        if (surname != null) {
            driver.setSurname(surname);
            counter--;
        }
        if (birth != null) {
            driver.setBirth(Date.valueOf(birth));
            counter--;
        }
        if (licenseTest != null) {
            driver.setLicenseTest(Date.valueOf(licenseTest));
            counter--;
        }
        if (salary != null) {
            driver.setSalary(Integer.valueOf(salary));
            counter--;
        }
        try {
            User user = new UserDataCollector().retrieveData(request);
            driver.setUser(user);
        } catch (WrongInputDataException e) {
            driver.setUser((User) request.getAttribute("user"));
            request.setAttribute("driver", driver);
            throw new WrongInputDataException(e);
        }
        if (counter != 0) {
            logger.warn("Not all input forms filled correctly");
            request.setAttribute("driver", driver);
            throw new WrongInputDataException("Not all input form filled correctly");
        }
        driver.getUser().setRole("driver");
        return driver;
    }
}
package model.service.validator.impl;

import model.domain.Driver;
import model.exception.InvalidDataRuntimeException;
import model.service.validator.Validator;

import java.util.regex.Pattern;

public class DriverValidator extends Validator<Driver> {

    private static final String PASSWORD_REGEX = "([A-Za-z0-9]{6,})";
    private static final String EMAIL_REGEX = "(\\w{2,})@(\\w+\\.)([a-z]{2,5})";
    private static final String NAME_REGEX = "([A-Za-z]{1,12})|([А-Яa-я]{1,12})";
    private static final String SALARY_REGEX = "\\d{1,7}";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern SALARY_PATTERN = Pattern.compile(SALARY_REGEX);

    public void validate(Driver driver) {
        if (driver == null) {
            LOGGER.warn("Driver is not valid");
            throw new InvalidDataRuntimeException("Driver is not valid");
        }

        validateByStringParam(driver.getName(), NAME_PATTERN, "Incorrect name");
        validateByStringParam(driver.getSurname(), NAME_PATTERN, "Incorrect surname");
        validateByStringParam(driver.getUser().getLogin(), EMAIL_PATTERN, "Incorrect email");
        validateByStringParam(driver.getUser().getPassword(), PASSWORD_PATTERN, "Incorrect password");
        validateByIntegerParam(driver.getSalary(), SALARY_PATTERN, "Incorrect salary");
    }
}

package model.service.validator.impl;

import model.domain.User;
import model.exception.InvalidDataRuntimeException;
import model.service.validator.Validator;

import java.util.regex.Pattern;

public class UserValidator extends Validator<User> {

    private static final String PASSWORD_REGEX = "([A-Za-z0-9]{6,})";
    private static final String EMAIL_REGEX = "(\\w{2,})@(\\w+\\.)([a-z]{2,5})";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public void validate(User user) {
        if (user == null) {
            LOGGER.warn("Driver is not valid");
            throw new InvalidDataRuntimeException("Driver is not valid");
        }

        validateByStringParam(user.getLogin(), EMAIL_PATTERN, "Incorrect email");
        validateByStringParam(user.getPassword(), PASSWORD_PATTERN, "Incorrect password");
    }
}

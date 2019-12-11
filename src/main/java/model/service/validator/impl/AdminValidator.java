package model.service.validator.impl;

import model.domain.Admin;
import model.exception.InvalidDataRuntimeException;
import model.service.validator.Validator;

import java.util.regex.Pattern;

public class AdminValidator extends Validator<Admin> {

    private static final String PASSWORD_REGEX = "([A-Za-z0-9]{6,})";
    private static final String EMAIL_REGEX = "(\\w{2,})@(\\w+\\.)([a-z]{2,5})";
    private static final String NAME_REGEX = "([A-Za-z]{1,12})|([А-Яa-я]{1,12})";
    private static final String DEGREE_REGEX = "([A-Za-z0-9]{3,})";
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
    private static final Pattern DEGREE_PATTERN = Pattern.compile(DEGREE_REGEX);

    public void validate(Admin admin) {
        if (admin == null) {
            LOGGER.warn("Admin is not valid");
            throw new InvalidDataRuntimeException("Admin is not valid");
        }

        validateByStringParam(admin.getUser().getLogin(), EMAIL_PATTERN, "Incorrect email");
        validateByStringParam(admin.getSurname(), NAME_PATTERN, "Incorrect surname");
        validateByStringParam(admin.getName(), NAME_PATTERN, "Incorrect name");
        validateByStringParam(admin.getDegree(), DEGREE_PATTERN, "Incorrect salary");
        validateByStringParam(admin.getUser().getPassword(), PASSWORD_PATTERN, "Incorrect password");
    }
}

package model.service.validator.impl;

import model.domain.Route;
import model.exception.InvalidDataRuntimeException;
import model.service.validator.Validator;

import java.util.regex.Pattern;

public class RouteValidator extends Validator<Route> {

    private static final String NUMBER_AND_TITLE_REGEX = "([A-Za-z0-9]{1,20})|([А-Яa-я0-9]{1,20})";
    private static final String DEPARTURE_AND_ARRIVAL_REGEX = "([A-Za-z]{1,10})";
    private static final String DISTANCE_REGEX = "([0-9]{1,3})";
    private static final Pattern NUMBER_AND_TITLE_PATTERN = Pattern.compile(NUMBER_AND_TITLE_REGEX);
    private static final Pattern DEPARTURE_AND_ARRIVAL_PATTERN = Pattern.compile(DEPARTURE_AND_ARRIVAL_REGEX);
    private static final Pattern DISTANCE_PATTERN = Pattern.compile(DISTANCE_REGEX);

    public void validate(Route route) {
        if (route == null) {
            LOGGER.warn("Bus is not valid");
            throw new InvalidDataRuntimeException("Bus is not valid");
        }

        validateByStringParam(route.getNumber(), NUMBER_AND_TITLE_PATTERN, "Incorrect number");
        validateByStringParam(route.getTitle(), NUMBER_AND_TITLE_PATTERN, "Incorrect title");
        validateByStringParam(route.getDeparture(), DEPARTURE_AND_ARRIVAL_PATTERN, "Incorrect departure");
        validateByStringParam(route.getArrival(), DEPARTURE_AND_ARRIVAL_PATTERN, "Incorrect arrival");
        validateByIntegerParam(route.getDistance(), DISTANCE_PATTERN, "Incorrect distance");
    }
}

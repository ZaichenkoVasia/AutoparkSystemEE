package controller.service.validator.impl;

import controller.exception.InvalidDataRuntimeException;
import controller.service.validator.Validator;
import domain.Bus;

import java.util.regex.Pattern;

public class BusValidator extends Validator<Bus> {

    private static final String MODEL_AND_PLATE_REGEX = "([A-Za-z]{1,20})|([А-Яa-я]{1,20})";
    private static final String CONSUMPTION_AND_SEATS_REGEX = "([0-9]{1,3})";
    private static final String MILEAGE_REGEX = "([0-9]{1,7})";
    private static final Pattern MODEL_AND_PLATE_PATTERN = Pattern.compile(MODEL_AND_PLATE_REGEX);
    private static final Pattern CONSUMPTION_AND_SEATS_PATTERN = Pattern.compile(CONSUMPTION_AND_SEATS_REGEX);
    private static final Pattern MILEAGE_PATTERN = Pattern.compile(MILEAGE_REGEX);

    public void validate(Bus bus) {
        if (bus == null) {
            LOGGER.warn("Bus is not valid");
            throw new InvalidDataRuntimeException("Bus is not valid");
        }

        validateByStringParam(bus.getModel(), MODEL_AND_PLATE_PATTERN, "Incorrect model");
        validateByStringParam(bus.getPlate(), MODEL_AND_PLATE_PATTERN, "Incorrect plate");
        validateByIntegerParam(bus.getConsumption(), CONSUMPTION_AND_SEATS_PATTERN, "Incorrect consumption");
        validateByIntegerParam(bus.getSeats(), CONSUMPTION_AND_SEATS_PATTERN, "Incorrect seats");
        validateByIntegerParam(bus.getMileage(), MILEAGE_PATTERN, "Incorrect mileage");
    }
}

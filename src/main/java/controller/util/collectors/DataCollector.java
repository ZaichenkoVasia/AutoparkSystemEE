package controller.util.collectors;

import controller.exception.WrongInputDataRuntimeException;

import javax.servlet.http.HttpServletRequest;

public abstract class DataCollector<E> {

    public abstract E retrieveData(HttpServletRequest request) throws WrongInputDataRuntimeException;
}

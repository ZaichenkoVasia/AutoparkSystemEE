package controller.command;

import controller.exception.ServiceLayerRuntimeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Command {

    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceLayerRuntimeException;
}

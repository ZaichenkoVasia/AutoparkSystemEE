package controller.service;

import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;

import java.sql.Connection;
import java.util.List;

public interface GenericService<D, ID> {

    Integer insertElement(D element);

    D getElementById(ID id);

    void deleteElement(ID id);

    void updateElement(D element);

    Integer getElementsAmount();

    List<D> getPaginatedList(int startIdx, int endIdx);
}

package controller.service;

import controller.exception.ServiceLayerException;
import controller.exception.WrongInputDataException;

import java.sql.Connection;
import java.util.List;

public interface GenericService<E, ID> {

    Integer insertElement(E elementn);

    E getElementById(ID id);

    void deleteElement(ID id);

    void updateElement(E element);

    Integer getElementsAmount();

    List<E> getPaginatedList(int startIdx, int endIdx);
}

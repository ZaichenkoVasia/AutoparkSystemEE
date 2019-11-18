package model.dao;

import model.exception.DAOException;

import java.sql.Connection;
import java.util.List;

/**
 * Generic DAO for handling input data from service layer and retrieving from DB
 * */

public interface GenericDAO<E, ID> {

    Integer insertElement(E element);

    E getElementById(ID id);

    void deleteElement(ID id);

    void updateElement(E element);

    Integer getElementsCount();

    List<E> getPaginatedList(int startIdx, int amountElements);
}

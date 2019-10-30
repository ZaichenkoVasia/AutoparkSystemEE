package ua.autopark.model.dao;

/**
 * Generic DAO for handling input data from service layer and retrieving from DB
 * */

public interface GenericDAO<E> {

    Integer insertElement(E element);

    E getElementById(int id);

    void deleteElement(int id);

    void updateElement(E element);
}

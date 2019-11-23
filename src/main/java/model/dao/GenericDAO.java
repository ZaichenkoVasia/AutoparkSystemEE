package model.dao;

import java.util.List;

public interface GenericDAO<E, ID> {

    Integer insertElement(E element);

    E getElementById(ID id);

    void deleteElement(ID id);

    void updateElement(E element);

    Integer getElementsCount();

    List<E> getPaginatedList(int startIdx, int amountElements);
}

package model.dao;

import java.util.List;

public interface GenericDAO<E, ID> {

    Integer insertElement(E element);

    E findElementById(ID id);

    void deleteElement(ID id);

    void updateElement(E element);

    Integer count();

    List<E> findPaginatedList(int startIdx, int amountElements);
}

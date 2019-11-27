package model.service;

import java.util.List;

public interface GenericService<D, ID> {

    Integer insertElement(D element);

    D findElementById(ID id);

    void deleteElement(ID id);

    void updateElement(D element);

    Integer findElementsAmount();

    List<D> findPaginatedList(int startIdx, int endIdx);
}

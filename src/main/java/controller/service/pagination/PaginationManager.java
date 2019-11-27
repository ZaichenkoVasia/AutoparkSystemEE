package controller.service.pagination;

import controller.exception.ServiceLayerRuntimeException;
import controller.service.GenericService;

public class PaginationManager<E> {

    public Carriage getCarriage(String pageNumber, String lastPage, String entityPerPage, GenericService<E, Integer> genericService) throws ServiceLayerRuntimeException {
        int currentPage = 0;
        int totalPages = 0;
        int totalEntityAmount = 0;
        int startFromIdx = 0;
        int entities = Integer.valueOf(entityPerPage);
        if (pageNumber == null) {
            totalEntityAmount = genericService.findElementsAmount();
            currentPage = 1;
            totalPages = (int) Math.ceil((double) totalEntityAmount / entities);
        } else {
            currentPage = Integer.valueOf(pageNumber);
            totalPages = Integer.valueOf(lastPage);
        }
        startFromIdx = (currentPage - 1) * entities;
        return new Carriage(startFromIdx, entities, totalPages, currentPage);
    }
}

package controller.service;

import controller.exception.ServiceLayerException;
import model.dao.GenericDAO;
import org.apache.log4j.Logger;

import java.util.List;

public abstract class AbstractGenericService<E> implements GenericService<E, Integer> {

    private GenericDAO<E, Integer> genericDAO;
    protected static final Logger LOGGER = Logger.getLogger(AbstractGenericService.class);

    public AbstractGenericService(GenericDAO<E, Integer> genericDAO) {
        this.genericDAO = genericDAO;
    }

    @Override
    public Integer insertElement(E element) {
        LOGGER.info("Inserting element");
        return genericDAO.insertElement(element);
    }

    @Override
    public void updateElement(E element) {
        LOGGER.info("Updating element");
        genericDAO.updateElement(element);
    }

    @Override
    public E getElementById(Integer id) {
        LOGGER.info("Try to get element by id");
        return genericDAO.getElementById(id);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info("Deleting element");
        genericDAO.deleteElement(id);
    }

    @Override
    public Integer getElementsAmount() throws ServiceLayerException {
        LOGGER.info("Getting elements amount");
        return genericDAO.getElementsCount();
    }

    @Override
    public List<E> getPaginatedList(int startIdx, int endIdx) throws ServiceLayerException {
        LOGGER.info("Getting paginated list");
        return genericDAO.getPaginatedList(startIdx, endIdx);
    }
}

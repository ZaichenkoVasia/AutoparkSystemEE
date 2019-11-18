package controller.service;

import controller.constants.LogMessages;
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
        LOGGER.info(LogMessages.INSERTING_ELEMENT);
        return genericDAO.insertElement(element);
    }

    @Override
    public void updateElement(E element) {
        LOGGER.info(LogMessages.UPDATING_ELEMENT);
        genericDAO.updateElement(element);
    }

    @Override
    public E getElementById(Integer id) {
        LOGGER.info(LogMessages.GETTING_ELEMENT_BY_ID);
        return genericDAO.getElementById(id);
    }

    @Override
    public void deleteElement(Integer id) {
        LOGGER.info(LogMessages.DELETING_ELEMENT);
        genericDAO.deleteElement(id);
    }

    @Override
    public Integer getElementsAmount() throws ServiceLayerException {
        LOGGER.info(LogMessages.GETTING_ELEMENTS_AMOUNT);
        return genericDAO.getElementsCount();
    }

    @Override
    public List<E> getPaginatedList(int startIdx, int endIdx) throws ServiceLayerException {
        LOGGER.info(LogMessages.GETTING_PAGINATED_LIST);
        return genericDAO.getPaginatedList(startIdx, endIdx);
    }
}

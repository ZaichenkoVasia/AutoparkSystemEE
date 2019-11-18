package model.dao;

import model.dao.connection.PoolConection;
import model.dao.constants.Constants;
import model.exception.DAOException;
import model.exception.DatabaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGenericDAO<E> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractGenericDAO.class);

    protected final PoolConection connector;

    public AbstractGenericDAO(PoolConection connector) {
        this.connector = connector;
    }

    protected Integer insert(E element, String query) {
        LOGGER.info("Inserting element");
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setInsertElementProperties(statement, element);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                LOGGER.info("Element created. Returning it to service layer");
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Can not insert element", e);
            throw new DAOException("Can not insert element", e);
        }
        return null;
    }

    protected E getElementByIntegerParam(Integer id, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Can not get element", e);
            throw new DAOException("Can not get element", e);
        }
        return null;
    }
 //TODO change to generic
    protected E getElementByStringParam(String data, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, data);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Can not get element", e);
            throw new DAOException("Can not get element", e);
        }
        return null;
    }

    //Change name method
    protected void updateElementFieldByIntegerParam(Integer id, String data, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, data);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not update element", e);
            throw new DatabaseRuntimeException("Can not update element", e);
        }
    }

    protected void updateElementByIntegerParam(Integer id, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not update element", e);
            throw new DAOException("Can not update element", e);
        }
    }

    protected void updateElement(E entity, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setUpdateElementProperties(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not update element", e);
            throw new DatabaseRuntimeException("Can not update element", e);
        }
    }

    protected void deleteElement(int id, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not delete element", e);
            throw new DAOException("Can not delete element", e);
        }
        LOGGER.info("Element was deleted from database");
    }

    protected Integer getElementCount(String query) {
        LOGGER.info("Getting amount of element");
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                LOGGER.info("Returning amount of entity");
                return resultSet.getInt(Constants.ELEMENTS_COUNT);
            }
        } catch (SQLException e) {
            LOGGER.error("Can not get count elements", e);
            throw new DAOException("Can not get count elements", e);
        }
        return null;
    }

    protected Integer getElementCountByIntegerParam(Integer id, String query) {
        LOGGER.info("Counting");
        ResultSet resultSet = null;
        Integer res = null;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt(Constants.ELEMENTS_COUNT);
            }
        } catch (SQLException e) {
            LOGGER.error("Can not get count elements", e);
            throw new DAOException("Can not get count elements", e);
        }
        return res;
    }

    protected List<E> getPaginatedList(int startIdx, int amountElements, String query) {
        ResultSet resultSet = null;
        List<E> list;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, startIdx);
            statement.setInt(2, amountElements);
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Can not get paganation list", e);
            throw new DAOException("Can not get paganation list", e);
        }
        return list;
    }

    protected List<E> getListByIntegerParam(Integer id, String query) {
        LOGGER.info("Getting");
        ResultSet resultSet = null;
        List<E> list;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Can not get list element", e);
            throw new DAOException("Can not get list element", e);
        }
        return list;
    }

    protected List<E> getListByStringParam(String data, String query) {
        LOGGER.info("Getting");
        ResultSet resultSet = null;
        List<E> list;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, data);
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Can not get list element", e);
            throw new DAOException("Can not get list element", e);
        }
        LOGGER.info("Returning list buses assigned to route");
        return list;
    }

    protected List<E> parseAllElements(ResultSet resultSet) {
        List<E> elements = new ArrayList<>();
        try {
            while (resultSet.next()) {
                elements.add(parseToOneElement(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Can not parse elements", e);
            throw new DAOException("Can not parse elements", e);
        }
        return elements;
    }

    protected abstract void setInsertElementProperties(PreparedStatement statement, E element) throws SQLException;

    protected abstract void setUpdateElementProperties(PreparedStatement statement, E element) throws SQLException;

    protected abstract E parseToOneElement(ResultSet resultSet) throws SQLException;
}

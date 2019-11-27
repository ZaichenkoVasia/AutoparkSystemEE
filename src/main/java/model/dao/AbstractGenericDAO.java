package model.dao;

import model.dao.connection.PoolConnection;
import model.dao.constants.Constants;
import model.exception.DatabaseRuntimeException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractGenericDAO<E> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractGenericDAO.class);

    protected final PoolConnection connector;

    public AbstractGenericDAO(PoolConnection connector) {
        this.connector = connector;
    }

    protected Integer insert(E element, String query) {
        LOGGER.info("Inserting element");
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            setInsertProperties(statement, element);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                LOGGER.info("Element created. Returning it to service layer");
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.error("Can not insert element", e);
            throw new DatabaseRuntimeException("Can not insert element", e);
        }
        return null;
    }

    protected E findByIntegerParam(Integer data, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, data);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOne(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Can not get element", e);
            throw new DatabaseRuntimeException("Can not get element", e);
        }
        return null;
    }

    protected E findByStringParam(String data, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, data);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOne(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error("Can not get element", e);
            throw new DatabaseRuntimeException("Can not get element", e);
        }
        return null;
    }

    protected void updateFieldByIntegerParam(Integer dataInt, String data, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, data);
            preparedStatement.setInt(2, dataInt);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not update element", e);
            throw new DatabaseRuntimeException("Can not update element", e);
        }
    }

    protected void updateByIntegerParam(Integer dataInt, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dataInt);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not update element", e);
            throw new DatabaseRuntimeException("Can not update element", e);
        }
    }

    protected void update(E entity, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            setUpdateProperties(preparedStatement, entity);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not update element", e);
            throw new DatabaseRuntimeException("Can not update element", e);
        }
    }

    protected void delete(int dataInt, String query) {
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dataInt);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can not delete element", e);
            throw new DatabaseRuntimeException("Can not delete element", e);
        }
        LOGGER.info("Element was deleted from database");
    }

    protected Integer findCount(String query) {
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
            throw new DatabaseRuntimeException("Can not get count elements", e);
        }
        return null;
    }

    protected Integer findCountByIntegerParam(Integer dataInt, String query) {
        LOGGER.info("Counting");
        ResultSet resultSet = null;
        Integer res = null;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dataInt);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt(Constants.ELEMENTS_COUNT);
            }
        } catch (SQLException e) {
            LOGGER.error("Can not get count elements", e);
            throw new DatabaseRuntimeException("Can not get count elements", e);
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
            list = parseAll(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Can not get paganation list", e);
            throw new DatabaseRuntimeException("Can not get paganation list", e);
        }
        return list;
    }

    protected List<E> findListByIntegerParam(Integer dataInt, String query) {
        LOGGER.info("Getting");
        ResultSet resultSet = null;
        List<E> list;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, dataInt);
            resultSet = statement.executeQuery();
            list = parseAll(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Can not get list element", e);
            throw new DatabaseRuntimeException("Can not get list element", e);
        }
        return list;
    }

    protected List<E> findListByStringParam(String data, String query) {
        LOGGER.info("Getting");
        ResultSet resultSet = null;
        List<E> list;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, data);
            resultSet = statement.executeQuery();
            list = parseAll(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Can not get list element", e);
            throw new DatabaseRuntimeException("Can not get list element", e);
        }
        LOGGER.info("Returning list buses assigned to route");
        return list;
    }

    protected List<E> parseAll(ResultSet resultSet) {
        List<E> elements = new ArrayList<>();
        try {
            while (resultSet.next()) {
                elements.add(parseToOne(resultSet));
            }
        } catch (SQLException e) {
            LOGGER.error("Can not parse elements", e);
            throw new DatabaseRuntimeException("Can not parse elements", e);
        }
        return elements;
    }

    protected abstract void setInsertProperties(PreparedStatement statement, E element) throws SQLException;

    protected abstract void setUpdateProperties(PreparedStatement statement, E element) throws SQLException;

    protected abstract E parseToOne(ResultSet resultSet) throws SQLException;
}

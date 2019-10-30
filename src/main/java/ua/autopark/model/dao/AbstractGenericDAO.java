package ua.autopark.model.dao;

import org.apache.log4j.Logger;
import ua.autopark.model.dao.connection.ConnectionFactory;
import ua.autopark.model.exception.DAORuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * General methods for base operations such as insert, update, delete, create
 * */
public abstract class AbstractGenericDAO<E>{

    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(AbstractGenericDAO.class);

    public AbstractGenericDAO(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Integer insertElement(E element, String query){
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            setInsertElementProperties(statement, element);
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                logger.info("Element created. Returning it to service layer");
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.error("Error");
            throw new DAORuntimeException();
        }
        return null;
    }

    public void updateElement(E element, String querry) {
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(querry)) {
            setUpdateElementProperties(statement, element);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
    }

    public E getElementById(int id, String querry) throws DAORuntimeException {
        try (Connection connection = connectionFactory.getConnection();
        PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        return null;
    }

    public void deleteElement(int id, String querry) throws DAORuntimeException {
        logger.info("Deleting element");
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(querry)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Element was deleted from database");
    }

    protected List<E> parseAllElements(ResultSet resultSet) throws SQLException {
        List<E> elements = new ArrayList<>();
        while (resultSet.next()) {
            elements.add(parseToOneElement(resultSet));
        }
        return elements;
    }

    protected abstract void setInsertElementProperties(PreparedStatement statement, E element) throws SQLException;

    protected abstract void setUpdateElementProperties(PreparedStatement statement, E element) throws SQLException;

    protected abstract E parseToOneElement(ResultSet resultSet) throws SQLException;
}

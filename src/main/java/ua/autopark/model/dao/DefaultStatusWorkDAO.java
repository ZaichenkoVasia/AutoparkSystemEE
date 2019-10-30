package ua.autopark.model.dao;

import org.apache.log4j.Logger;
import ua.autopark.model.domain.enums.Status;
import ua.autopark.model.exception.DAORuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DefaultStatusWorkDAO {
    Logger logger = Logger.getLogger(DefaultStatusWorkDAO.class);

    default void setStatusFree(int id, Connection connection, String query){
        logger.info("Setting status new for driver");
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, Status.FREE.toString());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Status accepted");
    }

    default void setStatusWork(int id, Connection connection, String query){
        logger.info("Setting status work");
        try(PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, Status.WORK.toString());
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Status accepted");
    }
}

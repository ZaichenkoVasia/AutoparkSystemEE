package ua.autopark.model.dao.impl;

import org.apache.log4j.Logger;
import ua.autopark.model.dao.AbstractGenericDAO;
import ua.autopark.model.dao.BusDAO;
import ua.autopark.model.dao.DefaultStatusWorkDAO;
import ua.autopark.model.dao.connection.ConnectionFactory;
import ua.autopark.model.domain.Bus;
import ua.autopark.model.domain.Route;
import ua.autopark.model.domain.enums.Status;
import ua.autopark.model.exception.DAORuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BusDAOImpl extends AbstractGenericDAO<Bus> implements BusDAO, DefaultStatusWorkDAO {
    private static final String FIND_COUNT_BUS_BY_IDROUTE = "SELECT COUNT(*) FROM bus WHERE idroute = ?";
    private static final String FIND_BUS_BY_IDROUTE = "SELECT * FROM bus WHERE bus.idroute = ?";
    private static final String FIND_BUS_BY_STATUS = "SELECT * FROM bus WHERE bus.status = ?";
    private static final String CANSEL_BUS_BY_ID = "UPDATE bus, driver SET bus.idroute = null, bus.status = ?, driver.status = ? WHERE bus.idbus = driver.idbus AND bus.idbus = ?";
    private static final String BUS_APPOINT_TO_ROUTE = "UPDATE bus, driver SET bus.status = ?, driver.status = ?, bus.idroute = ? WHERE bus.idbus = driver.idbus AND bus.idbus = ?";
    private static final String UPDATE_BUS_STATUS = "SELECT * FROM bus WHERE bus.status = ?";
    private static final String INSERT_BUS = "INSERT INTO bus (model, seats, status idschedule) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_BUS = "UPDATE bus SET plate = ?, model = ?, seats = ?, idschedule = ? WHERE idbus = ?";
    private static final String DELETE_BUS_BY_ID = "DELETE FROM bus WHERE idbus = ?";
    private static final String FIND_BUS_BY_ID = "SELECT * FROM bus WHERE bus.idbus = ?";

    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(BusDAOImpl.class);

    public BusDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected Bus parseToOneElement(ResultSet resultSet) throws SQLException {
        return Bus.builder()
                .withId(resultSet.getLong("bus.idbus"))
                .withModel(resultSet.getString("bus.model"))
                .withSeats(resultSet.getInt("bus.seats"))
                .withStatus(Status.valueOf(resultSet.getString("bus.status")))
                .withRoute(Route.builder()
                        .withId(resultSet.getLong("bus.idroute"))
                        .build())
                .build();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Bus element) throws SQLException {
        statement.setInt(1, element.getSeats());
        statement.setString(2, element.getStatus().toString());
        statement.setLong(3, element.getRoute().getId());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Bus element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setLong(4, element.getId());
    }

    @Override
    public Integer countBusesOnRouteById(int idRoute) {
        logger.info("Counting buses assigned to route");
        ResultSet resultSet = null;
        Integer res = null;
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_COUNT_BUS_BY_IDROUTE)) {
            statement.setInt(1, idRoute);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                res = resultSet.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Quantity counted buses is " + res);
        return res;
    }

    @Override
    public List<Bus> getBusesByIdRoute(int idRoute) {
        logger.info("Getting buses assigned to route");
        ResultSet resultSet = null;
        List<Bus> list;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BUS_BY_IDROUTE)) {
            statement.setInt(1, idRoute);
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Returning list buses assigned to route");
        return list;
    }

    @Override
    public List<Bus> getFreeBuses() {
        logger.info("Getting free buses");
        ResultSet resultSet = null;
        List<Bus> list;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BUS_BY_STATUS)) {
            statement.setString(1, Status.FREE.toString());
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Returning list of free buses");
        return list;
    }

    @Override
    public void cancelBusFromRoute(int idBus) {
        logger.info("Canceling bus from route");
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(CANSEL_BUS_BY_ID)) {
            statement.setString(1, Status.FREE.toString());
            statement.setString(2, Status.FREE.toString());
            statement.setInt(3, idBus);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Bus canceled");
    }

    @Override
    public void appointBusToRoute(int idRoute, int idBus){
        logger.info("Assigning bus to route");
        try (Connection connection = connectionFactory.getConnection();
                PreparedStatement statement = connection.prepareStatement(BUS_APPOINT_TO_ROUTE)) {
            statement.setString(1, Status.FREE.toString());
            statement.setString(2, Status.FREE.toString());
            statement.setInt(3, idRoute);
            statement.setInt(4, idBus);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Bus assigned to route");
    }

    @Override
    public Integer insertElement(Bus element) {
        return insertElement(element, INSERT_BUS);
    }

    @Override
    public Bus getElementById(int id) {
        return getElementById(id, FIND_BUS_BY_ID);
    }

    @Override
    public void deleteElement(int id) {
        deleteElement(id, DELETE_BUS_BY_ID);

    }

    @Override
    public void updateElement(Bus element) {
        updateElement(element, UPDATE_BUS);
    }

    public void setStatusFree(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            DefaultStatusWorkDAO.super.setStatusFree(id, connection, UPDATE_BUS_STATUS);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Set status free");
    }

    public void setStatusWork(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            DefaultStatusWorkDAO.super.setStatusWork(id, connection, UPDATE_BUS_STATUS);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Set status free");

    }
}


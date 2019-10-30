package ua.autopark.model.dao.impl;


import org.apache.log4j.Logger;
import ua.autopark.model.dao.AbstractGenericDAO;
import ua.autopark.model.dao.DefaultStatusWorkDAO;
import ua.autopark.model.dao.DriverDAO;
import ua.autopark.model.dao.connection.ConnectionFactory;
import ua.autopark.model.domain.Bus;
import ua.autopark.model.domain.Driver;
import ua.autopark.model.domain.enums.Status;
import ua.autopark.model.exception.DAORuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DriverDAOImpl extends AbstractGenericDAO<Driver> implements DriverDAO, DefaultStatusWorkDAO {
    private static final String CANSEL_DRIVER_FROM_BUS = "UPDATE driver SET driver.idbus = null, driver.status = ? WHERE driver.idbus = ?";
    private static final String FIND_DRIVER_BY_STATUS = "SELECT * FROM driver JOIN user ON driver.iduser = user.iduser WHERE driver.status = ?";
    private static final String FIND_DRIVER_BY_BUS_ID = "SELECT * FROM driver JOIN user ON driver.iduser = user.iduser WHERE driver.idbus = ?";
    private static final String UPDATE_DRIVER_STATUS = "UPDATE driver SET status = ? WHERE iddriver = ?";
    private static final String INSERT_DRIVER = "INSERT INTO driver (name, surname, birth, license_test, salary,iduser) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_DRIVER = "UPDATE driver SET name = ?, surname = ?, birth = ?, license_test = ?, salary = ?, iduser = ? WHERE iddriver = ?";
    private static final String DELETE_DRIVER_BY_ID = "DELETE FROM driver WHERE iddriver = ?";
    private static final String FIND_DRIVER_BY_ID = "SELECT * FROM driver JOIN user ON driver.iduser = user.iduser WHERE driver.iddriver = ?";

    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(DriverDAOImpl.class);

    public DriverDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected Driver parseToOneElement(ResultSet resultSet) throws SQLException {
        return Driver.builder()
                .withIdDriver(resultSet.getLong("driver.iddriver"))
                .withBus(Bus.builder()
                        .withId(resultSet.getLong("driver.idbus"))
                        .build())
                .withSalary(resultSet.getInt("driver.salary"))
                .withStatus(Status.valueOf(resultSet.getString("driver.status")))
                .withId(resultSet.getLong("user.iduser"))
                .withLogin(resultSet.getString("user.login"))
                .withPassword(resultSet.getString("user.password"))
                .withName(resultSet.getString("user.name"))
                .withSurname(resultSet.getString("user.surname"))
                .withBirth(resultSet.getDate("user.birth"))
                .build();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Driver element) throws SQLException {
        statement.setString(1, element.getName());
        statement.setString(2, element.getSurname());
        statement.setDate(3, element.getBirth());
        statement.setDouble(5, element.getSalary());
        statement.setLong(6, element.getId());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Driver element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setLong(7, element.getId());
    }

    @Override
    public Driver getDriverByBusId(int idBus) {
        logger.info("Getting driver by bus id. idBus = " + idBus);
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DRIVER_BY_BUS_ID)) {
            statement.setInt(1, idBus);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            logger.error(ExceptionMessages.CAN_NOT_GET_DRIVER_BY_BUS_ID, e);
            throw new DAORuntimeException(ExceptionMessages.CAN_NOT_GET_DRIVER_BY_BUS_ID, e);
        }
        return null;
    }

    @Override
    public void cancelDriverFromBus(int idBus, Connection connection) {
        logger.info("Canceling driver from bus");
        try (PreparedStatement statement = connection.prepareStatement(CANSEL_DRIVER_FROM_BUS)) {
            statement.setString(1, Status.FREE.toString());
            statement.setInt(2, idBus);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Driver canceled");
    }

    @Override
    public List<Driver> getFreeDrivers() {
        logger.info("Getting free drivers");
        ResultSet resultSet = null;
        List<Driver> list;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_DRIVER_BY_STATUS)) {
            statement.setString(1, Status.FREE.toString());
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Returning list of free drivers");
        return list;
    }

    @Override
    public Integer insertElement(Driver element) {
        return insertElement(element, INSERT_DRIVER);
    }

    @Override
    public Driver getElementById(int id) {
        return getElementById(id, FIND_DRIVER_BY_ID);
    }

    @Override
    public void deleteElement(int id) {
        deleteElement(id, DELETE_DRIVER_BY_ID);

    }

    @Override
    public void updateElement(Driver element) {
        updateElement(element, UPDATE_DRIVER);
    }

    public void setStatusFree(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            DefaultStatusWorkDAO.super.setStatusFree(id, connection, UPDATE_DRIVER_STATUS);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Set status free");
    }

    public void setStatusWork(int id) {
        try (Connection connection = connectionFactory.getConnection()) {
            DefaultStatusWorkDAO.super.setStatusWork(id, connection, UPDATE_DRIVER_STATUS);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Set status free");

    }
}

package model.dao.impl;

import domain.Bus;
import domain.Driver;
import domain.User;
import model.dao.AbstractGenericDAO;
import model.dao.DriverDAO;
import model.dao.connection.PoolConection;
import model.dao.constants.Constants;
import model.dao.constants.ExceptionMessages;
import model.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DriverDAOImpl extends AbstractGenericDAO<Driver> implements DriverDAO {

    private static final String COUNT = "SELECT COUNT(*) FROM driver";
    private static final String FIND_ALL = "SELECT * FROM driver JOIN user ON driver.iduser = user.iduser LIMIT ?,?";
    private static final String FIND_BY_ID = "SELECT * FROM driver JOIN user ON driver.iduser = user.iduser WHERE driver.iddriver = ?";
    private static final String FIND_BY_STATUS = "SELECT * FROM driver JOIN user ON driver.iduser = user.iduser WHERE driver.status = ?";
    private static final String FIND_BY_USER_ID = "SELECT * FROM driver JOIN user ON driver.iduser = user.iduser WHERE user.iduser = ?";
    private static final String FIND_BY_BUS_ID = "SELECT * FROM driver JOIN user ON driver.iduser = user.iduser WHERE driver.idbus = ?";
    private static final String CANSEL_DRIVER = "UPDATE driver SET driver.idbus = null, driver.status = ? WHERE driver.idbus = ?";
    private static final String DELETE = "DELETE FROM driver WHERE iddriver = ?";
    private static final String INSERT = "INSERT INTO driver (name, surname, birth, license_test, salary,iduser) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BUS_BY_ID = "UPDATE driver SET idbus = ? WHERE iddriver = ?";
    private static final String UPDATE_STATUS_BY_ID = "UPDATE driver SET status = ? WHERE iddriver = ?";
    private static final String UPDATE = "UPDATE driver SET name = ?, surname = ?, birth = ?, license_test = ?, salary = ?, iduser = ? WHERE iddriver = ?";

    public DriverDAOImpl(PoolConection poolConection) {
        super(poolConection);
    }

    @Override
    protected Driver parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Driver.DriverBuilder()
                .setId(resultSet.getInt("driver.iddriver"))
                .setName(resultSet.getString("driver.name"))
                .setSurname(resultSet.getString("driver.surname"))
                .setBirth(resultSet.getDate("driver.birth"))
                .setLicenseTest(resultSet.getDate("driver.license_test"))
                .setSalary(resultSet.getInt("driver.salary"))
                .setStatus(resultSet.getString("driver.status"))
                .setBus(new Bus.BusBuilder()
                        .setId(resultSet.getInt("driver.idbus"))
                        .createBus())
                .setUser(new User.UserBuilder()
                        .setId(resultSet.getInt("user.iduser"))
                        .setLogin(resultSet.getString("user.login"))
                        .setPassword(resultSet.getString("user.password"))
                        .createUser())
                .createDriver();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Driver element) throws SQLException {
        statement.setString(1, element.getName());
        statement.setString(2, element.getSurname());
        statement.setDate(3, element.getBirth());
        statement.setDate(4, element.getLicenseTest());
        statement.setDouble(5, element.getSalary());
        statement.setInt(6, element.getUser().getId());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Driver element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(7, element.getId());
    }

    @Override
    public Driver getDriverByUserId(Integer idUser) {
        return super.getElementByIntegerParam(idUser, FIND_BY_USER_ID);
    }

    @Override
    public Driver getDriverByBusId(Integer idBus) {
        return super.getElementByIntegerParam(idBus, FIND_BY_BUS_ID);
    }

    @Override
    public void setStatusNew(Integer idDriver) {
        updateElementFieldByIntegerParam(idDriver, Constants.STATUS_NEW, UPDATE_STATUS_BY_ID);
    }

    @Override
    public void setStatusWork(Integer idDriver) {
        updateElementFieldByIntegerParam(idDriver, Constants.STATUS_WORK, UPDATE_STATUS_BY_ID);
    }

    @Override
    public void cancelDriverFromBus(Integer idBus) {
        updateElementFieldByIntegerParam(idBus, Constants.STATUS_WORK, CANSEL_DRIVER);
    }

    @Override
    public List<Driver> getFreeDrivers() {
        return super.getListByStringParam(Constants.STATUS_FREE, FIND_BY_STATUS);
    }


    @Override
    public Integer insertElement(Driver element) {
        return super.insert(element, INSERT);
    }

    @Override
    public Driver getElementById(Integer id) {
        return super.getElementByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer id) {
        super.deleteElement(id, DELETE);
    }

    @Override
    public void updateElement(Driver element) {
        super.updateElement(element, UPDATE);
    }

    @Override
    public Integer getElementsCount() {
        return super.getElementCount(COUNT);
    }

    @Override
    public List<Driver> getPaginatedList(int startIdx, int amountElements) {
        return super.getPaginatedList(startIdx, amountElements, FIND_ALL);
    }

    @Override
    public void updateBusInfoForDriver(Integer idBus, Integer idDriver) {
        LOGGER.info("Updating bus info for driver. Assigning new bus.");
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_BUS_BY_ID)) {
            statement.setInt(1, idBus);
            statement.setInt(2, idDriver);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(ExceptionMessages.CAN_NOT_UPDATE_BUS_INFO_FOR_DRIVER, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_UPDATE_BUS_INFO_FOR_DRIVER, e);
        }
        LOGGER.info("New bus assigned");
    }
}

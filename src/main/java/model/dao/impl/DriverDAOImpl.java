package model.dao.impl;

import domain.Bus;
import domain.Driver;
import domain.User;
import model.dao.AbstractGenericDAO;
import model.dao.DriverDAO;
import model.dao.connection.PoolConection;
import model.dao.constants.Constants;
import model.entity.BusEntity;
import model.entity.DriverEntity;
import model.entity.UserEntity;
import model.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DriverDAOImpl extends AbstractGenericDAO<DriverEntity> implements DriverDAO {

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
    protected DriverEntity parseToOne(ResultSet resultSet) throws SQLException {
        return DriverEntity.builder()
                .withId(resultSet.getInt("driver.iddriver"))
                .withName(resultSet.getString("driver.name"))
                .withSurname(resultSet.getString("driver.surname"))
                .withBirth(resultSet.getDate("driver.birth"))
                .withLicenseTest(resultSet.getDate("driver.license_test"))
                .withSalary(resultSet.getInt("driver.salary"))
                .withStatus(resultSet.getString("driver.status"))
                .withBus(BusEntity.builder()
                        .withId(resultSet.getInt("driver.idbus"))
                        .build())
                .withUser(UserEntity.builder()
                        .withId(resultSet.getInt("user.iduser"))
                        .withLogin(resultSet.getString("user.login"))
                        .withPassword(resultSet.getString("user.password"))
                        .build())
                .build();
    }

    @Override
    protected void setInsertProperties(PreparedStatement statement, DriverEntity element) throws SQLException {
        statement.setString(1, element.getName());
        statement.setString(2, element.getSurname());
        statement.setDate(3, element.getBirth());
        statement.setDate(4, element.getLicenseTest());
        statement.setDouble(5, element.getSalary());
        statement.setInt(6, element.getUser().getId());
    }

    @Override
    protected void setUpdateProperties(PreparedStatement statement, DriverEntity element) throws SQLException {
        setInsertProperties(statement, element);
        statement.setInt(7, element.getId());
    }

    @Override
    public DriverEntity getDriverByUserId(Integer idUser) {
        return super.getByIntegerParam(idUser, FIND_BY_USER_ID);
    }

    @Override
    public DriverEntity getDriverByBusId(Integer idBus) {
        return super.getByIntegerParam(idBus, FIND_BY_BUS_ID);
    }

    @Override
    public void setStatusNew(Integer idDriver) {
        updateFieldByIntegerParam(idDriver, Constants.STATUS_NEW, UPDATE_STATUS_BY_ID);
    }

    @Override
    public void setStatusWork(Integer idDriver) {
        updateFieldByIntegerParam(idDriver, Constants.STATUS_WORK, UPDATE_STATUS_BY_ID);
    }

    @Override
    public void cancelDriverFromBus(Integer idBus) {
        updateFieldByIntegerParam(idBus, Constants.STATUS_NEW, CANSEL_DRIVER);
    }

    @Override
    public List<DriverEntity> getFreeDrivers() {
        return super.getListByStringParam(Constants.STATUS_FREE, FIND_BY_STATUS);
    }


    @Override
    public Integer insertElement(DriverEntity element) {
        return super.insert(element, INSERT);
    }

    @Override
    public DriverEntity getElementById(Integer id) {
        return super.getByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer id) {
        super.delete(id, DELETE);
    }

    @Override
    public void updateElement(DriverEntity element) {
        super.update(element, UPDATE);
    }

    @Override
    public Integer getElementsCount() {
        return super.getCount(COUNT);
    }

    @Override
    public List<DriverEntity> getPaginatedList(int startIdx, int amountElements) {
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
            LOGGER.error("Can't execute method updateBusInfoForDriver", e);
            throw new DAOException("Can't execute method updateBusInfoForDriver", e);
        }
        LOGGER.info("New bus assigned");
    }
}

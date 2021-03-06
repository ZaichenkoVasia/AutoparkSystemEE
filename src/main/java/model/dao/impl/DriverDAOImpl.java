package model.dao.impl;

import model.dao.AbstractGenericDAO;
import model.dao.DriverDAO;
import model.dao.connection.PoolConnection;
import model.dao.constants.Constants;
import model.entity.BusEntity;
import model.entity.DriverEntity;
import model.entity.UserEntity;
import model.exception.DatabaseRuntimeException;

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

    public DriverDAOImpl(PoolConnection poolConnection) {
        super(poolConnection);
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
    public DriverEntity findDriverByUserId(Integer idUser) {
        return findByIntegerParam(idUser, FIND_BY_USER_ID);
    }

    @Override
    public DriverEntity findDriverByBusId(Integer idBus) {
        return findByIntegerParam(idBus, FIND_BY_BUS_ID);
    }

    @Override
    public void setStatusNew(Integer idDriver) {
        updateFieldByIntegerParam(idDriver, Constants.STATUS_FREE, UPDATE_STATUS_BY_ID);
    }

    @Override
    public void setStatusWork(Integer idDriver) {
        updateFieldByIntegerParam(idDriver, Constants.STATUS_WORK, UPDATE_STATUS_BY_ID);
    }

    @Override
    public void cancelDriverFromBus(Integer idBus) {
        updateFieldByIntegerParam(idBus, Constants.STATUS_FREE, CANSEL_DRIVER);
    }

    @Override
    public List<DriverEntity> getFreeDrivers() {
        return super.findListByStringParam(Constants.STATUS_FREE, FIND_BY_STATUS);
    }


    @Override
    public Integer insertElement(DriverEntity element) {
        return insert(element, INSERT);
    }

    @Override
    public DriverEntity findElementById(Integer id) {
        return findByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer id) {
        delete(id, DELETE);
    }

    @Override
    public void updateElement(DriverEntity element) {
        update(element, UPDATE);
    }

    @Override
    public Integer count() {
        return findCount(COUNT);
    }

    @Override
    public List<DriverEntity> findPaginatedList(int startIdx, int amountElements) {
        return findPaginatedList(startIdx, amountElements, FIND_ALL);
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
            LOGGER.warn("Can't execute method updateBusInfoForDriver", e);
            throw new DatabaseRuntimeException("Can't execute method updateBusInfoForDriver", e);
        }
        LOGGER.info("New bus assigned");
    }
}

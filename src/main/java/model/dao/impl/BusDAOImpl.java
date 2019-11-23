package model.dao.impl;

import model.dao.AbstractGenericDAO;
import model.dao.BusDAO;
import model.dao.connection.PoolConection;
import model.dao.constants.Constants;
import model.entity.BusEntity;
import model.entity.RouteEntity;
import model.entity.ScheduleEntity;
import model.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BusDAOImpl extends AbstractGenericDAO<BusEntity> implements BusDAO {
    private static final String COUNT_ALL = "SELECT COUNT(*) FROM bus";
    private static final String COUNT_BY_ROUTE = "SELECT COUNT(*) FROM bus WHERE idroute = ?";
    private static final String FIND_ALL = "SELECT * FROM bus JOIN schedule WHERE bus.idschedule = schedule.idschedule LIMIT ?,?";
    private static final String FIND_BY_ROUTE = "SELECT * FROM bus JOIN schedule ON bus.idschedule = schedule.idschedule WHERE bus.idroute = ?";
    private static final String FIND_BY_ID = "SELECT * FROM bus JOIN schedule ON bus.idschedule = schedule.idschedule WHERE bus.idbus = ?";
    private static final String FIND_BY_STATUS = "SELECT * FROM bus JOIN schedule ON bus.idschedule = schedule.idschedule WHERE bus.status = ?";
    private static final String INSERT = "INSERT INTO bus (plate, model, mileage, inspection, consumption, release_date, seats, idschedule) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE bus SET plate = ?, model = ?, mileage = ?, inspection = ?, consumption = ?, release_date = ?, seats = ?, idschedule = ? WHERE idbus = ?";
    private static final String CANSEL_BUS = "UPDATE bus, driver SET bus.idroute = null, bus.status = ?, driver.status = ? WHERE bus.idbus = driver.idbus AND bus.idbus = ?";
    private static final String DELETE = "DELETE FROM bus WHERE idbus = ?";
    private static final String APPOINT_TO_ROUTE = "UPDATE bus, driver SET bus.status = ?, driver.status = ?, bus.idroute = ? WHERE bus.idbus = driver.idbus AND bus.idbus = ?";


    public BusDAOImpl(PoolConection poolConection) {
        super(poolConection);
    }

    @Override
    protected BusEntity parseToOne(ResultSet resultSet) throws SQLException {
        return BusEntity.builder()
                .withId(resultSet.getInt("bus.idbus"))
                .withPlate(resultSet.getString("bus.plate"))
                .withModel(resultSet.getString("bus.model"))
                .withMileage(resultSet.getInt("bus.mileage"))
                .withInspection(resultSet.getDate("bus.inspection"))
                .withConsumption(resultSet.getInt("bus.consumption"))
                .withRelease(resultSet.getDate("bus.release_date"))
                .withSeats(resultSet.getInt("bus.seats"))
                .withStatus(resultSet.getString("bus.status"))
                .withRoute(RouteEntity.builder()
                        .withId(resultSet.getInt("idroute"))
                        .build())
                .withSchedule(ScheduleEntity.builder()
                        .withId(resultSet.getInt("schedule.idschedule"))
                        .withDeparture(resultSet.getString("schedule.departure"))
                        .withArrival(resultSet.getString("schedule.arrival"))
                        .build())
                .build();
    }

    @Override
    protected void setInsertProperties(PreparedStatement statement, BusEntity element) throws SQLException {
        statement.setString(1, element.getPlate());
        statement.setString(2, element.getModel());
        statement.setInt(3, element.getMileage());
        statement.setDate(4, element.getInspection());
        statement.setInt(5, element.getConsumption());
        statement.setDate(6, element.getRelease());
        statement.setInt(7, element.getSeats());
        statement.setInt(8, element.getSchedule().getId());
    }

    @Override
    protected void setUpdateProperties(PreparedStatement statement, BusEntity element) throws SQLException {
        setInsertProperties(statement, element);
        statement.setInt(9, element.getId());
    }

    @Override
    public Integer countBusesOnRouteById(Integer idRoute) {
        return getCountByIntegerParam(idRoute, COUNT_BY_ROUTE);
    }

    @Override
    public List<BusEntity> getBusesByIdRoute(Integer idRoute) {
        return getListByIntegerParam(idRoute, FIND_BY_ROUTE);
    }

    @Override
    public List<BusEntity> getFreeBuses() {
        return super.getListByStringParam(Constants.STATUS_FREE, FIND_BY_STATUS);
    }

    @Override
    public Integer insertElement(BusEntity element) {
        return super.insert(element, INSERT);
    }

    @Override
    public BusEntity getElementById(Integer id) {
        return getByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer id) {
        super.delete(id, DELETE);
    }

    @Override
    public void updateElement(BusEntity element) {
        super.update(element, UPDATE);
    }

    @Override
    public Integer getElementsCount() {
        return super.getCount(COUNT_ALL);
    }

    @Override
    public List<BusEntity> getPaginatedList(int startIdx, int amountElements) {
        return super.getPaginatedList(startIdx, amountElements, FIND_ALL);
    }

    @Override
    public void cancelBusFromRoute(Integer idBus) {
        LOGGER.info("Canceling bus from route");
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(CANSEL_BUS)) {
            statement.setString(1, Constants.STATUS_FREE);
            statement.setString(2, Constants.STATUS_FREE);
            statement.setInt(3, idBus);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't execute method cancelBusFromRoute", e);
            throw new DAOException("Can't execute method cancelBusFromRoute", e);
        }
        LOGGER.info("Bus canceled");
    }

    @Override
    public void appointBusToRoute(Integer idRoute, Integer idBus) {
        LOGGER.info("Assigning bus to route");
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(APPOINT_TO_ROUTE)) {
            statement.setString(1, Constants.STATUS_WORK);
            statement.setString(2, Constants.STATUS_NEW);
            statement.setInt(3, idRoute);
            statement.setInt(4, idBus);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Can't execute method appointBusToRoute", e);
            throw new DAOException("Can't execute method appointBusToRoute", e);
        }
        LOGGER.info("Bus assigned to route");
    }

}

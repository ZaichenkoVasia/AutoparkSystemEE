package model.dao.impl;

import domain.Bus;
import domain.Route;
import domain.Schedule;
import model.dao.AbstractGenericDAO;
import model.dao.BusDAO;
import model.dao.connection.PoolConection;
import model.dao.constants.Constants;
import model.dao.constants.ExceptionMessages;
import model.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BusDAOImpl extends AbstractGenericDAO<Bus> implements BusDAO {
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


    public BusDAOImpl(PoolConection poolConection){
        super(poolConection);
    }

    @Override
    protected Bus parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Bus.BusBuilder()
                .setId(resultSet.getInt("bus.idbus"))
                .setPlate(resultSet.getString("bus.plate"))
                .setModel(resultSet.getString("bus.model"))
                .setMileage(resultSet.getInt("bus.mileage"))
                .setInspection(resultSet.getDate("bus.inspection"))
                .setConsumption(resultSet.getInt("bus.consumption"))
                .setRelease(resultSet.getDate("bus.release_date"))
                .setSeats(resultSet.getInt("bus.seats"))
                .setStatus(resultSet.getString("bus.status"))
                .setRoute(new Route.RouteBuilder()
                        .setId(resultSet.getInt("idroute"))
                        .createRoute())
                .setSchedule(new Schedule.ScheduleBuilder()
                        .setId(resultSet.getInt("schedule.idschedule"))
                        .setDeparture(resultSet.getString("schedule.departure"))
                        .setArrival(resultSet.getString("schedule.arrival"))
                        .createSchedule())
                .createBus();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Bus element) throws SQLException {
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
    protected void setUpdateElementProperties(PreparedStatement statement, Bus element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(9, element.getId());
    }

    @Override
    public Integer countBusesOnRouteById(Integer idRoute)  {
        return getElementCountByIntegerParam(idRoute, COUNT_BY_ROUTE);
    }

    @Override
    public List<Bus> getBusesByIdRoute(Integer idRoute) {
        return getListByIntegerParam(idRoute, FIND_BY_ROUTE);
    }

    @Override
    public List<Bus> getFreeBuses()  {
        return super.getListByStringParam(Constants.STATUS_FREE, FIND_BY_STATUS);
    }

    @Override
    public Integer insertElement(Bus element){
        return super.insert(element, INSERT);
    }

    @Override
    public Bus getElementById(Integer id) {
        return getElementByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer id) {
        super.deleteElement(id, DELETE);
    }

    @Override
    public void updateElement(Bus element) {
        super.updateElement(element, UPDATE);
    }

    @Override
    public Integer getElementsCount() {
        return super.getElementCount(COUNT_ALL);
    }

    @Override
    public List<Bus> getPaginatedList(int startIdx, int amountElements) {
        return super.getPaginatedList(startIdx, amountElements, FIND_ALL);
    }

    @Override
    public void cancelBusFromRoute(Integer idBus)  {
        LOGGER.info("Canceling bus from route");
        try (Connection connection = connector.getConnection();
                PreparedStatement statement = connection.prepareStatement(CANSEL_BUS)) {
            statement.setString(1, Constants.STATUS_FREE);
            statement.setString(2, Constants.STATUS_FREE);
            statement.setInt(3, idBus);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(ExceptionMessages.CAN_NOT_CANCEL_BUS_FROM_ROUTE, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_CANCEL_BUS_FROM_ROUTE, e);
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
            LOGGER.error(ExceptionMessages.CAN_NOT_APPOINT_BUS_TO_ROUTE, e);
            throw new DAOException(ExceptionMessages.CAN_NOT_APPOINT_BUS_TO_ROUTE, e);
        }
        LOGGER.info("Bus assigned to route");
    }

}

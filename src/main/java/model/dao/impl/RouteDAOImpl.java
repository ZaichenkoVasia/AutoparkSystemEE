package model.dao.impl;

import domain.Route;
import model.dao.AbstractGenericDAO;
import model.dao.RouteDAO;
import model.dao.connection.PoolConection;
import model.dao.constants.Constants;
import model.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RouteDAOImpl extends AbstractGenericDAO<Route> implements RouteDAO {
    private static final String COUNT = "SELECT COUNT(*) FROM route";
    private static final String FIND_ALL = "SELECT * FROM route LIMIT ?,?";
    private static final String FIND_BY_ID = "SELECT * FROM route WHERE idroute = ?";
    private static final String FIND_BY_CRITERIA = "SELECT * FROM route WHERE departure LIKE ? AND arrival LIKE ?";
    private static final String INSERT = "INSERT INTO route (route_number, title, distance, departure, arrival) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE route SET route_number = ?, title = ?, distance = ?, departure = ?, arrival = ? WHERE idroute = ?";
    private static final String UPDATE_BY_STATUS = "UPDATE route SET status = ? WHERE idroute = ?";
    private static final String DELETE = "DELETE FROM route WHERE idroute = ?";
    private static final String CANSEL_ALL = "UPDATE route, bus, driver SET route.status = 'empty', bus.status = 'free', bus.idroute = null, driver.status = 'free' WHERE route.idroute = bus.idroute AND bus.idbus = driver.idbus AND route.idroute = ?";

    public RouteDAOImpl(PoolConection poolConection) {
        super(poolConection);
    }

    @Override
    protected Route parseToOne(ResultSet resultSet) throws SQLException {
        return Route.builder()
                .withId(resultSet.getInt("idroute"))
                .withNumber(resultSet.getString("route_number"))
                .withTitle(resultSet.getString("title"))
                .withDistance(resultSet.getInt("distance"))
                .withStatus(resultSet.getString("status"))
                .withDeparture(resultSet.getString("departure"))
                .withArrival(resultSet.getString("arrival"))
                .build();
    }

    @Override
    protected void setInsertProperties(PreparedStatement statement, Route element) throws SQLException {
        statement.setString(1, element.getNumber());
        statement.setString(2, element.getTitle());
        statement.setInt(3, element.getDistance());
        statement.setString(4, element.getDeparture());
        statement.setString(5, element.getArrival());
    }

    @Override
    protected void setUpdateProperties(PreparedStatement statement, Route element) throws SQLException {
        setInsertProperties(statement, element);
        statement.setInt(6, element.getId());
    }

    @Override
    public void setStatusEmpty(Integer idRoute) throws DAOException {
        super.updateFieldByIntegerParam(idRoute, Constants.STATUS_EMPTY, UPDATE_BY_STATUS);
    }

    @Override
    public void setStatusWork(Integer idRoute) throws DAOException {
        super.updateFieldByIntegerParam(idRoute, Constants.STATUS_WORK, UPDATE_BY_STATUS);
    }

    @Override
    public void cancelAll(Integer idRoute) throws DAOException {
        updateByIntegerParam(idRoute, CANSEL_ALL);
    }

    @Override
    public Integer insertElement(Route element) {
        return super.insert(element, INSERT);
    }

    @Override
    public Route getElementById(Integer id) {
        return super.getByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer id) {
        super.delete(id, DELETE);
    }

    @Override
    public void updateElement(Route element) {
        super.update(element, UPDATE);
    }

    @Override
    public Integer getElementsCount() {
        return getCount(COUNT);
    }

    @Override
    public List<Route> getPaginatedList(int startIdx, int amountElements) {
        return getPaginatedList(startIdx, amountElements, FIND_ALL);
    }

    @Override
    public List<Route> searchByCriteria(String departure, String arrival) throws DAOException {
        LOGGER.info("Searching by criteria");
        ResultSet resultSet = null;
        List<Route> list;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_CRITERIA)) {
            statement.setString(1, departure + Constants.LIKE);
            statement.setString(2, arrival + Constants.LIKE);
            resultSet = statement.executeQuery();
            list = parseAll(resultSet);
        } catch (SQLException e) {
            LOGGER.error("Can't execute method searchByCriteria", e);
            throw new DAOException("Can't execute method searchByCriteria", e);
        }
        LOGGER.info("Returning list of routes according to criteria");
        return list;
    }
}

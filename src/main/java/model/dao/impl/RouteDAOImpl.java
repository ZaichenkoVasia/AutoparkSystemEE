package model.dao.impl;

import model.dao.AbstractGenericDAO;
import model.dao.RouteDAO;
import model.dao.connection.PoolConnection;
import model.dao.constants.Constants;
import model.entity.RouteEntity;
import model.exception.DatabaseRuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RouteDAOImpl extends AbstractGenericDAO<RouteEntity> implements RouteDAO {
    private static final String COUNT = "SELECT COUNT(*) FROM route";
    private static final String FIND_ALL = "SELECT * FROM route LIMIT ?,?";
    private static final String FIND_BY_ID = "SELECT * FROM route WHERE idroute = ?";
    private static final String FIND_BY_CRITERIA = "SELECT * FROM route WHERE departure LIKE ? AND arrival LIKE ?";
    private static final String INSERT = "INSERT INTO route (route_number, title, distance, departure, arrival) VALUES(?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE route SET route_number = ?, title = ?, distance = ?, departure = ?, arrival = ? WHERE idroute = ?";
    private static final String UPDATE_BY_STATUS = "UPDATE route SET status = ? WHERE idroute = ?";
    private static final String DELETE = "DELETE FROM route WHERE idroute = ?";
    private static final String CANSEL_ALL = "UPDATE route, bus, driver SET route.status = 'empty', bus.status = 'free', bus.idroute = null, driver.status = 'free' WHERE route.idroute = bus.idroute AND bus.idbus = driver.idbus AND route.idroute = ?";

    public RouteDAOImpl(PoolConnection poolConnection) {
        super(poolConnection);
    }

    @Override
    protected RouteEntity parseToOne(ResultSet resultSet) throws SQLException {
        return RouteEntity.builder()
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
    protected void setInsertProperties(PreparedStatement statement, RouteEntity element) throws SQLException {
        statement.setString(1, element.getNumber());
        statement.setString(2, element.getTitle());
        statement.setInt(3, element.getDistance());
        statement.setString(4, element.getDeparture());
        statement.setString(5, element.getArrival());
    }

    @Override
    protected void setUpdateProperties(PreparedStatement statement, RouteEntity element) throws SQLException {
        setInsertProperties(statement, element);
        statement.setInt(6, element.getId());
    }

    @Override
    public void setStatusEmpty(Integer idRoute) {
        updateFieldByIntegerParam(idRoute, Constants.STATUS_EMPTY, UPDATE_BY_STATUS);
    }

    @Override
    public void setStatusWork(Integer idRoute) {
        updateFieldByIntegerParam(idRoute, Constants.STATUS_WORK, UPDATE_BY_STATUS);
    }

    @Override
    public void cancelAll(Integer idRoute) {
        updateByIntegerParam(idRoute, CANSEL_ALL);
    }

    @Override
    public Integer insertElement(RouteEntity element) {
        return insert(element, INSERT);
    }

    @Override
    public RouteEntity findElementById(Integer id) {
        return findByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer id) {
        delete(id, DELETE);
    }

    @Override
    public void updateElement(RouteEntity element) {
        update(element, UPDATE);
    }

    @Override
    public Integer count() {
        return findCount(COUNT);
    }

    @Override
    public List<RouteEntity> findPaginatedList(int startIdx, int amountElements) {
        return findPaginatedList(startIdx, amountElements, FIND_ALL);
    }

    @Override
    public List<RouteEntity> findByCriteria(String departure, String arrival) {
        LOGGER.info("Searching by criteria");
        ResultSet resultSet = null;
        List<RouteEntity> list;
        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_CRITERIA)) {
            statement.setString(1, departure + "%");
            statement.setString(2, arrival + "%");
            resultSet = statement.executeQuery();
            list = parseAll(resultSet);
        } catch (SQLException e) {
            LOGGER.warn("Can't execute method findByCriteria", e);
            throw new DatabaseRuntimeException("Can't execute method findByCriteria", e);
        }
        LOGGER.info("Returning list of routes according to criteria");
        return list;
    }
}

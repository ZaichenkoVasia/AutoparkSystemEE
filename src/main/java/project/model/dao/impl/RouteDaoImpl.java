package project.model.dao.impl;

import project.model.dao.AbstractDao;
import project.model.dao.RouteDao;
import project.model.dao.connector.ConnectionPool;
import project.model.entity.RouteEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RouteDaoImpl extends AbstractDao<RouteEntity> implements RouteDao {
    private static final String INSERT_route = "INSERT INTO project.routes(route_arrival, route_departure, route_distance) VALUES(?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM project.routes WHERE route_id = ?";
    private static final String FIND_ALL_ROUTES = "SELECT * FROM project.routes LIMIT ?, ?";
    private static final String COUNT = "SELECT * FROM project.routes";
    private static final String UPDATE_ROUTE = "UPDATE project.routes SET route_arrival = ?, route_departure = ?, route_distance = ? WHERE route_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM project.routes WHERE route_id = ?";
    private static final String FIND_BY_ARRIVAL = "SELECT * FROM project.routes WHERE arrival = ?";
    private static final String FIND_BY_DEPARTURE = "SELECT * FROM project.routes WHERE departure = ?";

    public RouteDaoImpl(ConnectionPool connector) {
        super(connector);
    }

    @Override
    public boolean save(RouteEntity entity) {
        return save(entity, INSERT_route);
    }

    @Override
    public Optional<RouteEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<RouteEntity> findAll(int currentPage, int recordsPerPage) {
        return findAll(FIND_ALL_ROUTES, currentPage, recordsPerPage);
    }

    @Override
    public List<RouteEntity> findByArrival(String arrival) {
        return findByStringParam(arrival, FIND_BY_ARRIVAL);
    }

    @Override
    public List<RouteEntity> findByDeparture(String departure) {
        return findByStringParam(departure, FIND_BY_DEPARTURE);
    }

    @Override
    public void update(RouteEntity entity) {
        update(entity, UPDATE_ROUTE);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    public int getNumberOfRows() {
        return getNumberOfRows(COUNT);
    }

    @Override
    protected void createStatementMapper(RouteEntity route, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, route.getArrival());
        preparedStatement.setString(2, route.getDeparture());
        preparedStatement.setInt(3, route.getDistance());
    }

    @Override
    protected void updateStatementMapper(RouteEntity route, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(route, preparedStatement);
        preparedStatement.setInt(4, route.getId());
    }

    @Override
    protected Optional<RouteEntity> mapResultSetToEntity(ResultSet route) throws SQLException {
        return Optional.of(RouteEntity.builder().withId(route.getInt(1))
                .withArrival(route.getString(2))
                .withDeparture(route.getString(3))
                .withDistance(route.getInt(4))
                .build());
    }
}

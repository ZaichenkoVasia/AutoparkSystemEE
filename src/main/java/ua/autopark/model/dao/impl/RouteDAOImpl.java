package ua.autopark.model.dao.impl;

import org.apache.log4j.Logger;
import ua.autopark.model.dao.AbstractGenericDAO;
import ua.autopark.model.dao.RouteDAO;
import ua.autopark.model.dao.connection.ConnectionFactory;
import ua.autopark.model.domain.Route;
import ua.autopark.model.exception.DAORuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RouteDAOImpl extends AbstractGenericDAO<Route> implements RouteDAO {
    private static final String ROUTE_INSERT = "INSERT INTO route (route_number, distance, departure, arrival) VALUES(?, ?, ?, ?)";
    private static final String FIND_ROUTE_BY_ID = "SELECT * FROM route WHERE idroute = ?";
    private static final String UPDATE_ROUTE = "UPDATE route SET route_number = ?, distance = ?, departure = ?, arrival = ? WHERE idroute = ?";
    private static final String DELETE_ROUTE_BY_ID = "DELETE FROM route WHERE idroute = ?";
    private static final String FIND_ROUTE_BY_CRITERIA = "SELECT * FROM route WHERE departure = ? AND arrival = ?";

    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(RouteDAOImpl.class);

    public RouteDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected Route parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Route.RouteBuilder()
                .withId(resultSet.getLong("idroute"))
                .withNumber(resultSet.getString("route_number"))
                .withDistance(resultSet.getInt("distance"))
                .withDeparture(resultSet.getString("departure"))
                .withArrival(resultSet.getString("arrival"))
                .build();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Route element) throws SQLException {
        statement.setString(1, element.getNumber());
        statement.setInt(2, element.getDistance());
        statement.setString(3, element.getDeparture());
        statement.setString(4, element.getArrival());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Route element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setLong(5, element.getId());
    }

    @Override
    public List<Route> searchByCriteria(String departure, String arrival) {
        logger.info("Searching by criteria");
        ResultSet resultSet = null;
        List<Route> list;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ROUTE_BY_CRITERIA)) {
            statement.setString(1, departure + "%");
            statement.setString(2, arrival + "%");
            resultSet = statement.executeQuery();
            list = parseAllElements(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        logger.info("Returning list of routes according to criteria");
        return list;
    }

    @Override
    public Integer insertElement(Route element) {
        return insertElement(element, ROUTE_INSERT);
    }

    @Override
    public Route getElementById(int id) {
        return getElementById(id, FIND_ROUTE_BY_ID);
    }

    @Override
    public void deleteElement(int id) {
        deleteElement(id, DELETE_ROUTE_BY_ID);

    }

    @Override
    public void updateElement(Route element) {
        updateElement(element, UPDATE_ROUTE);
    }
}

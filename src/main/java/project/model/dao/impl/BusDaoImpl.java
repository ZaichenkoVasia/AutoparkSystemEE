package project.model.dao.impl;

import project.model.dao.AbstractDao;
import project.model.dao.BusDao;
import project.model.dao.connector.ConnectionPool;
import project.model.entity.BusEntity;
import project.model.entity.enums.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BusDaoImpl extends AbstractDao<BusEntity> implements BusDao {
    private static final String INSERT_BUS = "INSERT INTO project.buses(bus_model, bus_seats, bus_status) VALUES(?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM project.buses WHERE bus_id = ?";
    private static final String FIND_ALL_BUSES = "SELECT * FROM project.buses LIMIT ?, ?";
    private static final String COUNT = "SELECT * FROM project.buses";
    private static final String FIND_BY_STATUS = "SELECT * FROM project.buses WHERE status = ?";
    private static final String UPDATE_BUS = "UPDATE project.buses SET bus_model = ?, bus_seats = ?, bus_status = ? WHERE bus_id = ?";
    private static final String UPDATE_BUS_STATUS = "UPDATE project.buses SET bus_status = ? WHERE bus_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM project.buses WHERE bus_id = ?";

    public BusDaoImpl(ConnectionPool connector) {
        super(connector);
    }

    @Override
    public boolean save(BusEntity bus) {
        return save(bus, INSERT_BUS);
    }

    @Override
    public Optional<BusEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<BusEntity> findAll(int currentPage, int recordsPerPage) {
        return findAll(FIND_ALL_BUSES, currentPage, recordsPerPage);
    }

    @Override
    public List<BusEntity> findByStatus(String status) {
        return findByStringParam(status, FIND_BY_STATUS);
    }

    @Override
    public void update(BusEntity bus) {
        update(bus, UPDATE_BUS);
    }

    @Override
    public void updateBusStatus(BusEntity bus, String status) {
        updateByStringParam(bus.getId(), status, UPDATE_BUS_STATUS);
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
    protected void createStatementMapper(BusEntity bus, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, bus.getModel());
        preparedStatement.setInt(2, bus.getSeats());
        preparedStatement.setString(3, bus.getStatus().toString());

    }

    @Override
    protected void updateStatementMapper(BusEntity bus, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(bus, preparedStatement);
        preparedStatement.setInt(3, bus.getId());
    }

    @Override
    protected Optional<BusEntity> mapResultSetToEntity(ResultSet bus) throws SQLException {
        return Optional.of(BusEntity.builder()
                .withId(bus.getInt(1))
                .withModel(bus.getString(2))
                .withSeats(bus.getInt(3))
                .withStatus(Status.valueOf(bus.getString(4)))
                .build());
    }
}

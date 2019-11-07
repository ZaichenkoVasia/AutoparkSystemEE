package project.model.dao.impl;

import project.model.entity.AssignmentEntity;
import project.model.entity.BusEntity;
import project.model.entity.RouteEntity;
import project.model.entity.UserEntity;
import project.model.entity.enums.Status;
import project.model.dao.AbstractDao;
import project.model.dao.AssignmentDao;
import project.model.dao.connector.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AssignmentDaoImpl extends AbstractDao<AssignmentEntity> implements AssignmentDao {
    private static final String INSERT_ASSIGNMENT = "INSERT INTO project.assignments(bus_id, user_id, route_id, assignment_status) VALUES(?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM project.assignments WHERE assignment_id = ?";
    private static final String FIND_ALL_ASSIGNMENTS = "SELECT * FROM project.assignments";
    private static final String FIND_BY_BUS = "SELECT * FROM project.assignments WHERE bus_id = ?";
    private static final String FIND_BY_USER = "SELECT * FROM project.assignments WHERE user_id = ?";
    private static final String FIND_BY_ROUTE = "SELECT * FROM project.assignments WHERE route_id = ?";
    private static final String FIND_BY_STATUS = "SELECT * FROM project.assignments WHERE assignment_status = ?";
    private static final String UPDATE_ASSIGNMENT = "UPDATE project.assignments SET bus_id = ?, user_id = ?, route_id = ?, assignment_status = ? WHERE assignment_id = ?";
    private static final String UPDATE_ASSIGNMENT_STATUS = "UPDATE project.assignments SET assignment_status = ? WHERE assignment_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM project.assignments WHERE assignment_id = ?";

    public AssignmentDaoImpl(ConnectionPool connector) {
        super(connector);
    }

    @Override
    public boolean save(AssignmentEntity assignment) {
        return save(assignment, INSERT_ASSIGNMENT);
    }

    @Override
    public Optional<AssignmentEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<AssignmentEntity> findAll() {
        return findAll(FIND_ALL_ASSIGNMENTS);
    }

    @Override
    public List<AssignmentEntity> findByBus(Integer id) {
        return findEntitiesByForeignKey(id, FIND_BY_BUS);
    }

    @Override
    public List<AssignmentEntity> findByUser(Integer id) {
        return findEntitiesByForeignKey(id, FIND_BY_USER);
    }

    @Override
    public List<AssignmentEntity> findByRoute(Integer id) {
        return findEntitiesByForeignKey(id, FIND_BY_ROUTE);
    }

    @Override
    public List<AssignmentEntity> findByStatus(String status) {
        return findByStringParam(status, FIND_BY_STATUS);
    }

    @Override
    public void update(AssignmentEntity assignment) {
        update(assignment, UPDATE_ASSIGNMENT);
    }

    @Override
    public void updateAssignmentStatus(AssignmentEntity assignment, String status) {
        updateByStringParam(assignment.getId(), status, UPDATE_ASSIGNMENT_STATUS);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected Optional<AssignmentEntity> mapResultSetToEntity(ResultSet assignment) throws SQLException {
        return Optional.of(AssignmentEntity.builder().withId(assignment.getInt(1))
                .withBus(BusEntity.builder()
                        .withId(assignment.getInt(2))
                        .build())
                .withUser(UserEntity.builder()
                        .withId(assignment.getInt(3))
                        .build())
                .withRoute(RouteEntity.builder()
                        .withId(assignment.getInt(4))
                        .build())
                .withStatus(Status.valueOf(assignment.getString(5)))
                .build());
    }

    @Override
    protected void createStatementMapper(AssignmentEntity assignment, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setInt(1, assignment.getBus().getId());
        preparedStatement.setInt(2, assignment.getUser().getId());
        preparedStatement.setInt(3, assignment.getRoute().getId());
        preparedStatement.setString(4, assignment.getStatus().toString());
    }

    @Override
    protected void updateStatementMapper(AssignmentEntity assignment, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(assignment, preparedStatement);
        preparedStatement.setInt(5, assignment.getUser().getId());
    }
}

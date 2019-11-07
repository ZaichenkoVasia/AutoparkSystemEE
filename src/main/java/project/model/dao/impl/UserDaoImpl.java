package project.model.dao.impl;

import project.model.entity.UserEntity;
import project.model.entity.enums.Role;
import project.model.entity.enums.Status;
import project.model.dao.AbstractDao;
import project.model.dao.UserDao;
import project.model.dao.connector.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<UserEntity> implements UserDao {
    private static final String INSERT_USER = "INSERT INTO project.users(user_name, user_surname, user_email, user_password, user_role, user_status) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM project.users WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT * FROM project.users";
    private static final String FIND_BY_STATUS = "SELECT * FROM project.users WHERE status = ?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM project.users WHERE user_email = ?";
    private static final String UPDATE_USER = "UPDATE project.users SET user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_role = ?, user_status = ? WHERE user_id = ?";
    private static final String UPDATE_USER_STATUS = "UPDATE project.users SET user_status = ? WHERE user_id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM project.users WHERE user_id = ?";

    public UserDaoImpl(ConnectionPool connector) {
        super(connector);
    }

    @Override
    public boolean save(UserEntity user) {
        return save(user, INSERT_USER);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return findById(id, FIND_BY_ID);
    }

    @Override
    public List<UserEntity> findAll() {
        return findAll(FIND_ALL_USERS);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return findOneByStringParam(email, FIND_BY_EMAIL);
    }

    @Override
    public List<UserEntity> findByStatus(String status) {
        return findByStringParam(status, FIND_BY_STATUS);
    }

    @Override
    public void update(UserEntity user) {
        update(user, UPDATE_USER);
    }

    @Override
    public void updateUserStatus(UserEntity user, String status) {
        updateByStringParam(user.getId(), status, UPDATE_USER_STATUS);
    }

    @Override
    public boolean deleteById(Integer id) {
        return deleteById(id, DELETE_BY_ID);
    }

    @Override
    protected Optional<UserEntity> mapResultSetToEntity(ResultSet user) throws SQLException {

        return Optional.ofNullable(UserEntity.builder()
                .withId(user.getInt(1))
                .withName(user.getString(2))
                .withSurname(user.getString(3))
                .withEmail(user.getString(4))
                .withPassword(user.getString(5))
                .withRole(Role.valueOf(user.getString(6)))
                .withStatus(Status.valueOf(user.getString(7)))
                .build());
    }

    @Override
    protected void createStatementMapper(UserEntity user, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getPassword());
        preparedStatement.setString(5, user.getRole().toString());
        preparedStatement.setString(6, user.getStatus().toString());
    }

    @Override
    protected void updateStatementMapper(UserEntity user, PreparedStatement preparedStatement) throws SQLException {
        createStatementMapper(user, preparedStatement);
        preparedStatement.setInt(7, user.getId());
    }

}

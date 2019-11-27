package model.dao.impl;

import model.dao.AbstractGenericDAO;
import model.dao.UserDAO;
import model.dao.connection.PoolConnection;
import model.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends AbstractGenericDAO<UserEntity> implements UserDAO {
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String INSERT_USER = "INSERT INTO user (login, password, role) VALUES(?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM user where iduser = ?";
    private static final String UPDATE_BY_ID = "UPDATE user SET login = ?, password = ?, role = ? WHERE iduser = ?";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE iduser = ?";

    public UserDAOImpl(PoolConnection poolConnection) {
        super(poolConnection);
    }

    @Override
    protected UserEntity parseToOne(ResultSet resultSet) throws SQLException {
        return UserEntity.builder()
                .withId(resultSet.getInt("user.iduser"))
                .withLogin(resultSet.getString("user.login"))
                .withPassword(resultSet.getString("user.password"))
                .withRole(resultSet.getString("user.role"))
                .build();
    }

    @Override
    protected void setInsertProperties(PreparedStatement statement, UserEntity element) throws SQLException {
        statement.setString(1, element.getLogin());
        statement.setString(2, element.getPassword());
        statement.setString(3, "driver");
    }

    @Override
    protected void setUpdateProperties(PreparedStatement statement, UserEntity element) throws SQLException {
        setInsertProperties(statement, element);
        statement.setInt(4, element.getId());
    }

    @Override
    public UserEntity findByLogin(UserEntity user) {
        return findByStringParam(user.getLogin(), FIND_USER_BY_LOGIN);
    }

    @Override
    public Integer insertElement(UserEntity element) {
        return insert(element, INSERT_USER);
    }

    @Override
    public UserEntity findElementById(Integer id) {
        return findByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer id) {
        delete(id, DELETE_BY_ID);
    }

    @Override
    public void updateElement(UserEntity element) {
        update(element, UPDATE_BY_ID);
    }

    @Override
    public Integer count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<UserEntity> findPaginatedList(int startIdx, int amountElements) {
        throw new UnsupportedOperationException();
    }
}

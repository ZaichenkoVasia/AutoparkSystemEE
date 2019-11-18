package model.dao.impl;

import domain.User;
import model.dao.AbstractGenericDAO;
import model.dao.UserDAO;
import model.dao.connection.PoolConection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends AbstractGenericDAO<User> implements UserDAO {
    private static final String FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    private static final String INSERT_USER = "INSERT INTO user (login, password, role) VALUES(?, ?, ?)";
    private static final String FIND_BY_ID = "SELECT * FROM user where iduser = ?";
    private static final String UPDATE_BY_ID = "UPDATE user SET login = ?, password = ?, role = ? WHERE iduser = ?";
    private static final String DELETE_BY_ID = "DELETE FROM user WHERE iduser = ?";

    public UserDAOImpl(PoolConection poolConection) {
        super(poolConection);
    }

    @Override
    protected User parseToOneElement(ResultSet resultSet) throws SQLException {
        return new User.UserBuilder()
                .setId(resultSet.getInt("user.iduser"))
                .setLogin(resultSet.getString("user.login"))
                .setPassword(resultSet.getString("user.password"))
                .setRole(resultSet.getString("user.role"))
                .createUser();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, User element) throws SQLException {
        statement.setString(1, element.getLogin());
        statement.setString(2, element.getPassword());
        statement.setString(3, "driver");
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, User element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(4, element.getId());
    }

    //TODO change methods
    @Override
    public User findUserByLoginData(User user) {
        return super.getElementByStringParam(user.getLogin(), FIND_USER_BY_LOGIN);
    }


    @Override
    public Integer insertElement(User element) {
        return super.insert(element, INSERT_USER);
    }

    @Override
    public User getElementById(Integer id) {
        return super.getElementByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer id) {
        super.deleteElement(id, DELETE_BY_ID);
    }

    @Override
    public void updateElement(User element) {
        super.updateElement(element, UPDATE_BY_ID);
    }

    @Override
    public Integer getElementsCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> getPaginatedList(int startIdx, int amountElements) {
        throw new UnsupportedOperationException();
    }
}

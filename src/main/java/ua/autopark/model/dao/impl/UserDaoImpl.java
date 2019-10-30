package ua.autopark.model.dao.impl;

import org.apache.log4j.Logger;
import ua.autopark.model.dao.AbstractGenericDAO;
import ua.autopark.model.dao.UserDAO;
import ua.autopark.model.dao.connection.ConnectionFactory;
import ua.autopark.model.domain.User;
import ua.autopark.model.exception.DAORuntimeException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl extends AbstractGenericDAO<User> implements UserDAO {
    private static final String FIND_BY_EMAIL = "SELECT * FROM user WHERE login = ?";
    private static final String USER_INSERT = "INSERT INTO user (login, password, role) VALUES(?, ?, ?)";
    private static final String FIND_USER_BY_ID = "SELECT * FROM user where iduser = ?";
    private static final String UPDATE_USER = "UPDATE user SET login = ?, password = ?, role = ? WHERE iduser = ?";
    private static final String DELETE_USER_BY_ID = "DELETE FROM user WHERE iduser = ?";

    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(UserDAOImpl.class);

    public UserDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected User parseToOneElement(ResultSet resultSet) throws SQLException {
        return User.builder()
                .withId(resultSet.getLong("user.id_user"))
                .withLogin(resultSet.getString("user.login"))
                .withPassword(resultSet.getString("user.password"))
                .build();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, User element) throws SQLException {
        statement.setString(1, element.getLogin());
        statement.setString(2, element.getPassword());
        statement.setString(2, element.getRole().toString());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, User element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setLong(4, element.getId());
    }

    @Override
    public User findUserByLoginData(String email) {
        logger.info("Searching user by login data");
        ResultSet resultSet = null;
        try (Connection connection = connectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_EMAIL)) {
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                logger.info("Returning user from database");
                return parseToOneElement(resultSet);
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAORuntimeException();
        }
        return null;
    }

    @Override
    public Integer insertElement(User element) {
        return super.insertElement(element, USER_INSERT);
    }

    @Override
    public User getElementById(int id) {
        return super.getElementById(id, FIND_USER_BY_ID);
    }

    @Override
    public void deleteElement(int id) {
        super.deleteElement(id, DELETE_USER_BY_ID);
    }

    @Override
    public void updateElement(User element) {
        super.updateElement(element, UPDATE_USER);
    }
}

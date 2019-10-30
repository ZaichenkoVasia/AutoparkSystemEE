package ua.autopark.model.dao.impl;

import org.apache.log4j.Logger;
import ua.autopark.model.dao.AbstractGenericDAO;
import ua.autopark.model.dao.AdminDAO;
import ua.autopark.model.dao.connection.ConnectionFactory;
import ua.autopark.model.domain.Admin;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl extends AbstractGenericDAO<Admin> implements AdminDAO {
    private static final String INSERT_ADMIN = "INSERT INTO admin (degree, iduser) VALUES(?, ?)";
    private static final String UPDATE_ADMIN = "SELECT * FROM admin JOIN user ON admin.iduser = user.iduser WHERE user.iduser = ?";
    private static final String FIND_ADMIN_BY_ID = "SELECT * FROM bus WHERE bus.idbus = ?";
    private ConnectionFactory connectionFactory;
    private static final Logger logger = Logger.getLogger(AdminDAOImpl.class);

    public AdminDAOImpl(ConnectionFactory connectionFactory) {
        super(connectionFactory);
        this.connectionFactory = connectionFactory;
    }

    @Override
    protected Admin parseToOneElement(ResultSet resultSet) throws SQLException {
        return Admin.builder()
                .withIdAdmin(resultSet.getLong("admin.idadmin"))
                .withName(resultSet.getString("user.name"))
                .withSurname(resultSet.getString("user.surname"))
                .withBirth(resultSet.getDate("user.birth"))
                .withDegree(resultSet.getString("admin.degree"))
                .withId(resultSet.getLong("user.iduser"))
                .withLogin(resultSet.getString("user.login"))
                .withPassword(resultSet.getString("user.password"))
                .build();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Admin element) throws SQLException {
        statement.setString(1, element.getDegree());
        statement.setLong(2, element.getId());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Admin element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setLong(3, element.getId());
    }

    @Override
    public Integer insertElement(Admin element) {
        return insertElement(element, INSERT_ADMIN);
    }

    @Override
    public Admin getElementById(int id) {
        return getElementById(id, FIND_ADMIN_BY_ID);
    }

    @Override
    public void deleteElement(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateElement(Admin element) {
        updateElement(element, UPDATE_ADMIN);
    }
}

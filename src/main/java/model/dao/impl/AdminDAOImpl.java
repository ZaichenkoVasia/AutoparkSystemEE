package model.dao.impl;

import domain.Admin;
import domain.User;
import model.dao.AbstractGenericDAO;
import model.dao.AdminDAO;
import model.dao.connection.PoolConection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDAOImpl extends AbstractGenericDAO<Admin> implements AdminDAO {

    private static final String INSERT_ADMIN = "INSERT INTO admin (name, surname, birth, degree, graduation, iduser) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String FIND_ADMIN_BY_ID = "SELECT * FROM admin JOIN user ON admin.iduser = user.iduser WHERE user.iduser = ?";
    private static final String UPDATE_ADMIN_BY_ID = "UPDATE admin SET name = ?, surname = ?, birth = ?, degree = ?, graduation = ?, iduser = ? WHERE idadmin = ?";

    public AdminDAOImpl(PoolConection poolConection) {
        super(poolConection);
    }

    @Override
    protected Admin parseToOne(ResultSet resultSet) throws SQLException {
        return Admin.builder()
                .withId(resultSet.getInt("admin.idadmin"))
                .withName(resultSet.getString("admin.name"))
                .withSurname(resultSet.getString("admin.surname"))
                .withBirth(resultSet.getDate("admin.birth"))
                .withDegree(resultSet.getString("admin.degree"))
                .withGraduation(resultSet.getDate("admin.graduation"))
                .withUser(User.builder()
                        .withId(resultSet.getInt("user.iduser"))
                        .withLogin(resultSet.getString("user.login"))
                        .withPassword(resultSet.getString("user.password"))
                        .build())
                .build();
    }

    @Override
    protected void setInsertProperties(PreparedStatement statement, Admin element) throws SQLException {
        statement.setString(1, element.getName());
        statement.setString(2, element.getSurname());
        statement.setDate(3, element.getBirth());
        statement.setString(4, element.getDegree());
        statement.setDate(5, element.getGraduation());
        statement.setInt(6, element.getUser().getId());
    }

    @Override
    protected void setUpdateProperties(PreparedStatement statement, Admin element) throws SQLException {
        setInsertProperties(statement, element);
        statement.setInt(7, element.getId());
    }

    @Override
    public Admin getAdminByUserId(Integer idUser) {
        return super.getByIntegerParam(idUser, FIND_ADMIN_BY_ID);
    }


    @Override
    public Integer insertElement(Admin element) {
        return super.insert(element, INSERT_ADMIN);
    }

    @Override
    public Admin getElementById(Integer id) {
        return super.getByIntegerParam(id, FIND_ADMIN_BY_ID);
    }

    @Override
    public void deleteElement(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateElement(Admin element) {
        super.update(element, UPDATE_ADMIN_BY_ID);
    }

    @Override
    public Integer getElementsCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Admin> getPaginatedList(int startIdx, int amountElements) {
        throw new UnsupportedOperationException();
    }
}

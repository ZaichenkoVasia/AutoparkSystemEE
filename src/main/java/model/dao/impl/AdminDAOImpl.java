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
    protected Admin parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Admin.AdminBuilder()
                .setId(resultSet.getInt("admin.idadmin"))
                .setName(resultSet.getString("admin.name"))
                .setSurname(resultSet.getString("admin.surname"))
                .setBirth(resultSet.getDate("admin.birth"))
                .setDegree(resultSet.getString("admin.degree"))
                .setGraduation(resultSet.getDate("admin.graduation"))
                .setUser(new User.UserBuilder()
                        .setId(resultSet.getInt("user.iduser"))
                        .setLogin(resultSet.getString("user.login"))
                        .setPassword(resultSet.getString("user.password"))
                        .createUser())
                .createAdmin();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Admin element) throws SQLException {
        statement.setString(1, element.getName());
        statement.setString(2, element.getSurname());
        statement.setDate(3, element.getBirth());
        statement.setString(4, element.getDegree());
        statement.setDate(5, element.getGraduation());
        statement.setInt(6, element.getUser().getId());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Admin element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(7, element.getId());
    }

    @Override
    public Admin getAdminByUserId(Integer idUser) {
        return super.getElementByIntegerParam(idUser, FIND_ADMIN_BY_ID);
    }


    @Override
    public Integer insertElement(Admin element) {
        return super.insert(element, INSERT_ADMIN);
    }

    @Override
    public Admin getElementById(Integer id) {
        return super.getElementByIntegerParam(id, FIND_ADMIN_BY_ID);
    }

    @Override
    public void deleteElement(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateElement(Admin element) {
        super.updateElement(element, UPDATE_ADMIN_BY_ID);
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

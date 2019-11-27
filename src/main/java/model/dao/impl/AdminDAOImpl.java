package model.dao.impl;

import model.dao.AbstractGenericDAO;
import model.dao.AdminDAO;
import model.dao.connection.PoolConnection;
import model.entity.AdminEntity;
import model.entity.UserEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AdminDAOImpl extends AbstractGenericDAO<AdminEntity> implements AdminDAO {

    private static final String INSERT_ADMIN = "INSERT INTO admin (name, surname, birth, degree, graduation, iduser) VALUES(?, ?, ?, ?, ?, ?)";
    private static final String FIND_ADMIN_BY_ID = "SELECT * FROM admin JOIN user ON admin.iduser = user.iduser WHERE user.iduser = ?";
    private static final String UPDATE_ADMIN_BY_ID = "UPDATE admin SET name = ?, surname = ?, birth = ?, degree = ?, graduation = ?, iduser = ? WHERE idadmin = ?";

    public AdminDAOImpl(PoolConnection poolConnection) {
        super(poolConnection);
    }

    @Override
    protected AdminEntity parseToOne(ResultSet resultSet) throws SQLException {
        return AdminEntity.builder()
                .withId(resultSet.getInt("admin.idadmin"))
                .withName(resultSet.getString("admin.name"))
                .withSurname(resultSet.getString("admin.surname"))
                .withBirth(resultSet.getDate("admin.birth"))
                .withDegree(resultSet.getString("admin.degree"))
                .withGraduation(resultSet.getDate("admin.graduation"))
                .withUser(UserEntity.builder()
                        .withId(resultSet.getInt("user.iduser"))
                        .withLogin(resultSet.getString("user.login"))
                        .withPassword(resultSet.getString("user.password"))
                        .build())
                .build();
    }

    @Override
    protected void setInsertProperties(PreparedStatement statement, AdminEntity element) throws SQLException {
        statement.setString(1, element.getName());
        statement.setString(2, element.getSurname());
        statement.setDate(3, element.getBirth());
        statement.setString(4, element.getDegree());
        statement.setDate(5, element.getGraduation());
        statement.setInt(6, element.getUser().getId());
    }

    @Override
    protected void setUpdateProperties(PreparedStatement statement, AdminEntity element) throws SQLException {
        setInsertProperties(statement, element);
        statement.setInt(7, element.getId());
    }

    @Override
    public AdminEntity findAdminByUserId(Integer idUser) {
        return findByIntegerParam(idUser, FIND_ADMIN_BY_ID);
    }


    @Override
    public Integer insertElement(AdminEntity element) {
        return insert(element, INSERT_ADMIN);
    }

    @Override
    public AdminEntity findElementById(Integer id) {
        return findByIntegerParam(id, FIND_ADMIN_BY_ID);
    }

    @Override
    public void deleteElement(Integer integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateElement(AdminEntity element) {
        update(element, UPDATE_ADMIN_BY_ID);
    }

    @Override
    public Integer count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AdminEntity> findPaginatedList(int startIdx, int amountElements) {
        throw new UnsupportedOperationException();
    }
}

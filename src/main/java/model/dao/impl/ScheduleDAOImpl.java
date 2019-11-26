package model.dao.impl;

import model.dao.AbstractGenericDAO;
import model.dao.ScheduleDAO;
import model.dao.connection.PoolConnection;
import model.entity.ScheduleEntity;
import model.exception.DatabaseRuntimeException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScheduleDAOImpl extends AbstractGenericDAO<ScheduleEntity> implements ScheduleDAO {
    private static final String FIND_BY_ID = "SELECT * FROM schedule WHERE idschedule = ?";
    private static final String INSERT = "INSERT INTO schedule (departure, arrival) VALUES(?, ?)";
    private static final String UPDATE_BY_ID = "UPDATE schedule SET departure = ?, arrival = ? WHERE idschedule = ?";

    public ScheduleDAOImpl(PoolConnection poolConnection) {
        super(poolConnection);
    }

    @Override
    protected ScheduleEntity parseToOne(ResultSet resultSet) throws SQLException {
        return ScheduleEntity.builder()
                .withId(resultSet.getInt("schedule.idschedule"))
                .withDeparture(resultSet.getString("schedule.departure"))
                .withArrival(resultSet.getString("schedule.arrival"))
                .build();
    }

    @Override
    protected void setInsertProperties(PreparedStatement statement, ScheduleEntity element) throws SQLException {
        statement.setString(1, element.getDeparture());
        statement.setString(2, element.getArrival());
    }

    @Override
    protected void setUpdateProperties(PreparedStatement statement, ScheduleEntity element) throws SQLException {
        setInsertProperties(statement, element);
        statement.setInt(3, element.getId());
    }


    @Override
    public Integer insertElement(ScheduleEntity element) throws DatabaseRuntimeException {
        return super.insert(element, INSERT);
    }

    @Override
    public ScheduleEntity getElementById(Integer id) throws DatabaseRuntimeException {
        return getByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer integer) throws DatabaseRuntimeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateElement(ScheduleEntity element) throws DatabaseRuntimeException {
        super.update(element, UPDATE_BY_ID);
    }

    @Override
    public Integer getElementsCount() throws DatabaseRuntimeException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<ScheduleEntity> getPaginatedList(int startIdx, int amountElements) throws DatabaseRuntimeException {
        throw new UnsupportedOperationException();
    }
}

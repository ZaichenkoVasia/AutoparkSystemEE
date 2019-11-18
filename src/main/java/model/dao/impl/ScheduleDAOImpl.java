package model.dao.impl;

import domain.Schedule;
import model.dao.AbstractGenericDAO;
import model.dao.ScheduleDAO;
import model.dao.connection.PoolConection;
import model.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ScheduleDAOImpl extends AbstractGenericDAO<Schedule> implements ScheduleDAO {
    private static final String FIND_BY_ID = "SELECT * FROM schedule WHERE idschedule = ?";
    private static final String INSERT = "INSERT INTO schedule (departure, arrival) VALUES(?, ?)";
    private static final String UPDATE_BY_ID = "UPDATE schedule SET departure = ?, arrival = ? WHERE idschedule = ?";

    public ScheduleDAOImpl(PoolConection poolConection) {
        super(poolConection);
    }

    @Override
    protected Schedule parseToOneElement(ResultSet resultSet) throws SQLException {
        return new Schedule.ScheduleBuilder()
                .setId(resultSet.getInt("schedule.idschedule"))
                .setDeparture(resultSet.getString("schedule.departure"))
                .setArrival(resultSet.getString("schedule.arrival"))
                .createSchedule();
    }

    @Override
    protected void setInsertElementProperties(PreparedStatement statement, Schedule element) throws SQLException {
        statement.setString(1, element.getDeparture());
        statement.setString(2, element.getArrival());
    }

    @Override
    protected void setUpdateElementProperties(PreparedStatement statement, Schedule element) throws SQLException {
        setInsertElementProperties(statement, element);
        statement.setInt(3, element.getId());
    }


    @Override
    public Integer insertElement(Schedule element) throws DAOException {
        return super.insert(element, INSERT);
    }

    @Override
    public Schedule getElementById(Integer id) throws DAOException {
        return getElementByIntegerParam(id, FIND_BY_ID);
    }

    @Override
    public void deleteElement(Integer integer) throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void updateElement(Schedule element) throws DAOException {
        super.updateElement(element, UPDATE_BY_ID);
    }

    @Override
    public Integer getElementsCount() throws DAOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Schedule> getPaginatedList(int startIdx, int amountElements) throws DAOException {
        throw new UnsupportedOperationException();
    }
}

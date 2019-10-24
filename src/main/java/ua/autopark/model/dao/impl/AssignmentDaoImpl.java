package ua.autopark.model.dao.impl;

import org.apache.log4j.Logger;
import ua.autopark.model.dao.AssignmentDao;
import ua.autopark.model.dao.DBConnector;
import ua.autopark.model.entity.Assignment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AssignmentDaoImpl extends AbstractCrudDaoImpl<Assignment> implements AssignmentDao {
    private static final Logger LOGGER = Logger.getLogger(AssignmentDaoImpl.class);
    private static final String FIND_BY_ID_QUERY = "SELECT * from topics WHERE id = ?";

    public AssignmentDaoImpl(DBConnector connector) {
        super(connector);
    }

    @Override
    public Assignment save(Assignment entity) {
        return null;
    }

    @Override
    public Optional<Assignment> findById(Long id) {
        return findById(id, FIND_BY_ID_QUERY);
    }

    @Override
    protected Optional<Assignment> mapResultSetToEntity(ResultSet resultSet) throws SQLException {
        return resultSet.next() ?
                Optional.ofNullable(Topic.builder()
                        .withId(resultSet.getLong("id"))
                        .withTitle(resultSet.getString("title"))
                        .build())
                : Optional.empty();
    }

    @Override
    public List<Assignment> findAll() {
        return null;
    }

    @Override
    public void update(Assignment entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteAllByIds(Set<Long> ids) {

    }
}

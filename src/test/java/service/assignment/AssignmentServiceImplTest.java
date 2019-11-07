package service.assignment;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.model.dao.AssignmentDao;
import project.model.domain.Assignment;
import project.model.domain.User;
import project.model.entity.AssignmentEntity;
import project.model.exception.InvalidEntityCreation;
import project.model.service.impl.AssignmentServiceImpl;
import project.model.service.mapper.AssignmentMapper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentServiceImplTest {
    private static final Assignment assignment = Assignment.builder().
            withId(1).
            withUser(User.builder()
                    .withId(1)
                    .build())
            .withId(1)
            .build();
    private static final List<AssignmentEntity> entities = Arrays.asList(
            AssignmentEntity.builder()
                    .withId(1)
                    .build(),
            AssignmentEntity.builder().
                    withId(2).
                    build());
    private static final List<Assignment> stories = Arrays.asList(assignment, assignment);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private AssignmentDao AssignmentDao;

    @Mock
    private AssignmentMapper mapper;

    @InjectMocks
    private AssignmentServiceImpl service;

    @After
    public void resetMock() {
        reset(AssignmentDao, mapper);
    }

    @Test
    public void shouldCreateAssignment() {
        when(mapper.mapAssignmentToAssignmentEntity(any(Assignment.class))).thenReturn(entities.get(1));
        when(AssignmentDao.save(any(AssignmentEntity.class))).thenReturn(true);

        assertTrue(service.createAssignment(assignment));
    }

    @Test
    public void shouldThrowInvalidEntityCreationWithNullAssignment() {
        exception.expect(InvalidEntityCreation.class);
        exception.expectMessage("assignment is not valid");

        service.createAssignment(null);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullStatus() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.findAssignmentsByUser(null);
    }

    @Test
    public void shouldShowAllStoriesByUser() {
        when(AssignmentDao.findByUser(any(Integer.class))).thenReturn(entities);
        when(mapper.mapAssignmentEntityToAssignment(any(AssignmentEntity.class))).thenReturn(assignment);

        List<Assignment> actual = service.findAssignmentsByUser(1);

        assertEquals(stories, actual);
    }

    @Test
    public void shouldReturnEmptyListSearchingByUser() {
        when(AssignmentDao.findByUser(any(Integer.class))).thenReturn(Collections.emptyList());

        List<Assignment> actual = service.findAssignmentsByUser(1);

        assertEquals(Collections.emptyList(), actual);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWithNullUserId() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Parameter is not valid");

        service.findAssignmentsByUser(null);
    }
}

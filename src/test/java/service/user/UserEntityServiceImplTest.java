package service.user;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import project.model.dao.UserDao;
import project.model.domain.User;
import project.model.entity.UserEntity;
import project.model.exception.InvalidRegistrationRuntimeException;
import project.model.exception.UserIsRegisteredRuntimeException;
import project.model.exception.UserNotFoundRuntimeException;
import project.model.service.encoder.PasswordEncoder;
import project.model.service.impl.UserServiceImpl;
import project.model.service.mapper.UserMapper;
import project.model.service.validator.UserValidator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserEntityServiceImplTest {
    private static final UserEntity entity = UserEntity.builder()
            .withId(1)
            .withName("Name")
            .withSurname("Surname")
            .withPassword("ENCODED")
            .withEmail("correct@gmail.com")
            .build();
    ;
    private static final User user = User.builder()
            .withName("Name")
            .withSurname("Surname")
            .withPassword("Hello")
            .withEmail("correct@gmail.com")
            .build();

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private UserDao repository;
    @Mock
    private UserMapper mapper;
    @Mock
    private UserValidator validator;
    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserServiceImpl userService;

    @After
    public void resetMock() {
        reset(repository);
        reset(validator);
        reset(encoder);
    }

    @Test
    public void shouldRegisterUser() {
        when(repository.save(any(UserEntity.class))).thenReturn(true);
        when(mapper.mapUserToUserEntity(any(User.class))).thenReturn(entity);
        when(encoder.encode(any(String.class))).thenReturn(Optional.of(entity.getPassword()));
        boolean actual = userService.register(user);

        assertTrue(actual);
    }

    @Test
    public void shouldThrowRuntimeExceptionWhenRegisterUser() {
        exception.expect(UserIsRegisteredRuntimeException.class);
        exception.expectMessage("User is already registered by this e-mail");

        when(repository.findByEmail(any(String.class))).thenReturn(Optional.ofNullable(entity));


        userService.register(user);
    }

    @Test
    public void shouldThrowInvalidRegistrationExceptionWhenRegisterNullUser() {
        exception.expect(InvalidRegistrationRuntimeException.class);

        doThrow(InvalidRegistrationRuntimeException.class).when(validator).validate(null);
        userService.register(null);
    }

    @Test
    public void shouldLoginUser() {
        when(repository.findByEmail("correct@gmail.com")).thenReturn(Optional.of(entity));
        when(encoder.encode("hello")).thenReturn(Optional.of(entity.getPassword()));
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(user);

        User actual = userService.login("correct@gmail.com", "hello");

        assertEquals(user, actual);
    }

    @Test
    public void shouldThrowUserNotFoundExceptionWithIncorrectPassword() {
        exception.expect(UserNotFoundRuntimeException.class);
        exception.expectMessage("Incorrect password");

        when(encoder.encode(any(String.class))).thenReturn(Optional.of("test"));
        when(repository.findByEmail("correct@gmail.com")).thenReturn(Optional.ofNullable(entity));

        userService.login("correct@gmail.com", "test");
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> expected = Collections.singletonList(user);
        List<UserEntity> entities = Collections.singletonList(entity);

        when(repository.findAll(1, 2)).thenReturn(entities);
        when(mapper.mapUserEntityToUser(entity)).thenReturn(user);
        List<User> actual = userService.findAll(1, 2);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmptyListWhenThereIsNoUsers() {
        List<User> expected = Collections.emptyList();

        when(repository.findAll(1, 2)).thenReturn(Collections.emptyList());
        List<User> actual = userService.findAll(1, 2);

        assertEquals(expected, actual);
    }
}

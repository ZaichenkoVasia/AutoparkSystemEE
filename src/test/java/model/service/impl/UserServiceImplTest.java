package model.service.impl;

import model.dao.UserDAO;
import model.domain.User;
import model.entity.UserEntity;
import model.exception.InvalidDataRuntimeException;
import model.exception.UserNotExistRuntimeException;
import model.service.encoder.EncoderPassword;
import model.service.mapper.UserMapper;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final UserEntity ENTITY = getUserEntity();

    private static final User DOMAIN = getUser();

    private static final List<UserEntity> ENTITIES = Collections.singletonList(ENTITY);

    private static final List<User> DOMAINS = Collections.singletonList(DOMAIN);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private UserDAO repository;

    @Mock
    private UserMapper mapper;

    @Mock
    private EncoderPassword encoder;

    @InjectMocks
    private UserServiceImpl service;

    @After
    public void resetMock() {
        reset(repository, mapper, encoder);
    }

    @Test
    public void shouldReturnUserByLogin() {
        when(encoder.encode(any(String.class))).thenReturn("21232f297a57a5a743894ae4a801fc3");
        when(mapper.mapUserToUserEntity(any(User.class))).thenReturn(ENTITY);
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(DOMAIN);
        when(repository.findByLogin(any(UserEntity.class))).thenReturn(ENTITY);
        User actual = service.findUserByLoginData(DOMAIN);
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullUser() {
        exception.expect(InvalidDataRuntimeException.class);
        service.findUserByLoginData(null);
    }

    @Test
    public void shouldThrowUserNotExistRuntimeExceptionWhenUserLoginOrPassDifferent() {
        exception.expect(UserNotExistRuntimeException.class);
        when(encoder.encode(any(String.class))).thenReturn("wrongEncoderPass");
        when(mapper.mapUserToUserEntity(any(User.class))).thenReturn(ENTITY);
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(DOMAIN);
        when(repository.findByLogin(any(UserEntity.class))).thenReturn(ENTITY);
        User actual = service.findUserByLoginData(DOMAIN);
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldInsertUser() {
        when(mapper.mapUserToUserEntity(any(User.class))).thenReturn(ENTITY);
        when(repository.insertElement(any(UserEntity.class))).thenReturn(ENTITY.getId());
        Integer actual = service.insertElement(DOMAIN);
        assertThat(actual, equalTo(DOMAIN.getId()));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenUserNullInInsert() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldReturnUserById() {
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(DOMAIN);
        when(repository.findElementById(any(Integer.class))).thenReturn(ENTITY);
        User actual = service.findElementById(DOMAIN.getId());
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenUserNullInFindById() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldDeleteUserById() {
        service.deleteElement(DOMAIN.getId());
        verify(repository).deleteElement(DOMAIN.getId());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInDelete() {
        exception.expect(InvalidDataRuntimeException.class);
        service.deleteElement(null);
    }

    @Test
    public void shouldUpdateUserById() {
        when(mapper.mapUserToUserEntity(any(User.class))).thenReturn(ENTITY);
        service.updateElement(DOMAIN);
        verify(repository).updateElement(ENTITY);
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInUpdate() {
        exception.expect(InvalidDataRuntimeException.class);
        service.updateElement(null);
    }

    @Test
    public void shouldReturnPagenationUser() {
        when(repository.findPaginatedList(anyInt(), anyInt())).thenReturn(ENTITIES);
        when(mapper.mapUserEntityToUser(any(UserEntity.class))).thenReturn(DOMAIN);
        List<User> actual = service.findPaginatedList(1, 1);
        assertThat(DOMAINS, equalTo(actual));
    }

    private static UserEntity getUserEntity() {
        return UserEntity.builder()
                .withId(1)
                .withLogin("email@gmail.com ")
                .withPassword("21232f297a57a5a743894ae4a801fc3")
                .withRole("admin")
                .build();
    }

    private static User getUser() {
        return User.builder()
                .withId(1)
                .withLogin("email@gmail.com ")
                .withPassword("admin")
                .withRole("admin")
                .build();
    }
}

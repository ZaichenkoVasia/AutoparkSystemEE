package model.service.impl;

import model.dao.AdminDAO;
import model.domain.Admin;
import model.domain.User;
import model.entity.AdminEntity;
import model.entity.UserEntity;
import model.exception.InvalidDataRuntimeException;
import model.service.mapper.AdminMapper;
import model.service.validator.impl.AdminValidator;
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
public class AdminServiceImplTest {

    private static final AdminEntity ENTITY = getAdminEntity();

    private static final Admin DOMAIN = getAdmin();

    private static final List<AdminEntity> ENTITIES = Collections.singletonList(ENTITY);

    private static final List<Admin> DOMAINS = Collections.singletonList(DOMAIN);

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    private AdminDAO repository;

    @Mock
    private AdminMapper mapper;

    @Mock
    private AdminValidator validator;

    @InjectMocks
    private AdminServiceImpl service;

    @After
    public void resetMock() {
        reset(repository, mapper, validator);
    }

    @Test
    public void shouldReturnAdminById() {
        when(mapper.mapAdminEntityToAdmin(any(AdminEntity.class))).thenReturn(DOMAIN);
        when(repository.findAdminByUserId(anyInt())).thenReturn(ENTITY);
        Admin actual = service.findAdminByUserId(DOMAIN.getId());
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenNullUser() {
        exception.expect(InvalidDataRuntimeException.class);
        service.findAdminByUserId(null);
    }

    @Test
    public void shouldInsertAdmin() {
        when(mapper.mapAdminToAdminEntity(any(Admin.class))).thenReturn(ENTITY);
        when(repository.insertElement(any(AdminEntity.class))).thenReturn(ENTITY.getId());
        doNothing().when(validator).validate(any(Admin.class));
        Integer actual = service.insertElement(DOMAIN);
        assertThat(actual, equalTo(DOMAIN.getId()));
        verify(validator).validate(any(Admin.class));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenAdminNullInInsert() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldReturnAdmiById() {
        when(mapper.mapAdminEntityToAdmin(any(AdminEntity.class))).thenReturn(DOMAIN);
        when(repository.findElementById(any(Integer.class))).thenReturn(ENTITY);
        Admin actual = service.findElementById(DOMAIN.getId());
        assertThat(actual, equalTo(DOMAIN));
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenAdminNullInFindById() {
        exception.expect(InvalidDataRuntimeException.class);
        service.insertElement(null);
    }

    @Test
    public void shouldDeleteAdminById() {
        service.deleteElement(DOMAIN.getId());
        verify(repository).deleteElement(DOMAIN.getId());
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInDelete() {
        exception.expect(InvalidDataRuntimeException.class);
        service.deleteElement(null);
    }

    @Test
    public void shouldUpdateAdminById() {
        when(mapper.mapAdminToAdminEntity(any(Admin.class))).thenReturn(ENTITY);
        service.updateElement(DOMAIN);
        verify(repository).updateElement(ENTITY);
    }

    @Test
    public void shouldThrowInvalidDataRuntimeExceptionWhenIdNullInUpdate() {
        exception.expect(InvalidDataRuntimeException.class);
        service.updateElement(null);
    }

    @Test
    public void shouldReturnPagenationAdmin() {
        when(repository.findPaginatedList(anyInt(), anyInt())).thenReturn(ENTITIES);
        when(mapper.mapAdminEntityToAdmin(any(AdminEntity.class))).thenReturn(DOMAIN);
        List<Admin> actual = service.findPaginatedList(1, 1);
        assertThat(DOMAINS, equalTo(actual));
    }

    private static AdminEntity getAdminEntity() {
        return AdminEntity.builder()
                .withId(1)
                .withName("admin")
                .withSurname("surname")
                .withDegree("degree")
                .withUser(UserEntity.builder()
                        .withLogin("email@gmail.com ")
                        .withPassword("21232f297a57a5a743894ae4a801fc3")
                        .withRole("admin")
                        .build())
                .build();
    }

    private static Admin getAdmin() {
        return Admin.builder()
                .withId(1)
                .withName("admin")
                .withSurname("surname")
                .withDegree("degree")
                .withUser(User.builder()
                        .withLogin("email@gmail.com ")
                        .withPassword("21232f297a57a5a743894ae4a801fc3")
                        .withRole("admin")
                        .build())
                .build();
    }
}

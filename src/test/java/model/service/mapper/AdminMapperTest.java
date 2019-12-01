package model.service.mapper;

import model.domain.Admin;
import model.domain.User;
import model.entity.AdminEntity;
import model.entity.UserEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AdminMapperTest {
    private static final AdminEntity ADMIN_ENTITY = getAdminEntity();

    private static final Admin ADMIN = getAdmin();

    private AdminMapper adminMapper = new AdminMapper();

    @Test
    public void shouldMapAdminEntityToAdmin() {
        Admin actual = adminMapper.mapAdminEntityToAdmin(ADMIN_ENTITY);

        assertThat(actual.getId(), is(ADMIN.getId()));
        assertThat(actual.getName(), is(ADMIN.getName()));
        assertThat(actual.getSurname(), is(ADMIN.getSurname()));
        assertThat(actual.getDegree(), is(ADMIN.getDegree()));
        assertThat(actual.getUser().getId(), is(ADMIN.getUser().getId()));
        assertThat(actual.getUser().getLogin(), is(ADMIN.getUser().getLogin()));
    }

    @Test
    public void shouldMapAdminToAdminEntity() {
        AdminEntity actual = adminMapper.mapAdminToAdminEntity(ADMIN);

        assertThat(actual.getId(), is(ADMIN_ENTITY.getId()));
        assertThat(actual.getName(), is(ADMIN_ENTITY.getName()));
        assertThat(actual.getSurname(), is(ADMIN_ENTITY.getSurname()));
        assertThat(actual.getDegree(), is(ADMIN_ENTITY.getDegree()));
        assertThat(actual.getUser().getId(), is(ADMIN_ENTITY.getUser().getId()));
        assertThat(actual.getUser().getLogin(), is(ADMIN_ENTITY.getUser().getLogin()));
    }

    private static AdminEntity getAdminEntity() {
        return AdminEntity.builder()
                .withId(1)
                .withName("admin.name")
                .withSurname("admin.surname")
                .withDegree("admin.degree")
                .withUser(UserEntity.builder()
                        .withId(1)
                        .withLogin("user.login")
                        .withPassword("user.password")
                        .build())
                .build();
    }

    private static Admin getAdmin() {
        return Admin.builder()
                .withId(1)
                .withName("admin.name")
                .withSurname("admin.surname")
                .withDegree("admin.degree")
                .withUser(User.builder()
                        .withId(1)
                        .withLogin("user.login")
                        .withPassword("user.password")
                        .build())
                .build();
    }
}

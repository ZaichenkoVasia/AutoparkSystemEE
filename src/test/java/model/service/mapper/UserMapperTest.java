package model.service.mapper;

import model.domain.User;
import model.entity.UserEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserMapperTest {
    private static final UserEntity USER_ENTITY = getUserEntity();

    private static final User USER_DOMAIN = getUser();

    private UserMapper userMapper = new UserMapper();

    @Test
    public void shouldMapUserEntityToUser() {
        User actual = userMapper.mapUserEntityToUser(USER_ENTITY);

        assertThat(actual.getId(), is(USER_DOMAIN.getId()));
        assertThat(actual.getLogin(), is(USER_DOMAIN.getLogin()));
        assertThat(actual.getPassword(), is(USER_DOMAIN.getPassword()));
        assertThat(actual.getRole(), is(USER_DOMAIN.getRole()));
    }

    @Test
    public void shouldMapUserToUserEntity() {
        UserEntity actual = userMapper.mapUserToUserEntity(USER_DOMAIN);

        assertThat(actual.getId(), is(USER_ENTITY.getId()));
        assertThat(actual.getLogin(), is(USER_ENTITY.getLogin()));
        assertThat(actual.getPassword(), is(USER_ENTITY.getPassword()));
        assertThat(actual.getRole(), is(USER_ENTITY.getRole()));
    }

    private static UserEntity getUserEntity() {
        return UserEntity.builder()
                .withId(1)
                .withLogin("email@gmail.com")
                .withPassword("password")
                .withRole("driver")
                .build();
    }

    private static User getUser() {
        return User.builder()
                .withId(1)
                .withLogin("email@gmail.com")
                .withPassword("password")
                .withRole("driver")
                .build();
    }
}
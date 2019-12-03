package model.service.mapper;

import model.domain.Driver;
import model.domain.User;
import model.entity.DriverEntity;
import model.entity.UserEntity;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DriverMapperTest {
    private static final DriverEntity DRIVER_ENTITY = getDriverEntity();

    private static final Driver DRIVER = getDriver();

    private DriverMapper driverMapper = new DriverMapper();

    @Test
    public void shouldMapDriverEntityToDriver() {
        Driver actual = driverMapper.mapDriverEntityToDriver(DRIVER_ENTITY);

        assertThat(actual.getId(), is(DRIVER.getId()));
        assertThat(actual.getName(), is(DRIVER.getName()));
        assertThat(actual.getSurname(), is(DRIVER.getSurname()));
        assertThat(actual.getSalary(), is(DRIVER.getSalary()));
        assertThat(actual.getStatus(), is(DRIVER.getStatus()));
        assertThat(actual.getUser().getId(), is(DRIVER.getUser().getId()));
        assertThat(actual.getUser().getLogin(), is(DRIVER.getUser().getLogin()));
    }

    @Test
    public void shouldMapDriverToDriverEntity() {
        DriverEntity actual = driverMapper.mapDriverToDriverEntity(DRIVER);

        assertThat(actual.getId(), is(DRIVER_ENTITY.getId()));
        assertThat(actual.getName(), is(DRIVER_ENTITY.getName()));
        assertThat(actual.getSurname(), is(DRIVER_ENTITY.getSurname()));
        assertThat(actual.getSalary(), is(DRIVER_ENTITY.getSalary()));
        assertThat(actual.getStatus(), is(DRIVER_ENTITY.getStatus()));
        assertThat(actual.getUser().getId(), is(DRIVER_ENTITY.getUser().getId()));
        assertThat(actual.getUser().getLogin(), is(DRIVER_ENTITY.getUser().getLogin()));
    }

    private static DriverEntity getDriverEntity() {
        return DriverEntity.builder()
                .withId(1)
                .withName("name")
                .withSurname("surname")
                .withSalary(100)
                .withStatus("driver")
                .withUser(UserEntity.builder()
                        .withId(1)
                        .withLogin("user.login")
                        .withPassword("user.password")
                        .build())
                .build();
    }

    private static Driver getDriver() {
        return Driver.builder()
                .withId(1)
                .withName("name")
                .withSurname("surname")
                .withSalary(100)
                .withStatus("driver")
                .withUser(User.builder()
                        .withId(1)
                        .withLogin("user.login")
                        .withPassword("user.password")
                        .build())
                .build();
    }
}
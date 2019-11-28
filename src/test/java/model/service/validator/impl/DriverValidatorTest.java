package model.service.validator.impl;

import model.domain.Driver;
import model.domain.User;
import model.exception.InvalidDataRuntimeException;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class DriverValidatorTest {
    private static DriverValidator validator = new DriverValidator();

    @Test
    public void shouldCorrectValidateDriver() {
        User user = User.builder()
                .withLogin("email@gmail.com")
                .withPassword("password")
                .build();
        Driver driver = Driver.builder()
                .withName("Name")
                .withSurname("Surname")
                .withUser(user)
                .withSalary(100)
                .build();
        validator.validate(driver);
        assertThat("email@gmail.com", is(driver.getUser().getLogin()));
        assertThat("password", is(driver.getUser().getPassword()));
        assertThat("Name", is(driver.getName()));
        assertThat("Surname", is(driver.getSurname()));
        assertThat(100, is(driver.getSalary()));
    }

    @Test(expected = InvalidDataRuntimeException.class)
    public void shouldThrowInvalidDataRuntimeExceptionValidatingInvalidEmail() {
        User user = User.builder()
                .withLogin("WrongEmail")
                .build();
        Driver driver = Driver.builder()
                .withName("Name")
                .withSurname("Surname")
                .withUser(user)
                .build();
        validator.validate(driver);
    }

    @Test(expected = InvalidDataRuntimeException.class)
    public void shouldThrowInvalidDataRuntimeExceptionValidatingNull() {
        validator.validate(null);
    }
//
//    @Test(expected = InvalidDataRuntimeException.class)
//    public void shouldThrowInvalidDataRuntimeExceptionValidatingInvalidName() {
//        Driver driver = Driver.builder()
//                .withName("WrongName123")
//                .build();
//        validator.validate(driver);
//    }
//
//    @Test(expected = InvalidDataRuntimeException.class)
//    public void shouldThrowInvalidDataRuntimeExceptionValidatingInvalidSurname() {
//        Driver driver = Driver.builder()
//                .withName("Name")
//                .withSurname("WrongSurname123")
//                .build();
//        validator.validate(driver);
//    }
//
//    @Test(expected = InvalidDataRuntimeException.class)
//    public void shouldThrowInvalidDataRuntimeExceptionValidatingInvalidPassword() {
//        User user = User.builder()
//                .withLogin("email@gmail.com")
//                .withPassword("WrongPassword$")
//                .build();
//        Driver driver = Driver.builder()
//                .withName("Name")
//                .withSurname("Surname")
//                .withUser(user)
//                .build();
//        validator.validate(driver);
//    }
//
//    @Test(expected = InvalidDataRuntimeException.class)
//    public void shouldThrowInvalidDataRuntimeExceptionValidatingInvalidSalary() {
//        User user = User.builder()
//                .withLogin("email@gmail.com")
//                .withPassword("password")
//                .build();
//        Driver driver = Driver.builder()
//                .withName("Name")
//                .withSurname("Surname")
//                .withUser(user)
//                .withSalary(1000000000)
//                .build();
//        validator.validate(driver);
//    }
}

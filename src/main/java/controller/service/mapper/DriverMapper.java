package controller.service.mapper;

import domain.Bus;
import domain.Driver;
import domain.User;
import model.entity.BusEntity;
import model.entity.DriverEntity;
import model.entity.UserEntity;

import java.util.Objects;

public class DriverMapper {
    public DriverEntity mapDriverToDriverEntity(Driver driver) {
        DriverEntity driverEntity = DriverEntity.builder()
                .withId(driver.getId())
                .withName(driver.getName())
                .withSurname(driver.getSurname())
                .withBirth(driver.getBirth())
                .withLicenseTest(driver.getLicenseTest())
                .withSalary(driver.getSalary())
                .withStatus(driver.getStatus())
                .withUser(UserEntity.builder()
                        .withId(driver.getUser().getId())
                        .withLogin(driver.getUser().getLogin())
                        .withPassword(driver.getUser().getPassword())
                        .withRole(driver.getUser().getRole())
                        .build())
                .build();

        if (Objects.isNull(driver.getBus())) {
            return driverEntity;
        }

        if (driverEntity.getBus().getId() == 0 || driverEntity.getBus().getId() == null) {
            return driverEntity;
        }
        return DriverEntity.builder()
                .withId(driver.getId())
                .withName(driver.getName())
                .withSurname(driver.getSurname())
                .withBirth(driver.getBirth())
                .withLicenseTest(driver.getLicenseTest())
                .withSalary(driver.getSalary())
                .withStatus(driver.getStatus())
                .withUser(UserEntity.builder()
                        .withId(driver.getUser().getId())
                        .withLogin(driver.getUser().getLogin())
                        .withPassword(driver.getUser().getPassword())
                        .withRole(driver.getUser().getRole())
                        .build())
                .withBus(BusEntity.builder()
                        .withId(driverEntity.getBus().getId())
                        .build())
                .build();
    }

    public Driver mapDriverEntityToDriver(DriverEntity driverEntity) {
        Driver driver = Driver.builder()
                .withId(driverEntity.getId())
                .withName(driverEntity.getName())
                .withSurname(driverEntity.getSurname())
                .withBirth(driverEntity.getBirth())
                .withLicenseTest(driverEntity.getLicenseTest())
                .withSalary(driverEntity.getSalary())
                .withStatus(driverEntity.getStatus())
                .withUser(User.builder()
                        .withId(driverEntity.getUser().getId())
                        .withLogin(driverEntity.getUser().getLogin())
                        .withPassword(driverEntity.getUser().getPassword())
                        .withRole(driverEntity.getUser().getRole())
                        .build())
                .build();

        if (Objects.isNull(driverEntity.getBus())) {
            return driver;
        }

        if (driverEntity.getBus().getId() == 0 || driverEntity.getBus().getId() == null) {
            return driver;
        }
        return Driver.builder()
                .withId(driverEntity.getId())
                .withName(driverEntity.getName())
                .withSurname(driverEntity.getSurname())
                .withBirth(driverEntity.getBirth())
                .withLicenseTest(driverEntity.getLicenseTest())
                .withSalary(driverEntity.getSalary())
                .withStatus(driverEntity.getStatus())
                .withUser(User.builder()
                        .withId(driverEntity.getUser().getId())
                        .withLogin(driverEntity.getUser().getLogin())
                        .withPassword(driverEntity.getUser().getPassword())
                        .withRole(driverEntity.getUser().getRole())
                        .build())
                .withBus(Bus.builder()
                        .withId(driverEntity.getBus().getId())
                        .build())
                .build();
    }
}

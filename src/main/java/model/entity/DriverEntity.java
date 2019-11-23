package model.entity;

import java.sql.Date;

public class DriverEntity {
    private final Integer id;
    private final String name;
    private final String surname;
    private final Date birth;
    private final UserEntity user;
    private final Date licenseTest;
    private final Integer salary;
    private final String status;
    private final BusEntity bus;

    public DriverEntity(DriverBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.birth = builder.birth;
        this.user = builder.user;
        this.licenseTest = builder.licenseTest;
        this.salary = builder.salary;
        this.status = builder.status;
        this.bus = builder.bus;
    }

    public DriverEntity(DriverEntity driver, BusEntity bus) {
        this.id = driver.id;
        this.name = driver.name;
        this.surname = driver.surname;
        this.birth = driver.birth;
        this.user = driver.user;
        this.licenseTest = driver.licenseTest;
        this.salary = driver.salary;
        this.status = driver.status;
        this.bus = bus;
    }

    public DriverEntity(DriverEntity driver, UserEntity user) {
        this.id = driver.id;
        this.name = driver.name;
        this.surname = driver.surname;
        this.birth = driver.birth;
        this.user = user;
        this.licenseTest = driver.licenseTest;
        this.salary = driver.salary;
        this.status = driver.status;
        this.bus = driver.bus;
    }

    public DriverEntity(DriverEntity driver, Integer id) {
        this.id = id;
        this.name = driver.name;
        this.surname = driver.surname;
        this.birth = driver.birth;
        this.user = driver.user;
        this.licenseTest = driver.licenseTest;
        this.salary = driver.salary;
        this.status = driver.status;
        this.bus = driver.bus;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirth() {
        return birth;
    }

    public UserEntity getUser() {
        return user;
    }

    public Date getLicenseTest() {
        return licenseTest;
    }

    public Integer getSalary() {
        return salary;
    }

    public String getStatus() {
        return status;
    }

    public BusEntity getBus() {
        return bus;
    }

    public static DriverBuilder builder() {
        return new DriverBuilder();
    }

    public static final class DriverBuilder {
        private Integer id;
        private String name;
        private String surname;
        private Date birth;
        private UserEntity user;
        private Date licenseTest;
        private Integer salary;
        private String status;
        private BusEntity bus;

        private DriverBuilder() {
        }

        public DriverBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public DriverBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public DriverBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public DriverBuilder withBirth(Date birth) {
            this.birth = birth;
            return this;
        }

        public DriverBuilder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public DriverBuilder withLicenseTest(Date licenseTest) {
            this.licenseTest = licenseTest;
            return this;
        }

        public DriverBuilder withSalary(Integer salary) {
            this.salary = salary;
            return this;
        }

        public DriverBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public DriverBuilder withBus(BusEntity bus) {
            this.bus = bus;
            return this;
        }

        public DriverEntity build() {
            return new DriverEntity(this);
        }
    }
}

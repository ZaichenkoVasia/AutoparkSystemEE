package ua.autopark.model.domain;

import ua.autopark.model.domain.enums.Role;
import ua.autopark.model.domain.enums.Status;

import java.util.Objects;

public class Driver extends User {
    private final Long idDriver;
    private final Integer salary;
    private final Status status;
    private final Bus bus;

    private Driver(DriverBuilder builder) {
        super(builder);
        this.salary = builder.salary;
        this.status = builder.status;
        this.bus = builder.bus;
        this.idDriver = builder.idDriver;
    }

    public Integer getSalary() {
        return salary;
    }

    public Status getStatus() {
        return status;
    }

    public Long getIdDriver() {
        return idDriver;
    }

    public Bus getBus() {
        return bus;
    }

    public static DriverBuilder builder() {
        return new DriverBuilder();
    }

    public static class DriverBuilder extends UserBuilder<DriverBuilder> {
        private Long idDriver;
        private Integer salary;
        private Status status = Status.FREE;
        private Bus bus;

        private DriverBuilder() {
        }

        @Override
        public DriverBuilder self() {
            return this;
        }

        public DriverBuilder withSalary(Integer salary) {
            this.salary = salary;
            return self();
        }

        public DriverBuilder withIdDriver(Long id) {
            this.idDriver = id;
            return self();
        }

        public DriverBuilder withStatus(Status status) {
            this.status = status;
            return self();
        }

        public DriverBuilder withBus(Bus bus) {
            this.bus = bus;
            return self();
        }

        public Driver build() {
            role = Role.DRIVER;
            return new Driver(self());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Driver)) {
            return false;
        }
        Driver driver = (Driver) o;
        return Objects.equals(salary, driver.salary) &&
                status == driver.status &&
                Objects.equals(bus, driver.bus) &&
                Objects.equals(idDriver, driver.idDriver) &&
                super.equals(driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, status, bus, idDriver) * super.hashCode();
    }

    @Override
    public String toString() {
        return "Driver{" +
                "salary=" + salary +
                ", status=" + status +
                ", bus=" + bus +
                ", id=" + id +
                ", idDriver=" + idDriver +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                '}';
    }
}

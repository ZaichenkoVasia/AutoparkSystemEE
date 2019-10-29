package ua.autopark.model.domain;

import ua.autopark.model.domain.enums.Role;
import ua.autopark.model.domain.enums.Status;

import java.util.Objects;

public class Driver extends User {
    private final Integer salary;
    private final Status status;
    private final Bus bus;

    public Driver(DriverBuilder builder) {
        super(builder);
        this.salary = builder.salary;
        this.status = builder.status;
        this.bus = builder.bus;
    }

    public Integer getSalary() {
        return salary;
    }

    public Status getStatus() {
        return status;
    }

    public Bus getBus() {
        return bus;
    }

    public static DriverBuilder builder() {
        return new DriverBuilder();
    }

    public static class DriverBuilder extends UserBuilder {
        private Integer salary;
        private Status status = Status.FREE;
        private Bus bus;

        private DriverBuilder() {
        }

        public DriverBuilder withSalary(Integer salary) {
            this.salary = salary;
            return this;
        }

        public DriverBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public DriverBuilder withBus(Bus bus) {
            this.bus = bus;
            return this;
        }

        public Driver build() {
            role = Role.DRIVER;
            return new Driver(this);
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
                super.equals(driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salary, status, bus) * super.hashCode();
    }

    @Override
    public String toString() {
        return "Driver{" +
                "salary=" + salary +
                ", status=" + status +
                ", bus=" + bus +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                '}';
    }
}

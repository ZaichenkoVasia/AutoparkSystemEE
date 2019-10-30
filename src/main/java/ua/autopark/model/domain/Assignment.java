package ua.autopark.model.domain;

import ua.autopark.model.domain.enums.Status;

import java.util.Objects;

public class Assignment {
    private final Long id;
    private final Bus bus;
    private final Driver driver;
    private final Route route;
    private final Status status;

    public Assignment(AssignmentBuilder builder) {
        this.id = builder.id;
        this.bus = builder.bus;
        this.driver = builder.driver;
        this.route = builder.route;
        this.status = builder.status;
    }

    public Bus getBus() {
        return bus;
    }

    public Driver getDriver() {
        return driver;
    }

    public Route getRoute() {
        return route;
    }

    public Status getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public static AssignmentBuilder builder() {
        return new AssignmentBuilder();
    }

    public static final class AssignmentBuilder {
        private Long id;
        private Bus bus;
        private Driver driver;
        private Route route;
        private Status status = Status.FREE;

        private AssignmentBuilder() {
        }

        public AssignmentBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AssignmentBuilder withBus(Bus bus) {
            this.bus = bus;
            return this;
        }

        public AssignmentBuilder withDriver(Driver driver) {
            this.driver = driver;
            return this;
        }

        public AssignmentBuilder withRoute(Route route) {
            this.route = route;
            return this;
        }

        public AssignmentBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public Assignment build() {
            return new Assignment(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Assignment)) {
            return false;
        }
        Assignment that = (Assignment) o;
        return Objects.equals(bus, that.bus) &&
                Objects.equals(driver, that.driver) &&
                Objects.equals(route, that.route) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bus, driver, route, status);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "bus=" + bus +
                ", driver=" + driver +
                ", route=" + route +
                ", status=" + status +
                '}';
    }
}

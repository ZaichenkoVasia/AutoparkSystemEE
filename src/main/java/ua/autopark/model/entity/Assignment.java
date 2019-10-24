package ua.autopark.model.entity;

import ua.autopark.model.entity.enums.Status;

import java.time.LocalDate;

public class Assignment {

    private final Long id;
    private final LocalDate date;
    private final Route route;
    private final Status status;
    private final User driver;
    private final Car bus;

    public Assignment(AssignmentBuilder builder) {
        this.id = builder.id;
        this.date = builder.date;
        this.route = builder.route;
        this.status = builder.status;
        this.driver = builder.driver;
        this.bus = builder.bus;
    }

    public static AssignmentBuilder builder() {
        return new AssignmentBuilder();
    }

    public Long getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public User getDriver() {
        return driver;
    }

    public Car getBus() {
        return bus;
    }

    public LocalDate getDate() {
        return date;
    }

    public Route getRoute() {
        return route;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", date=" + date +
                ", route=" + route +
                ", status=" + status +
                ", driver=" + driver +
                ", bus=" + bus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Assignment that = (Assignment) o;

        return date.equals(that.date) &&
                route.equals(that.route) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        int result = date.hashCode();
        result = 31 * result + (route != null ? route.hashCode() : 0);
        result = 31 * result + status.hashCode();
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (bus != null ? bus.hashCode() : 0);
        return result;
    }

    public static final class AssignmentBuilder {
        private Long id;
        private LocalDate date;
        private Route route;
        private Status status;
        private User driver;
        private Car bus;

        private AssignmentBuilder() {
        }

        public AssignmentBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AssignmentBuilder withDate(LocalDate date) {
            this.date = date;
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

        public AssignmentBuilder withDriver(User driver) {
            this.driver = driver;
            return this;
        }

        public AssignmentBuilder withBus(Car bus) {
            this.bus = bus;
            return this;
        }

        public Assignment build() {
            return new Assignment(this);
        }
    }
}

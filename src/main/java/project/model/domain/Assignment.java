package project.model.domain;

import project.model.entity.enums.Status;

import java.util.Objects;

public class Assignment {
    private final Integer id;
    private final Bus bus;
    private final User user;
    private final Route route;
    private final Status status;

    private Assignment(AssignmentBuilder builder) {
        id = builder.id;
        bus = builder.bus;
        user = builder.user;
        route = builder.route;
        status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public Bus getBus() {
        return bus;
    }

    public User getUser() {
        return user;
    }

    public Route getRoute() {
        return route;
    }

    public Status getStatus() {
        return status;
    }

    public static AssignmentBuilder builder() {
        return new AssignmentBuilder();
    }

    public static final class AssignmentBuilder {
        private Integer id;
        private Bus bus;
        private User user;
        private Route route;
        private Status status;

        private AssignmentBuilder() {
        }

        public AssignmentBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public AssignmentBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public AssignmentBuilder withRoute(Route route) {
            this.route = route;
            return this;
        }

        public AssignmentBuilder withBus(Bus bus) {
            this.bus = bus;
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
        if (this == o){
            return true;
        }
        if (!(o instanceof Assignment)) {
            return false;
        }
        Assignment that = (Assignment) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bus, that.bus) &&
                Objects.equals(user, that.user) &&
                Objects.equals(route, that.route) &&
                status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bus, user, route, status);
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", bus=" + bus +
                ", user=" + user +
                ", route=" + route +
                ", status=" + status +
                '}';
    }
}

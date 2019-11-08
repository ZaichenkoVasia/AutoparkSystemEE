package project.model.entity;

import project.model.entity.enums.Status;

import java.util.Objects;

public class AssignmentEntity {
    private final Integer id;
    private final BusEntity bus;
    private final UserEntity user;
    private final RouteEntity route;
    private final Status status;

    private AssignmentEntity(AssignmentBuilder builder) {
        id = builder.id;
        bus = builder.bus;
        user = builder.user;
        route = builder.route;
        status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public BusEntity getBus() {
        return bus;
    }

    public UserEntity getUser() {
        return user;
    }

    public RouteEntity getRoute() {
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
        private BusEntity bus;
        private UserEntity user;
        private RouteEntity route;
        private Status status;

        private AssignmentBuilder() {
        }

        public AssignmentBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public AssignmentBuilder withUser(UserEntity user) {
            this.user = user;
            return this;
        }

        public AssignmentBuilder withRoute(RouteEntity route) {
            this.route = route;
            return this;
        }

        public AssignmentBuilder withBus(BusEntity bus) {
            this.bus = bus;
            return this;
        }

        public AssignmentBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public AssignmentEntity build() {
            return new AssignmentEntity(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AssignmentEntity)) {
            return false;
        }
        AssignmentEntity that = (AssignmentEntity) o;
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
        return "AssignmentEntity{" +
                "id=" + id +
                ", bus=" + bus +
                ", user=" + user +
                ", route=" + route +
                ", status=" + status +
                '}';
    }
}

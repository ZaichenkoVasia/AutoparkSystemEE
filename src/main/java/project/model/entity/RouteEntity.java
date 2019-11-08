package project.model.entity;

import java.util.Objects;

public class RouteEntity {
    private final Integer id;
    private final String arrival;
    private final String departure;
    private final int distance;

    private RouteEntity(RouteBuilder builder) {
        id = builder.id;
        arrival = builder.arrival;
        departure = builder.departure;
        distance = builder.distance;
    }

    public Integer getId() {
        return id;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public int getDistance() {
        return distance;
    }

    public static RouteBuilder builder() {
        return new RouteBuilder();
    }

    public static final class RouteBuilder {
        private Integer id;
        private String departure;
        private String arrival;
        private int distance;

        private RouteBuilder() {
        }

        public RouteBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public RouteBuilder withDeparture(String departure) {
            this.departure = departure;
            return this;
        }

        public RouteBuilder withArrival(String arrival) {
            this.arrival = arrival;
            return this;
        }

        public RouteBuilder withDistance(int distance) {
            this.distance = distance;
            return this;
        }

        public RouteEntity build() {
            return new RouteEntity(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RouteEntity)) {
            return false;
        }
        RouteEntity that = (RouteEntity) o;
        return distance == that.distance &&
                Objects.equals(id, that.id) &&
                Objects.equals(departure, that.departure) &&
                Objects.equals(arrival, that.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departure, arrival, distance);
    }

    @Override
    public String toString() {
        return "RouteEntity{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", distance=" + distance +
                '}';
    }
}

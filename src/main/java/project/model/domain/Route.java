package project.model.domain;

import java.util.Objects;

public class Route {
    private final Integer id;
    private final String departure;
    private final String arrival;
    private final int distance;

    private Route(RouteBuilder builder) {
        id = builder.id;
        departure = builder.departure;
        arrival = builder.arrival;
        distance = builder.distance;
    }

    public Integer getId() {
        return id;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
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

        public Route build() {
            return new Route(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Route)) {
            return false;
        }
        Route route = (Route) o;
        return distance == route.distance &&
                Objects.equals(id, route.id) &&
                Objects.equals(departure, route.departure) &&
                Objects.equals(arrival, route.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departure, arrival, distance);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", distance=" + distance +
                '}';
    }
}

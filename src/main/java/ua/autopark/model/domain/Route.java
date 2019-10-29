package ua.autopark.model.domain;

import java.io.Serializable;
import java.util.Objects;

public class Route implements Serializable {
    private final Long id;
    private final String number;
    private final Integer distance;
    private final String departure;
    private final String arrival;

    public Route(RouteBuilder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.distance = builder.distance;
        this.departure = builder.departure;
        this.arrival = builder.arrival;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public static RouteBuilder builder() {
        return new RouteBuilder();
    }

    public static class RouteBuilder {
        private Long id;
        private String number;
        private Integer distance;
        private String departure;
        private String arrival;

        public RouteBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RouteBuilder withNumber(String number) {
            this.number = number;
            return this;
        }

        public RouteBuilder withDistance(Integer distance) {
            this.distance = distance;
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

        public Route builder() {
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
        return Objects.equals(id, route.id) &&
                Objects.equals(number, route.number) &&
                Objects.equals(distance, route.distance) &&
                Objects.equals(departure, route.departure) &&
                Objects.equals(arrival, route.arrival);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, distance, departure, arrival);
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", distance=" + distance +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                '}';
    }
}

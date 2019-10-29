package ua.autopark.model.domain;

import java.io.Serializable;
import java.util.Objects;

public class Bus implements Serializable {
    private final Long id;
    private final String model;
    private final Integer seats;
    private final Route route;

    public Bus(BusBuilder builder) {
        this.id = builder.id;
        this.model = builder.model;
        this.seats = builder.seats;
        this.route = builder.route;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public Integer getSeats() {
        return seats;
    }

    public Route getRoute() {
        return route;
    }

    public static BusBuilder builder() {
        return new BusBuilder();
    }

    public static class BusBuilder {
        private Long id;
        private String model;
        private Integer seats;
        private Route route;

        public BusBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public BusBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder withSeats(Integer seats) {
            this.seats = seats;
            return this;
        }

        public BusBuilder withRoute(Route route) {
            this.route = route;
            return this;
        }

        public Bus build() {
            return new Bus(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bus bus = (Bus) o;
        return Objects.equals(id, bus.id) &&
                Objects.equals(model, bus.model) &&
                Objects.equals(seats, bus.seats) &&
                Objects.equals(route, bus.route);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, seats, route);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", seats=" + seats +
                ", route=" + route +
                '}';
    }
}

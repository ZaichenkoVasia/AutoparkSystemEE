package project.model.domain;

import project.model.entity.enums.Status;

import java.util.Objects;

public class Bus {
    private final Integer id;
    private final String model;
    private final int seats;
    private final Status status;

    private Bus(BusBuilder builder) {
        this.id = builder.id;
        this.model = builder.model;
        this.seats = builder.seats;
        this.status = builder.status;
    }

    public Integer getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getSeats() {
        return seats;
    }

    public Status getStatus() {
        return status;
    }

    public static BusBuilder builder() {
        return new BusBuilder();
    }

    public static final class BusBuilder {
        private Integer id;
        private String model;
        private int seats;
        private Status status;

        private BusBuilder() {
        }

        public BusBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public BusBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder withSeats(int seats) {
            this.seats = seats;
            return this;
        }

        public BusBuilder withStatus(Status status) {
            this.status = status;
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
        if (!(o instanceof Bus)) {
            return false;
        }
        Bus bus = (Bus) o;
        return seats == bus.seats &&
                Objects.equals(id, bus.id) &&
                Objects.equals(model, bus.model) &&
                status == bus.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, seats, status);
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", seats=" + seats +
                ", status=" + status +
                '}';
    }
}

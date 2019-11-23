package model.entity;

import java.sql.Date;

public class BusEntity {
    private final Integer id;
    private final String plate;
    private final String model;
    private final Integer mileage;
    private final Date inspection;
    private final Integer consumption;
    private final Date release;
    private final String status;
    private final Integer seats;
    private final ScheduleEntity schedule;
    private final RouteEntity route;

    public BusEntity(BusBuilder builder) {
        this.id = builder.id;
        this.plate = builder.plate;
        this.model = builder.model;
        this.mileage = builder.mileage;
        this.inspection = builder.inspection;
        this.consumption = builder.consumption;
        this.release = builder.release;
        this.status = builder.status;
        this.seats = builder.seats;
        this.schedule = builder.schedule;
        this.route = builder.route;
    }

    public BusEntity(BusEntity bus, RouteEntity route) {
        this.id = bus.id;
        this.plate = bus.plate;
        this.model = bus.model;
        this.mileage = bus.mileage;
        this.inspection = bus.inspection;
        this.consumption = bus.consumption;
        this.release = bus.release;
        this.status = bus.status;
        this.seats = bus.seats;
        this.schedule = bus.schedule;
        this.route = route;
    }

    public BusEntity(BusEntity bus, ScheduleEntity schedule) {
        this.id = bus.id;
        this.plate = bus.plate;
        this.model = bus.model;
        this.mileage = bus.mileage;
        this.inspection = bus.inspection;
        this.consumption = bus.consumption;
        this.release = bus.release;
        this.status = bus.status;
        this.seats = bus.seats;
        this.schedule = schedule;
        this.route = bus.route;
    }

    public BusEntity(BusEntity bus, Integer id) {
        this.id = id;
        this.plate = bus.plate;
        this.model = bus.model;
        this.mileage = bus.mileage;
        this.inspection = bus.inspection;
        this.consumption = bus.consumption;
        this.release = bus.release;
        this.status = bus.status;
        this.seats = bus.seats;
        this.schedule = bus.schedule;
        this.route = bus.route;
    }

    public Integer getId() {
        return id;
    }

    public String getPlate() {
        return plate;
    }

    public String getModel() {
        return model;
    }

    public Integer getMileage() {
        return mileage;
    }

    public Date getInspection() {
        return inspection;
    }

    public Integer getConsumption() {
        return consumption;
    }

    public Date getRelease() {
        return release;
    }

    public String getStatus() {
        return status;
    }

    public Integer getSeats() {
        return seats;
    }

    public ScheduleEntity getSchedule() {
        return schedule;
    }

    public RouteEntity getRoute() {
        return route;
    }

    public static BusBuilder builder() {
        return new BusBuilder();
    }

    public static final class BusBuilder {
        private Integer id;
        private String plate;
        private String model;
        private Integer mileage;
        private Date inspection;
        private Integer consumption;
        private Date release;
        private String status;
        private Integer seats;
        private ScheduleEntity schedule;
        private RouteEntity route;

        private BusBuilder() {
        }

        public BusBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public BusBuilder withPlate(String plate) {
            this.plate = plate;
            return this;
        }

        public BusBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public BusBuilder withMileage(Integer mileage) {
            this.mileage = mileage;
            return this;
        }

        public BusBuilder withInspection(Date inspection) {
            this.inspection = inspection;
            return this;
        }

        public BusBuilder withConsumption(Integer consumption) {
            this.consumption = consumption;
            return this;
        }

        public BusBuilder withRelease(Date release) {
            this.release = release;
            return this;
        }

        public BusBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public BusBuilder withSeats(Integer seats) {
            this.seats = seats;
            return this;
        }

        public BusBuilder withSchedule(ScheduleEntity schedule) {
            this.schedule = schedule;
            return this;
        }

        public BusBuilder withRoute(RouteEntity route) {
            this.route = route;
            return this;
        }

        public BusEntity build() {
            return new BusEntity(this);
        }
    }
}

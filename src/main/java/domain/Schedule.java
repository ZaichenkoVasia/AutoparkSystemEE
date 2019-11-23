package domain;

public class Schedule {
    private final Integer id;
    private final String departure;
    private final String arrival;

    public Schedule(ScheduleBuilder builder) {
        this.id = builder.id;
        this.departure = builder.departure;
        this.arrival = builder.arrival;
    }

    public Schedule(Schedule schedule, Integer id) {
        this.id = id;
        this.departure = schedule.departure;
        this.arrival = schedule.arrival;
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

    public static ScheduleBuilder builder() {
        return new ScheduleBuilder();
    }

    public static final class ScheduleBuilder {
        private Integer id;
        private String departure;
        private String arrival;

        private ScheduleBuilder() {
        }

        public ScheduleBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public ScheduleBuilder withDeparture(String departure) {
            this.departure = departure;
            return this;
        }

        public ScheduleBuilder withArrival(String arrival) {
            this.arrival = arrival;
            return this;
        }

        public Schedule build() {
            return new Schedule(this);
        }
    }
}

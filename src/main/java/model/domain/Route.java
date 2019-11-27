package model.domain;

public class Route {
    private final Integer id;
    private final String number;
    private final String title;
    private final Integer distance;
    private final String status;
    private final String departure;
    private final String arrival;

    public Route(RouteBuilder builder) {
        this.id = builder.id;
        this.number = builder.number;
        this.title = builder.title;
        this.distance = builder.distance;
        this.status = builder.status;
        this.departure = builder.departure;
        this.arrival = builder.arrival;
    }

    public Route(Route route, Integer id) {
        this.id = id;
        this.number = route.number;
        this.title = route.title;
        this.distance = route.distance;
        this.status = route.status;
        this.departure = route.departure;
        this.arrival = route.arrival;
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public Integer getDistance() {
        return distance;
    }

    public String getStatus() {
        return status;
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

    public static final class RouteBuilder {
        private Integer id;
        private String number;
        private String title;
        private Integer distance;
        private String status;
        private String departure;
        private String arrival;

        private RouteBuilder() {
        }

        public RouteBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public RouteBuilder withNumber(String number) {
            this.number = number;
            return this;
        }

        public RouteBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public RouteBuilder withDistance(Integer distance) {
            this.distance = distance;
            return this;
        }

        public RouteBuilder withStatus(String status) {
            this.status = status;
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

        public Route build() {
            return new Route(this);
        }
    }
}

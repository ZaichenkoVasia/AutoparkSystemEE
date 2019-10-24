package ua.autopark.model.entity;


import java.util.Objects;

public class Route {
    private final Long id;
    private final String start;
    private final String finish;

    public Route(RouteBuilder builder) {
        this.id = builder.id;
        this.start = builder.start;
        this.finish = builder.finish;
    }

    public static RouteBuilder builder() {
        return new RouteBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getStart() {
        return start;
    }

    public String getFinish() {
        return finish;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", start='" + start + '\'' +
                ", finish='" + finish + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return start.equals(route.start)
                && finish.equals(route.finish);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class RouteBuilder {
        private Long id;
        private String start;
        private String finish;

        private RouteBuilder() {
        }

        public RouteBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public RouteBuilder withStart(String start) {
            this.start = start;
            return this;
        }

        public RouteBuilder withFinish(String finish) {
            this.finish = finish;
            return this;
        }

        public Route build() {
            return new Route(this);
        }
    }
}

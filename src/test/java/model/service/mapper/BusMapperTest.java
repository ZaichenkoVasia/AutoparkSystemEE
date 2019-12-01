package model.service.mapper;

import model.domain.*;
import model.entity.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BusMapperTest {
    private static final BusEntity BUS_ENTITY = getBusEntity();

    private static final Bus BUS = getBus();

    private BusMapper busMapper = new BusMapper();

    @Test
    public void shouldMapBusEntityToBus() {
        Bus actual = busMapper.mapBusEntityToBus(BUS_ENTITY);

        assertThat(actual.getId(), is(BUS.getId()));
        assertThat(actual.getPlate(), is(BUS.getPlate()));
        assertThat(actual.getMileage(), is(BUS.getMileage()));
        assertThat(actual.getModel(), is(BUS.getModel()));
        assertThat(actual.getConsumption(), is(BUS.getConsumption()));
        assertThat(actual.getStatus(), is(BUS.getStatus()));
        assertThat(actual.getSeats(), is(BUS.getSeats()));
        assertThat(actual.getRoute().getId(), is(BUS.getRoute().getId()));
        assertThat(actual.getSchedule().getId(), is(BUS.getSchedule().getId()));
        assertThat(actual.getSchedule().getArrival(), is(BUS.getSchedule().getArrival()));
        assertThat(actual.getSchedule().getDeparture(), is(BUS.getSchedule().getDeparture()));
    }

    @Test
    public void shouldMapBusToBusEntity() {
        BusEntity actual = busMapper.mapBusToBusEntity(BUS);

        assertThat(actual.getId(), is(BUS_ENTITY.getId()));
        assertThat(actual.getPlate(), is(BUS_ENTITY.getPlate()));
        assertThat(actual.getMileage(), is(BUS_ENTITY.getMileage()));
        assertThat(actual.getModel(), is(BUS_ENTITY.getModel()));
        assertThat(actual.getConsumption(), is(BUS_ENTITY.getConsumption()));
        assertThat(actual.getStatus(), is(BUS_ENTITY.getStatus()));
        assertThat(actual.getSeats(), is(BUS_ENTITY.getSeats()));
        assertThat(actual.getRoute().getId(), is(BUS_ENTITY.getRoute().getId()));
        assertThat(actual.getSchedule().getId(), is(BUS_ENTITY.getSchedule().getId()));
        assertThat(actual.getSchedule().getArrival(), is(BUS_ENTITY.getSchedule().getArrival()));
        assertThat(actual.getSchedule().getDeparture(), is(BUS_ENTITY.getSchedule().getDeparture()));
    }

    private static BusEntity getBusEntity() {
        return BusEntity.builder()
                .withId(1)
                .withPlate("bus.plate")
                .withModel("bus.model")
                .withMileage(100)
                .withConsumption(1000)
                .withSeats(20)
                .withStatus("bus.status")
                .withRoute(RouteEntity.builder()
                        .withId(1)
                        .build())
                .withSchedule(ScheduleEntity.builder()
                        .withId(1)
                        .withDeparture("schedule.departure")
                        .withArrival("schedule.arrival")
                        .build())
                .build();
    }

    private static Bus getBus() {
        return Bus.builder()
                .withId(1)
                .withPlate("bus.plate")
                .withModel("bus.model")
                .withMileage(100)
                .withConsumption(1000)
                .withSeats(20)
                .withStatus("bus.status")
                .withRoute(Route.builder()
                        .withId(1)
                        .build())
                .withSchedule(Schedule.builder()
                        .withId(1)
                        .withDeparture("schedule.departure")
                        .withArrival("schedule.arrival")
                        .build())
                .build();
    }
}

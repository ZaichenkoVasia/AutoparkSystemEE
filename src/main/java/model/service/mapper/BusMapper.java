package model.service.mapper;

import model.domain.Bus;
import model.domain.Route;
import model.domain.Schedule;
import model.entity.BusEntity;
import model.entity.RouteEntity;
import model.entity.ScheduleEntity;

import java.util.Objects;

public class BusMapper {
    public BusEntity mapBusToBusEntity(Bus bus) {
        BusEntity busEntity = BusEntity.builder()
                .withId(bus.getId())
                .withPlate(bus.getPlate())
                .withModel(bus.getModel())
                .withMileage(bus.getMileage())
                .withInspection(bus.getInspection())
                .withConsumption(bus.getConsumption())
                .withRelease(bus.getRelease())
                .withSeats(bus.getSeats())
                .withStatus(bus.getStatus())
                .withSchedule(ScheduleEntity.builder()
                        .withId(bus.getSchedule().getId())
                        .withDeparture(bus.getSchedule().getDeparture())
                        .withArrival(bus.getSchedule().getArrival())
                        .build())
                .build();

        if (Objects.isNull(bus.getRoute())) {
            return busEntity;
        }

        if (bus.getRoute().getId() == 0 || bus.getRoute().getId() == null) {
            return busEntity;
        }
        return BusEntity.builder()
                .withId(bus.getId())
                .withPlate(bus.getPlate())
                .withModel(bus.getModel())
                .withMileage(bus.getMileage())
                .withInspection(bus.getInspection())
                .withConsumption(bus.getConsumption())
                .withRelease(bus.getRelease())
                .withSeats(bus.getSeats())
                .withStatus(bus.getStatus())
                .withSchedule(ScheduleEntity.builder()
                        .withId(bus.getSchedule().getId())
                        .withDeparture(bus.getSchedule().getDeparture())
                        .withArrival(bus.getSchedule().getArrival())
                        .build())
                .withRoute(RouteEntity.builder()
                        .withId(bus.getRoute().getId())
                        .build())
                .build();
    }

    public Bus mapBusEntityToBus(BusEntity busEntity) {
        Bus bus = Bus.builder()
                .withId(busEntity.getId())
                .withPlate(busEntity.getPlate())
                .withModel(busEntity.getModel())
                .withMileage(busEntity.getMileage())
                .withInspection(busEntity.getInspection())
                .withConsumption(busEntity.getConsumption())
                .withRelease(busEntity.getRelease())
                .withSeats(busEntity.getSeats())
                .withStatus(busEntity.getStatus())
                .withSchedule(Schedule.builder()
                        .withId(busEntity.getSchedule().getId())
                        .withDeparture(busEntity.getSchedule().getDeparture())
                        .withArrival(busEntity.getSchedule().getArrival())
                        .build())
                .build();

        if (Objects.isNull(busEntity.getRoute())) {
            return bus;
        }

        if (busEntity.getRoute().getId() == 0 || busEntity.getRoute().getId() == null) {
            return bus;
        }
        return Bus.builder()
                .withId(busEntity.getId())
                .withPlate(busEntity.getPlate())
                .withModel(busEntity.getModel())
                .withMileage(busEntity.getMileage())
                .withInspection(busEntity.getInspection())
                .withConsumption(busEntity.getConsumption())
                .withRelease(busEntity.getRelease())
                .withSeats(busEntity.getSeats())
                .withStatus(busEntity.getStatus())
                .withSchedule(Schedule.builder()
                        .withId(busEntity.getSchedule().getId())
                        .withDeparture(busEntity.getSchedule().getDeparture())
                        .withArrival(busEntity.getSchedule().getArrival())
                        .build())
                .withRoute(Route.builder()
                        .withId(busEntity.getRoute().getId())
                        .build())
                .build();
    }
}

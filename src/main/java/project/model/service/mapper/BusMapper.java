package project.model.service.mapper;

import project.model.domain.Bus;
import project.model.entity.BusEntity;

public class BusMapper {
    public BusEntity mapBusToBusEntity(Bus domain) {
        return BusEntity.builder()
                .withId(domain.getId())
                .withModel(domain.getModel())
                .withSeats(domain.getSeats())
                .withStatus(domain.getStatus())
                .build();
    }

    public Bus mapBusEntityToBus(BusEntity entity) {
        return Bus.builder()
                .withId(entity.getId())
                .withModel(entity.getModel())
                .withSeats(entity.getSeats())
                .withStatus(entity.getStatus())
                .build();
    }
}

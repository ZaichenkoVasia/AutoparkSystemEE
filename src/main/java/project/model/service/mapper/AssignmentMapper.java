package project.model.service.mapper;

import project.model.domain.Assignment;
import project.model.domain.Bus;
import project.model.domain.Route;
import project.model.domain.User;
import project.model.entity.AssignmentEntity;
import project.model.entity.BusEntity;
import project.model.entity.RouteEntity;
import project.model.entity.UserEntity;

public class AssignmentMapper {
    public AssignmentEntity mapAssignmentToAssignmentEntity(Assignment domain) {
        return AssignmentEntity.builder()
                .withId(domain.getId())
                .withBus(BusEntity.builder()
                        .withId(domain.getBus().getId())
                        .build())
                .withRoute(RouteEntity.builder()
                        .withId(domain.getRoute().getId())
                        .build())
                .withUser(UserEntity.builder()
                        .withId(domain.getUser().getId())
                        .build())
                .withStatus(domain.getStatus())
                .build();
    }

    public Assignment mapAssignmentEntityToAssignment(AssignmentEntity entity) {
        return Assignment.builder()
                .withId(entity.getId())
                .withBus(Bus.builder()
                        .withId(entity.getBus().getId())
                        .build())
                .withRoute(Route.builder()
                        .withId(entity.getRoute().getId())
                        .build())
                .withUser(User.builder()
                        .withId(entity.getUser().getId())
                        .build())
                .withStatus(entity.getStatus())
                .build();
    }
}

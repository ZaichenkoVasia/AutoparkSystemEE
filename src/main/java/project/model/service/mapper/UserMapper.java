package project.model.service.mapper;

import project.model.domain.User;
import project.model.entity.UserEntity;

public class UserMapper {
    public UserEntity mapUserToUserEntity(User domain) {
        return UserEntity.builder()
                .withName(domain.getName())
                .withSurname(domain.getSurname())
                .withEmail(domain.getEmail())
                .withPassword(domain.getPassword())
                .withRole(domain.getRole())
                .withStatus(domain.getStatus())
                .build();
    }

    public User mapUserEntityToUser(UserEntity entity) {
        return User.builder()
                .withId(entity.getId())
                .withName(entity.getName())
                .withSurname(entity.getSurname())
                .withEmail(entity.getEmail())
                .withPassword(entity.getPassword())
                .withRole(entity.getRole())
                .withStatus(entity.getStatus())
                .build();
    }
}

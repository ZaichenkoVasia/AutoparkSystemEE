package model.service.mapper;

import model.domain.User;
import model.entity.UserEntity;

public class UserMapper {
    public UserEntity mapUserToUserEntity(User user) {
        return UserEntity.builder()
                .withId(user.getId())
                .withLogin(user.getLogin())
                .withPassword(user.getPassword())
                .withRole(user.getRole())
                .build();
    }

    public User mapUserEntityToUser(UserEntity userEntity) {
        return User.builder()
                .withId(userEntity.getId())
                .withLogin(userEntity.getLogin())
                .withPassword(userEntity.getPassword())
                .withRole(userEntity.getRole())
                .build();
    }
}

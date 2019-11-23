package controller.service.mapper;

import domain.Admin;
import domain.User;
import model.entity.AdminEntity;
import model.entity.UserEntity;

public class AdminMapper {
    public AdminEntity mapAdminToAdminEntity(Admin admin) {
        return AdminEntity.builder()
                .withId(admin.getId())
                .withName(admin.getName())
                .withSurname(admin.getSurname())
                .withBirth(admin.getBirth())
                .withDegree(admin.getDegree())
                .withGraduation(admin.getGraduation())
                .withUser(UserEntity.builder()
                        .withId(admin.getUser().getId())
                        .withLogin(admin.getUser().getLogin())
                        .withPassword(admin.getUser().getLogin())
                        .withRole(admin.getUser().getRole())
                        .build())
                .build();
    }

    public Admin mapAdminEntityToAdmin(AdminEntity adminEntity) {
        return Admin.builder()
                .withId(adminEntity.getId())
                .withName(adminEntity.getName())
                .withSurname(adminEntity.getSurname())
                .withBirth(adminEntity.getBirth())
                .withDegree(adminEntity.getDegree())
                .withGraduation(adminEntity.getGraduation())
                .withUser(User.builder()
                        .withId(adminEntity.getUser().getId())
                        .withLogin(adminEntity.getUser().getLogin())
                        .withPassword(adminEntity.getUser().getLogin())
                        .withRole(adminEntity.getUser().getRole())
                        .build())
                .build();
    }
}

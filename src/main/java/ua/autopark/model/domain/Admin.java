package ua.autopark.model.domain;

import ua.autopark.model.domain.enums.Role;

import java.util.Objects;

public class Admin extends User {
    private final Long idAdmin;
    private final String degree;

    private Admin(AdminBuilder builder) {
        super(builder);
        this.degree = builder.degree;
        this.idAdmin = builder.idAdmin;
    }

    public String getDegree() {
        return degree;
    }

    public Long getIdAdmin() {
        return idAdmin;
    }

    public static AdminBuilder builder() {
        return new AdminBuilder();
    }

    public static class AdminBuilder extends UserBuilder<AdminBuilder> {
        private String degree;
        private Long idAdmin;

        private AdminBuilder() {
        }

        @Override
        public AdminBuilder self() {
            return this;
        }

        public AdminBuilder withDegree(String degree) {
            this.degree = degree;
            return self();
        }

        public AdminBuilder withIdAdmin(Long id) {
            this.idAdmin = id;
            return self();
        }

        public Admin build() {
            role = Role.ADMIN;
            return new Admin(self());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Admin)) {
            return false;
        }
        Admin admin = (Admin) o;
        return Objects.equals(degree, admin.degree) &&
                Objects.equals(idAdmin, admin.idAdmin) &&
                super.equals(admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degree, idAdmin) * super.hashCode();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "degree='" + degree + '\'' +
                ", id=" + id +
                ", idAdmin=" + idAdmin +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                '}';
    }
}

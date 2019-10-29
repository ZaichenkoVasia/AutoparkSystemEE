package ua.autopark.model.domain;

import ua.autopark.model.domain.enums.Role;

import java.util.Objects;

public class Admin extends User {
    private final String degree;

    public Admin(AdminBuilder builder) {
        super(builder);
        this.degree = builder.degree;
    }

    public String getDegree() {
        return degree;
    }

    public static AdminBuilder builder() {
        return new AdminBuilder();
    }

    public static class AdminBuilder extends UserBuilder {
        private String degree;

        private AdminBuilder() {
        }

        public AdminBuilder withDegree(String degree) {
            this.degree = degree;
            return this;
        }

        public Admin build() {
            role = Role.ADMIN;
            return new Admin(this);
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
                super.equals(admin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(degree) * super.hashCode();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "degree='" + degree + '\'' +
                ", id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth=" + birth +
                '}';
    }
}

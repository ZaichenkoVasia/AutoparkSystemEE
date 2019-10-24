package ua.autopark.model.entity;

import ua.autopark.model.entity.enums.LicenseType;
import ua.autopark.model.entity.enums.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private final Long id;
    private final String login;
    private final String password;
    private final Role role;
    private final String firstName;
    private final String secondName;
    private final LicenseType licenseType;
    private final List<Car> cars = new ArrayList<>();

    public User(UserBuilder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
        this.firstName = builder.firstName;
        this.secondName = builder.secondName;
        this.licenseType = builder.licenseType;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public Long getId() {
        return id;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    public List<Car> getCars() {
        return cars;
    }

    public Role getRole() {
        if (role == null) {
            return Role.GUEST;
        }
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getLogin() {
        return login;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    public static class UserBuilder {
        private Long id;
        private String login;
        private String password;
        private Role role;
        private String firstName;
        private String secondName;
        private LicenseType licenseType;
        private List<Car> cars = new ArrayList<>();

        private UserBuilder() {
        }

        public UserBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public UserBuilder withLogin(String login) {
            this.login = login;
            return this;
        }

        public UserBuilder withPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder withRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder withSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public UserBuilder withLicenseType(LicenseType licenseType) {
            this.licenseType = licenseType;
            return this;
        }

        public UserBuilder withCars(List<Car> cars) {
            this.cars = cars;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

package model.entity;

import java.util.Objects;

public class UserEntity {
    private final Integer id;
    private final String login;
    private final String password;
    private final String role;

    public UserEntity(UserBuilder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.role = builder.role;
    }

    public UserEntity(UserEntity user, Integer id) {
        this.id = id;
        this.login = user.login;
        this.password = user.password;
        this.role = user.role;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static final class UserBuilder {
        private Integer id;
        private String login;
        private String password;
        private String role;

        private UserBuilder() {
        }

        public UserBuilder withId(Integer id) {
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

        public UserBuilder withRole(String role) {
            this.role = role;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserEntity)) {
            return false;
        }
        UserEntity user = (UserEntity) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, role);
    }
}

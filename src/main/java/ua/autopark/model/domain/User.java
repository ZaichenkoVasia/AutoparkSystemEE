package ua.autopark.model.domain;

import ua.autopark.model.domain.enums.Role;

import java.sql.Date;

public class User {
    protected final Long id;
    protected final String login;
    protected final String password;
    protected final Role role;
    protected final String name;
    protected final String surname;
    protected final Date birth;

    public User(UserBuilder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.name = builder.name;
        this.surname = builder.surname;
        this.birth = builder.birth;
        this.role = builder.role;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirth() {
        return birth;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
    }

    public static class UserBuilder {
        private Long id;
        private String login;
        private String password;
        protected Role role = Role.USER;
        private String name;
        private String surname;
        private Date birth;

        protected UserBuilder() {
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

        public UserBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public UserBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserBuilder withBirth(Date birth) {
            this.birth = birth;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}

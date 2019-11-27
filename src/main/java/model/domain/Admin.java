package model.domain;

import java.sql.Date;

public class Admin {
    private final Integer id;
    private final String name;
    private final String surname;
    private final Date birth;
    private final User user;
    private final String degree;
    private final Date graduation;

    public Admin(AdminBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.birth = builder.birth;
        this.user = builder.user;
        this.degree = builder.degree;
        this.graduation = builder.graduation;
    }

    public Admin(Admin admin, Integer id) {
        this.id = id;
        this.name = admin.name;
        this.surname = admin.surname;
        this.birth = admin.birth;
        this.user = admin.user;
        this.degree = admin.degree;
        this.graduation = admin.graduation;
    }

    public Admin(Admin admin, User user) {
        this.id = admin.id;
        this.name = admin.name;
        this.surname = admin.surname;
        this.birth = admin.birth;
        this.user = user;
        this.degree = admin.degree;
        this.graduation = admin.graduation;
    }

    public Integer getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public String getDegree() {
        return degree;
    }

    public Date getGraduation() {
        return graduation;
    }

    public static AdminBuilder builder() {
        return new AdminBuilder();
    }

    public static final class AdminBuilder {
        private Integer id;
        private String name;
        private String surname;
        private Date birth;
        private User user;
        private String degree;
        private Date graduation;

        public AdminBuilder withId(Integer id) {
            this.id = id;
            return this;
        }

        public AdminBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public AdminBuilder withSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public AdminBuilder withBirth(Date birth) {
            this.birth = birth;
            return this;
        }

        public AdminBuilder withUser(User user) {
            this.user = user;
            return this;
        }

        public AdminBuilder withDegree(String degree) {
            this.degree = degree;
            return this;
        }

        public AdminBuilder withGraduation(Date graduation) {
            this.graduation = graduation;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}

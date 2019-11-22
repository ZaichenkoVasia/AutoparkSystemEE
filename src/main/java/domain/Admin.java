package domain;

import java.sql.Date;

public class Admin{
    private Integer id;
    private String name;
    private String surname;
    private Date birth;
    private User user;
    private String degree;
    private Date graduation;

    public Admin() {
    }

    public Admin(Integer id, String name, String surname, Date birth, User user, String degree, Date graduation) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.user = user;
        this.degree = degree;
        this.graduation = graduation;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setUser(User user) {
        this.user = user;
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

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getGraduation() {
        return graduation;
    }

    public void setGraduation(Date graduation) {
        this.graduation = graduation;
    }

    public static class AdminBuilder {
        private Integer id;
        private String name;
        private String surname;
        private Date birth;
        private User user;
        private String degree;
        private Date graduation;

        public AdminBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public AdminBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public AdminBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public AdminBuilder setBirth(Date birth) {
            this.birth = birth;
            return this;
        }

        public AdminBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public AdminBuilder setDegree(String degree) {
            this.degree = degree;
            return this;
        }

        public AdminBuilder setGraduation(Date graduation) {
            this.graduation = graduation;
            return this;
        }

        public Admin createAdmin() {
            return new Admin(id, name, surname, birth, user, degree, graduation);
        }
    }
}

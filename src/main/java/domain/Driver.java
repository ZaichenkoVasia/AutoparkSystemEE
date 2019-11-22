package domain;

import java.sql.Date;

public class Driver {
    private Integer id;
    private String name;
    private String surname;
    private Date birth;
    private User user;
    private Date licenseTest;
    private Integer salary;
    private String status;
    private Bus bus;

    public Driver() {
    }

    public Driver(Integer id, String name, String surname, Date birth, User user, Date licenseTest, Integer salary, String status, Bus bus) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birth = birth;
        this.user = user;
        this.licenseTest = licenseTest;
        this.salary = salary;
        this.status = status;
        this.bus = bus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getLicenseTest() {
        return licenseTest;
    }

    public void setLicenseTest(Date licenseTest) {
        this.licenseTest = licenseTest;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    public static class DriverBuilder {
        private Integer id;
        private String name;
        private String surname;
        private Date birth;
        private User user;
        private Date licenseTest;
        private Integer salary;
        private String status;
        private Bus bus;

        public DriverBuilder setId(Integer id) {
            this.id = id;
            return this;
        }

        public DriverBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public DriverBuilder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public DriverBuilder setBirth(Date birth) {
            this.birth = birth;
            return this;
        }

        public DriverBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public DriverBuilder setLicenseTest(Date licenseTest) {
            this.licenseTest = licenseTest;
            return this;
        }

        public DriverBuilder setSalary(Integer salary) {
            this.salary = salary;
            return this;
        }

        public DriverBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public DriverBuilder setBus(Bus bus) {
            this.bus = bus;
            return this;
        }

        public Driver createDriver() {
            return new Driver(id, name, surname, birth, user, licenseTest, salary, status, bus);
        }
    }
}

package ua.autopark.model.entity;

import ua.autopark.model.entity.enums.LicenseType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Car {
    private final Long id;
    private final String model;
    private final LocalDate year;
    private final LicenseType licenseType;
    private final List<User> drivers = new ArrayList<>();

    public Car(CarBuilder builder) {
        this.id = builder.id;
        this.model = builder.model;
        this.year = builder.year;
        this.licenseType = builder.licenseType;
    }

    public static CarBuilder builder() {
        return new CarBuilder();
    }

    public List<User> getDrivers() {
        return drivers;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public LocalDate getYear() {
        return year;
    }

    public LicenseType getLicenseType() {
        return licenseType;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", licenseType=" + licenseType +
                ", drivers=" + drivers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return model.equals(car.model) &&
                year.equals(car.year) &&
                licenseType == car.licenseType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, year, licenseType);
    }

    public static final class CarBuilder {
        private Long id;
        private String model;
        private LocalDate year;
        private LicenseType licenseType;
        private List<User> drivers = new ArrayList<>();

        private CarBuilder() {
        }

        public CarBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public CarBuilder withModel(String model) {
            this.model = model;
            return this;
        }

        public CarBuilder withYear(LocalDate year) {
            this.year = year;
            return this;
        }

        public CarBuilder withLicenseType(LicenseType licenseType) {
            this.licenseType = licenseType;
            return this;
        }

        public CarBuilder withDrivers(List<User> drivers) {
            this.drivers = drivers;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

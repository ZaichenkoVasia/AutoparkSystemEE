CREATE DATABASE project;

CREATE TABLE project.buses (
  bus_id BIGINT NOT NULL AUTO_INCREMENT KEY,
  bus_model VARCHAR(128),
  bus_status VARCHAR(128),
  bus_seats BIGINT,
  route_id BIGINT,
    FOREIGN KEY (route_id) REFERENCES project.route (route_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project.users (
  user_id BIGINT NOT NULL AUTO_INCREMENT KEY,
  user_name VARCHAR(128),
  user_surname VARCHAR(128),
  user_login VARCHAR(128) UNIQUE,
  user_password VARCHAR(128),
  role VARCHAR(32) NOT NULL DEFAULT 'USER'
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project.admins (
  admin_id BIGINT NOT NULL AUTO_INCREMENT KEY,
  user_id BIGINT UNIQUE,
   FOREIGN KEY (user_id) REFERENCES project.users (user_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
  admin_degrre VARCHAR(128),
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project.drivers (
  driver_id BIGINT NOT NULL AUTO_INCREMENT KEY,
  user_id BIGINT UNIQUE,
   FOREIGN KEY (user_id) REFERENCES project.users (user_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
  bus_id BIGINT UNIQUE,
   FOREIGN KEY (bus_id) REFERENCES project.buses (bus_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
  driver_status VARCHAR(128),
    driver_salary BIGINT NOT NULL,
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project.routes (
  route_id BIGINT NOT NULL AUTO_INCREMENT KEY,
  route_departure VARCHAR(128),
  route_arrival VARCHAR(128),
  route_distance SMALLINT,

) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project.assignments (
  assignment_id BIGINT NOT NULL AUTO_INCREMENT KEY,
  bus_id BIGINT UNIQUE,
   FOREIGN KEY (bus_id) REFERENCES project.buses (bus_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
     driver_id BIGINT UNIQUE,
   FOREIGN KEY (driver_id) REFERENCES project.drivers (driver_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
     route_id BIGINT UNIQUE,
   FOREIGN KEY (route_id) REFERENCES project.routes (route_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
  assignment_status VARCHAR(128),
) ENGINE InnoDB DEFAULT CHARSET=utf8;

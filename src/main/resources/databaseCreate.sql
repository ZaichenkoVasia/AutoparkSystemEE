CREATE DATABASE project;

CREATE TABLE project.buses (
  bus_id INT NOT NULL AUTO_INCREMENT KEY,
  bus_model VARCHAR(128),
  bus_seats INT,
  bus_status VARCHAR(128)
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project.users (
  user_id INT NOT NULL AUTO_INCREMENT KEY,
  user_name VARCHAR(128),
  user_surname VARCHAR(128),
  user_email VARCHAR(128) UNIQUE,
  user_password VARCHAR(128),
  user_role VARCHAR(32) NOT NULL DEFAULT 'USER',
  user_status VARCHAR(128)
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project.routes (
  route_id INT NOT NULL AUTO_INCREMENT KEY,
  route_arrival VARCHAR(128),
  route_departure VARCHAR(128),
  route_distance INT
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE project.assignments (
  assignment_id INT NOT NULL AUTO_INCREMENT KEY,
  bus_id INT UNIQUE,
   FOREIGN KEY (bus_id) REFERENCES project.buses (bus_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
     user_id INT UNIQUE,
   FOREIGN KEY (user_id) REFERENCES project.users (user_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
     route_id INT UNIQUE,
   FOREIGN KEY (route_id) REFERENCES project.routes (route_id)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
  assignment_status VARCHAR(128)
) ENGINE InnoDB DEFAULT CHARSET=utf8;

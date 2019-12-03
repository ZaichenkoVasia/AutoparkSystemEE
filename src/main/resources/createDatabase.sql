CREATE DATABASE autopark;

CREATE TABLE autopark.user (
  iduser INT NOT NULL AUTO_INCREMENT KEY,
  login VARCHAR(128) UNIQUE,
  password VARCHAR(128),
  role VARCHAR(32) NOT NULL DEFAULT 'DRIVER'
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE autopark.admin (
  idadmin INT NOT NULL AUTO_INCREMENT KEY,
  name VARCHAR(128),
  surname VARCHAR(128),
  birth TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  degree VARCHAR(128),
  graduation VARCHAR(128),
  iduser INT UNIQUE,
   FOREIGN KEY (iduser) REFERENCES autopark.user (iduser)
    ON UPDATE CASCADE
     ON DELETE RESTRICT
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE autopark.route (
  idroute INT NOT NULL AUTO_INCREMENT KEY,
  route_number VARCHAR(128),
  title VARCHAR(128),
  distance INT,
  departure VARCHAR(128),
  arrival VARCHAR(128),
  status VARCHAR(128)
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE autopark.schedule (
  idschedule INT NOT NULL AUTO_INCREMENT KEY,
  departure VARCHAR(128),
  arrival VARCHAR(128)
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE autopark.bus (
  idbus INT NOT NULL AUTO_INCREMENT KEY,
  plate  VARCHAR(128),
  model VARCHAR(128),
  mileage INT,
  inspection TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  consumption INT,
  release_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  seats INT,
  status VARCHAR(128),
  idschedule INT UNIQUE,
   FOREIGN KEY (idschedule) REFERENCES autopark.schedule (idschedule)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
   idroute INT UNIQUE,
   FOREIGN KEY (idroute) REFERENCES autopark.route (idroute)
    ON UPDATE CASCADE
     ON DELETE RESTRICT
) ENGINE InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE autopark.driver (
  iddriver INT NOT NULL AUTO_INCREMENT KEY,
  name VARCHAR(128),
  surname VARCHAR(128),
  birth TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  license_test TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  salary INT,
  status VARCHAR(128),
  iduser INT UNIQUE,
   FOREIGN KEY (iduser) REFERENCES autopark.user (iduser)
    ON UPDATE CASCADE
     ON DELETE RESTRICT,
   idbus INT UNIQUE,
   FOREIGN KEY (idbus) REFERENCES autopark.bus (idbus)
    ON UPDATE CASCADE
     ON DELETE RESTRICT
) ENGINE InnoDB DEFAULT CHARSET=utf8;

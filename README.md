# AutoparkSystemEE

## Description

Fleet system. Drivers and Administrators can log into the system. In the fleet there are buses, routes. The Administrator can assign free buses to the Routes, to buses of free Drivers, and also release them, making them free. The driver can see his place of work, and he must also confirm his new Appointment.

## Instalation and running 
### Prerequisites

- JDK, JRE 8 or later
- Apache Maven
- Apache Tomcat
- MySQL

### Set up
- Clone the project to local reposiroty and build `.war` using Maven command: `mvn clean package -DskipTests`.
- Create database using createDatabase.sql file. Init database using insertDatabase.sql. 
- Deploy `.war` file to Apache Tomcat.

### Login data
- Admin - login: admin@gmail.com password: admin
--Driver - login: driver1@gmail.com password: driver

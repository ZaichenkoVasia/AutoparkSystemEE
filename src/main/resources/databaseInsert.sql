-- INSERT users:
INSERT INTO project.users (user_name, user_surname, user_email, user_password, user_role, user_status)
VALUES ('Vas', 'Vas', 'vas@gmail.com', 'vasia', 'ADMIN', 'FREE');
INSERT INTO project.users (user_name, user_surname, user_email, user_password, user_role, user_status)
VALUES ('Van', 'Van', 'van@gmail.com', 'vanya', 'DRIVER', 'FREE');
INSERT INTO project.users (user_name, user_surname, user_email, user_password, user_role, user_status)
VALUES ('Vov', 'Vov', 'vov@gmail.com', 'vovak', 'DRIVER', 'FREE');
INSERT INTO project.users (user_name, user_surname, user_email, user_password, user_role, user_status)
VALUES ('Hhhh', 'Hhhh', 'hhhh@gmail.com', 'Hhhh', 'DRIVER', 'FREE');
INSERT INTO project.users (user_name, user_surname, user_email, user_password, user_role, user_status)
VALUES ('Mmmmm', 'Mmmmm', 'mmmmm@gmail.com', 'Mmmmm', 'DRIVER', 'FREE');
INSERT INTO project.users (user_name, user_surname, user_email, user_password, user_role, user_status)
VALUES ('Kkkk', 'Kkkk', 'kkkk@gmail.com', 'Kkkk', 'DRIVER', 'FREE');
INSERT INTO project.users (user_name, user_surname, user_email, user_password, user_role, user_status)
VALUES ('Lllll', 'Lllll', 'lllll@gmail.com', 'Lllll', 'DRIVER', 'FREE');
INSERT INTO project.users (user_name, user_surname, user_email, user_password, user_role, user_status)
VALUES ('Ppppp', 'Ppppp', 'ppppp@gmail.com', 'Ppppp', 'DRIVER', 'FREE');

-- INSERT buses:
INSERT INTO project.buses (bus_model, bus_seats, bus_status)
VALUES ('Mers', 20, 'FREE');
INSERT INTO project.buses (bus_model, bus_seats, bus_status)
VALUES ('Mers', 25, 'FREE');
INSERT INTO project.buses (bus_model, bus_seats, bus_status)
VALUES ('Mers', 22, 'FREE');
INSERT INTO project.buses (bus_model, bus_seats, bus_status)
VALUES ('Mers', 21, 'FREE');
INSERT INTO project.buses (bus_model, bus_seats, bus_status)
VALUES ('Mers', 22, 'FREE');
INSERT INTO project.buses (bus_model, bus_seats, bus_status)
VALUES ('Sprinter', 21, 'FREE');
INSERT INTO project.buses (bus_model, bus_seats, bus_status)
VALUES ('Sprinter', 20, 'FREE');
INSERT INTO project.buses (bus_model, bus_seats, bus_status)
VALUES ('Sprinter', 27, 'FREE');

-- INSERT routes:
INSERT INTO project.routes (route_arrival, route_departure, route_distance)
VALUES ('Kyiv', 'Uman', 200);
INSERT INTO project.routes (route_arrival, route_departure, route_distance)
VALUES ('Kyiv', 'Monte', 250);
INSERT INTO project.routes (route_arrival, route_departure, route_distance)
VALUES ('Lviv', 'Santa', 2100);
INSERT INTO project.routes (route_arrival, route_departure, route_distance)
VALUES ('Korona', 'Bolt', 1000);
INSERT INTO project.routes (route_arrival, route_departure, route_distance)
VALUES ('Lob', 'Tron', 88200);
INSERT INTO project.routes (route_arrival, route_departure, route_distance)
VALUES ('Dosa', 'City', 900);
INSERT INTO project.routes (route_arrival, route_departure, route_distance)
VALUES ('Town', 'Village', 5);
INSERT INTO project.routes (route_arrival, route_departure, route_distance)
VALUES ('Heart', 'Mol', 8800);

-- INSERT assignments:
INSERT INTO project.assignments (bus_id, user_id, route_id)
VALUES (2, 2, 2);
INSERT INTO project.assignments (bus_id, user_id, route_id)
VALUES (3, 3, 3);
INSERT INTO project.assignments (bus_id, user_id, route_id)
VALUES (5, 4, 4);
INSERT INTO project.assignments (bus_id, user_id, route_id)
VALUES (4, 5, 5);
INSERT INTO project.assignments (bus_id, user_id, route_id)
VALUES (6, 6, 6);
INSERT INTO project.assignments (bus_id, user_id, route_id)
VALUES (8, 7, 7);
INSERT INTO project.assignments (bus_id, user_id, route_id)
VALUES (7, 8, 8);

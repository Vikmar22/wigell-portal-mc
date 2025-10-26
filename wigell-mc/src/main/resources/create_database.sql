DROP DATABASE IF EXISTS wigell_mc_db;
CREATE DATABASE wigell_mc_db;
USE wigell_mc_db;


CREATE TABLE Customer (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
user_name VARCHAR(255),
first_name VARCHAR(255),
last_name VARCHAR(255)
);


CREATE TABLE Bike (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
bike_name VARCHAR(255)
);


CREATE TABLE Bookings (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
time VARCHAR(255),
priceSEK INT,
priceGB INT,
end_date date,
start_date date,
bike_id bigint not null,
customer_id bigint,
constraint FK_Bookings_Customer foreign key (customer_id) references Customer (id),
constraint FK_Bookings_Bike foreign key (bike_id) references Bike (id),
status VARCHAR(255)


);


CREATE TABLE Address (
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
customer_id bigint not null,
street VARCHAR(255),
city VARCHAR(255),
postal_code VARCHAR(255),
foreign key (customer_id) references Customer(id)
);

CREATE TABLE users (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(100) NOT NULL UNIQUE,
password VARCHAR(255) NOT NULL,
role VARCHAR(50) NOT NULL
);

INSERT INTO Customer (user_name, first_name, last_name) VALUES
('jdoe', 'John', "doe"),
('asmith', 'Alice', "smith"),
('mmartin', 'Maria', "martin"),
('pparker', 'Peter', 'Parker'),
('tstark', 'Tony', 'Stark');



INSERT INTO Address (customer_id, street, city, postal_code) VALUES
(1, 'Sveavägen 12', 'Stockholm', '11157'),
(2, 'Sommarvägen 4', 'Uppsala', '75230'),
(3, 'Hamnvägen 88', 'Göteborg', '41457'),
(4, 'Storgatan 10', 'Malmö', '21122'),
(5, 'Lilla Vägen 7', 'Lund', '22224');




INSERT INTO Bike (bike_name)
VALUES
('Kawasaki'), ('Harley-Davidson'), ("Honda"), ("Suzuki"), ("Indian");

INSERT INTO Bookings (time, priceSEK, priceGB, start_date, end_date, bike_id, customer_id, status)
VALUES
('09:00', 800, 64, '2025-10-01', '2025-10-03', 1, 1, "ACTIVE"),
('10:00', 1000, 80, '2025-10-05', '2025-10-06', 2, 2, "ACTIVE");
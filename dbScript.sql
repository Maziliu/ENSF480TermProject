DROP DATABASE IF EXISTS MovieReservationDB;

CREATE DATABASE MovieReservationDB;
USE MovieReservationDB;


CREATE TABLE RegisteredUser (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    theater_credits INT NOT NULL DEFAULT 0,
    address VARCHAR(255) NOT NULL,
);


CREATE TABLE Movie (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    duration INT NOT NULL, 
    description TEXT,
    genre VARCHAR(100),
    release_date DATE,
    rating DECIMAL(2,1) 
);


CREATE TABLE Theater (
    theater_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address TEXT NOT NULL,
    seatmap JSON, 
    movie_list JSON 
);

CREATE TABLE PaymentCard (
    card_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    card_type ENUM('Credit', 'Debit') NOT NULL,
    card_holder_name VARCHAR(100) NOT NULL,
    expiration_date DATE NOT NULL,
    cvv CHAR(3),
    FOREIGN KEY (user_id) REFERENCES RegisteredUser(user_id) ON DELETE CASCADE
);

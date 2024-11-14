DROP DATABASE IF EXISTS MovieReservationDB;

CREATE DATABASE MovieReservationDB;
USE MovieReservationDB;


CREATE TABLE RegisteredUser (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    theater_credits INT NOT NULL DEFAULT 0,
    address VARCHAR(255) NOT NULL
);


CREATE TABLE Movie (
    movie_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    duration INT NOT NULL, 
    description TEXT,
    genre VARCHAR(100),
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
    card_number VARCHAR(20) PRIMARY KEY,
    user_id INT NOT NULL,
    card_type ENUM('Credit', 'Debit') NOT NULL,
    card_holder_name VARCHAR(100) NOT NULL,
    expiration_date DATE NOT NULL,
    cvv CHAR(3),
    FOREIGN KEY (user_id) REFERENCES RegisteredUser(user_id) ON DELETE CASCADE
);

CREATE TABLE Transactions (
    transaction_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NULL,
    user_email VARCHAR(255) NULL,
    transaction_reference VARCHAR(50) UNIQUE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_method ENUM('Credit', 'Debit', 'BankAccount') NOT NULL,
    card_number VARCHAR(20) NOT NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status ENUM('COMPLETED', 'REFUNDED') DEFAULT 'COMPLETED',
    FOREIGN KEY (user_id) REFERENCES RegisteredUser(user_id) ON DELETE SET NULL,
    FOREIGN KEY (card_number) REFERENCES PaymentCard(card_number) ON DELETE SET NULL
);
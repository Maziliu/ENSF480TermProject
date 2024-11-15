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
    user_id INT PRIMARY KEY,
    card_number VARCHAR(20) NOT NULL,
    card_type ENUM('Credit', 'Debit') NOT NULL,
    card_holder_name VARCHAR(100) NOT NULL,
    expiration_date DATE NOT NULL,
    cvv CHAR(3),
    FOREIGN KEY (user_id) REFERENCES RegisteredUser(user_id) ON DELETE CASCADE
);

CREATE TABLE Transactions (
    transaction_reference VARCHAR(50) PRIMARY KEY UNIQUE NOT NULL,
    user_id INT NULL,
    user_email VARCHAR(255) NULL,
    amount DECIMAL(10, 2) NOT NULL,
    card_number VARCHAR(20) NULL,
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    transaction_type ENUM('PURCHASE', 'REFUND', 'RENEWAL'),
    FOREIGN KEY (user_id) REFERENCES RegisteredUser(user_id)
);

CREATE TABLE CreditCodes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(255) UNIQUE NOT NULL,
    credit_amount DECIMAL(10, 2) NOT NULL,
    is_redeemed BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    expiration_date DATE NOT NULL
);

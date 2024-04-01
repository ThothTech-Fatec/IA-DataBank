create database K_ankh;
use K_ankh;

CREATE TABLE users (
    user_id INT auto_increment,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    PRIMARY KEY (`user_id`)
    )
    
    

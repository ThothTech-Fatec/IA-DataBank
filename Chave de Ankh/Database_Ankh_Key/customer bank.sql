-- Creating tables

create database Teste1;

use Teste1;


-- Creating tables
CREATE DATABASE IF NOT EXISTS Teste1;
USE Teste1;

CREATE TABLE Categories (
    CategoryID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL
);

CREATE TABLE Products (
    ProductID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    CategoryID INT NOT NULL,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL
);

CREATE TABLE Orders (
    OrderID INT AUTO_INCREMENT PRIMARY KEY,
    CustomerID INT NOT NULL,
    OrderDate DATE NOT NULL,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE OrderDetails (
    OrderDetailID INT AUTO_INCREMENT PRIMARY KEY,
    OrderID INT NOT NULL,
    ProductID INT NOT NULL,
    Quantity INT NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Inserting random data
INSERT INTO Categories (Name) VALUES 
    ('Laptops'),
    ('Smartphones'),
    ('Tablets'),
    ('Headphones'),
    ('Cameras');

INSERT INTO Products (Name, Price, CategoryID) VALUES 
    ('MacBook Air', 999.99, 1),
    ('iPhone 12', 799.99, 2),
    ('iPad Pro', 899.99, 3),
    ('Sony WH-1000XM4', 349.99, 4),
    ('Canon EOS Rebel T7i', 699.99, 5);

INSERT INTO Customers (FirstName, LastName) VALUES 
    ('John', 'Doe'),
    ('Alice', 'Smith'),
    ('Bob', 'Johnson');

INSERT INTO Orders (CustomerID, OrderDate) VALUES 
    (1, '2024-05-01'),
    (2, '2024-05-02'),
    (3, '2024-05-03');

INSERT INTO OrderDetails (OrderID, ProductID, Quantity) VALUES 
    (1, 1, 2),
    (2, 2, 1),
    (3, 3, 1),
    (3, 4, 1);


Select * from Orders;
Select * from OrderDetails;
Select * from Customers;
Select * from Products;
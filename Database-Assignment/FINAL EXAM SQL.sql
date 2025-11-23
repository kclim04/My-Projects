DROP TABLE OrderItems cascade constraints;
DROP TABLE Orders cascade constraints;
DROP TABLE Reviews cascade constraints;
DROP TABLE Products cascade constraints;
DROP TABLE Categories cascade constraints;
DROP TABLE Addresses cascade constraints;
DROP TABLE Users cascade constraints;

CREATE TABLE Users (
    UserID NUMERIC PRIMARY KEY,
    Username VARCHAR(50) NOT NULL UNIQUE,
    Email VARCHAR(100) NOT NULL UNIQUE,
    PasswordHash VARCHAR(255) NOT NULL,
    CreatedAt DATE
);

CREATE TABLE Categories (
    CategoryID NUMERIC PRIMARY KEY,
    Name VARCHAR(50) NOT NULL UNIQUE,
    Description VARCHAR(100)
);

CREATE TABLE Products (
    ProductID NUMERIC PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Description VARCHAR(100),
    Price NUMERIC(10, 2) NOT NULL,
    Stock NUMERIC DEFAULT 0,
    CategoryID NUMERIC,
    CreatedAt DATE,
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);

CREATE TABLE Orders (
    OrderID NUMERIC PRIMARY KEY,
    UserID NUMERIC,
    OrderDate DATE,
    Status VARCHAR(50),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE OrderItems (
    OrderItemID NUMERIC PRIMARY KEY,
    OrderID NUMERIC,
    ProductID NUMERIC,
    Quantity NUMERIC NOT NULL,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE Reviews (
    ReviewID NUMERIC PRIMARY KEY,
    UserID NUMERIC,
    ProductID NUMERIC,
    Rating NUMERIC,
    Content VARCHAR(100),
    CreatedAt DATE,
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

CREATE TABLE Addresses (
    AddressID NUMERIC PRIMARY KEY,
    UserID NUMERIC,
    Street VARCHAR(20) NOT NULL,
    City VARCHAR(100) NOT NULL,
    State VARCHAR(100) NOT NULL,
    ZipCode NUMERIC NOT NULL,
    Country VARCHAR(100) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

INSERT INTO Users (UserID, Username, Email, PasswordHash, CreatedAt) VALUES (1, 'john doe', 'john@example.com', 'passwordhash1', TO_DATE('2023-03-15', 'YYYY-MM-DD'));
INSERT INTO Users (UserID, Username, Email, PasswordHash, CreatedAt) VALUES (2, 'matthew ', 'matthew@blabla.com', 'idgaf', TO_DATE('2023-04-20', 'YYYY-MM-DD'));
INSERT INTO Users (UserID, Username, Email, PasswordHash, CreatedAt) VALUES (3, 'alice smith', 'alice@example.com', 'passwordhash3', TO_DATE('2023-05-10', 'YYYY-MM-DD'));
INSERT INTO Users (UserID, Username, Email, PasswordHash, CreatedAt) VALUES (4, 'amy smith', 'sam@example.com', 'passwordhash4', TO_DATE('2023-06-25', 'YYYY-MM-DD'));
INSERT INTO Users (UserID, Username, Email, PasswordHash, CreatedAt) VALUES (5, 'emily wilson', 'emily@example.com', 'passwordhash5', TO_DATE('2023-07-18', 'YYYY-MM-DD'));
INSERT INTO Users (UserID, Username, Email, PasswordHash, CreatedAt) VALUES (6, 'mike jones', 'mike@example.com', 'passwordhash6', TO_DATE('2023-08-05', 'YYYY-MM-DD'));
INSERT INTO Users (UserID, Username, Email, PasswordHash, CreatedAt) VALUES (7, 'Taylor Swift', 'taylor@example.com', 'passwordhash7', TO_DATE('2023-12-12', 'YYYY-MM-DD'));

INSERT INTO Categories (CategoryID, Name, Description) VALUES (1, 'Electronics', 'Electronic items');
INSERT INTO Categories (CategoryID, Name, Description) VALUES (2, 'Books', 'Books of all genres');
INSERT INTO Categories (CategoryID, Name, Description) VALUES (3, 'Clothing', 'Men and Women Clothing');
INSERT INTO Categories (CategoryID, Name, Description) VALUES (4, 'Home Appliances', 'Appliances for household use');
INSERT INTO Categories (CategoryID, Name, Description) VALUES (5, 'Toys', 'Children''s toys and games');
INSERT INTO Categories (CategoryID, Name, Description) VALUES (6, 'Beauty', 'Beauty and personal care products');
INSERT INTO Categories (CategoryID, Name, Description) VALUES (7, 'Food', 'Dry and processed food');

INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (1, 'Laptop', 'A high performance laptop', 999.99, 10, 1, TO_DATE('2017-08-21', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (2, 'Smartphone', 'Latest model smartphone', 699.99, 25, 1, TO_DATE('2016-11-05', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (3, 'Novel', 'A best-selling novel', 19.99, 50, 2, TO_DATE('2018-03-12', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (5, 'Refrigerator', 'Large capacity refrigerator', 799.99, 15, 4, TO_DATE('2016-05-17', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (6, 'Microwave Oven', 'Compact microwave oven', 129.99, 20, 4, TO_DATE('2017-12-08', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (7, 'Teddy Bear', 'Soft and cuddly teddy bear', 14.99, 30, 5, TO_DATE('2015-10-22', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (9, 'TV', 'High definition television with speakers', 1499.99, 5, 1, TO_DATE('2018-07-03', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (10, 'Digital Camera', 'Professional DSLR camera', 899.99, 10, 1, TO_DATE('2019-04-28', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (12, 'Cookware Set', 'Stainless steel cookware set', 199.99, 20, 4, TO_DATE('2016-08-03', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (13, 'Board Game', 'Classic board game', 29.99, 25, 5, TO_DATE('2017-09-19', 'YYYY-MM-DD'));
INSERT INTO Products (ProductID, Name, Description, Price, Stock, CategoryID, CreatedAt) VALUES (14, 'Shampoo', 'Moisturizing shampoo', 7.99, 30, 6, TO_DATE('2018-11-27', 'YYYY-MM-DD'));


INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (1, 1, TO_DATE('2024-06-08', 'YYYY-MM-DD'), 'Shipped');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (2, 2, TO_DATE('2024-05-15', 'YYYY-MM-DD'), 'Shipped');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (3, 3, TO_DATE('2024-04-23', 'YYYY-MM-DD'), 'Shipped');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (4, 1, TO_DATE('2024-03-10', 'YYYY-MM-DD'), 'Shipped');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (5, 4, TO_DATE('2024-02-18', 'YYYY-MM-DD'), 'Shipped');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (6, 6, TO_DATE('2024-01-25', 'YYYY-MM-DD'), 'Delivered');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (7, 1, TO_DATE('2024-06-07', 'YYYY-MM-DD'), 'Shipped');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (9, 3, TO_DATE('2024-04-22', 'YYYY-MM-DD'), 'Delivered');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (10, 2, TO_DATE('2024-03-09', 'YYYY-MM-DD'), 'Shipped');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (11, 5, TO_DATE('2024-02-17', 'YYYY-MM-DD'), 'Processing');
INSERT INTO Orders (OrderID, UserID, OrderDate, Status) VALUES (12, 6, TO_DATE('2024-01-24', 'YYYY-MM-DD'), 'Delivered');


INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (1, 1, 2, 1);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (2, 1, 3, 1);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (3, 2, 2, 2);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (4, 3, 2, 1);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (5, 3, 3, 1);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (6, 4, 2, 1);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (7, 5, 2, 3);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (8, 6, 6, 4);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (9, 7, 9, 1);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (10, 7, 10, 1);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (12, 9, 12, 1);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (13, 10, 9, 2);
INSERT INTO OrderItems (OrderItemID, OrderID, ProductID, Quantity) VALUES (14, 11, 14, 3);

INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (1, 1, 2, 5, 'Great smartphone for student like me!', TO_DATE('2023-03-15', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (3, 3, 1, 5, 'Excellent laptop', TO_DATE('2023-05-10', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (4, 4, 5, 5, 'Great refrigerator!', TO_DATE('2023-06-25', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (5, 1, 9, 5, 'Amazing TV, crystal clear picture quality', TO_DATE('2023-07-18', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (7, 3, 12, 4, 'High quality cookware set, durable', TO_DATE('2023-09-14', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (8, 4, 13, 5, 'Fun board game for the whole family', TO_DATE('2023-10-30', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (9, 5, 14, 3, 'Decent shampoo, but scent is too strong', TO_DATE('2023-11-12', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (10, 6, 9, 4, 'Excellent purchase, fast delivery', TO_DATE('2023-12-08', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (11, 2, 9, 4, 'Excellent purchase and fast delivery', TO_DATE('2023-12-11', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (12, 3, 9, 5, 'Stupendously reasonable purchase and swift delivery', TO_DATE('2023-12-20', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (13, 1, 12, 1, 'Horrendous pans. Sounds like a cymbal when knocked.', TO_DATE('2023-01-14', 'YYYY-MM-DD'));
INSERT INTO Reviews (ReviewID, UserID, ProductID, Rating, Content, CreatedAt) VALUES (14, 2, 12, 2, 'I bought this to smack my student cause they are lazy', TO_DATE('2024-06-08', 'YYYY-MM-DD'));


INSERT INTO Addresses (AddressID, UserID, Street, City, State, ZipCode, Country) VALUES (1, 1, '123 Main St', 'PJ', 'Kuala Lumpur', '62701', 'MY');
INSERT INTO Addresses (AddressID, UserID, Street, City, State, ZipCode, Country) VALUES (2, 2, 'Sunway', 'Sungei Wey', 'Selangor', '17500', 'MY');
INSERT INTO Addresses (AddressID, UserID, Street, City, State, ZipCode, Country) VALUES (3, 3, '789 Oak St', 'Segamat', 'Johor', '62701', 'MY');
INSERT INTO Addresses (AddressID, UserID, Street, City, State, ZipCode, Country) VALUES (4, 4, '321 Pine St', 'Springfield', 'Woodland', '33701', 'SG');
INSERT INTO Addresses (AddressID, UserID, Street, City, State, ZipCode, Country) VALUES (5, 5, '654 Maple St', 'Langkawi', 'Kedah', '92701', 'MY');
INSERT INTO Addresses (AddressID, UserID, Street, City, State, ZipCode, Country) VALUES (6, 6, '987 Cedar St', 'Jalan Sudirman', 'Jakarta', '72701', 'IND');
INSERT INTO Addresses (AddressID, UserID, Street, City, State, ZipCode, Country) VALUES (7, 7, '41 Jalan 1/2', 'PJ', 'Kuala Lumpur', '62701', 'MY');

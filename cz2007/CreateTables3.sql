CREATE TABLE Employee (
	EmployeeID int NOT NULL IDENTITY(1,1),
	EmployeeName varchar(255) NOT NULL,
	Salary float NOT NULL

	PRIMARY KEY (EmployeeID)
	);

CREATE TABLE Users (
	UserID int NOT NULL IDENTITY(1,1),
	UserName varchar(255) NOT NULL,

	PRIMARY KEY (UserID)
	);

CREATE TABLE Shops (
	ShopName varchar(255) NOT NULL,

	PRIMARY KEY (ShopName)
	);

CREATE TABLE Products (
	ProductName varchar(255) NOT NULL, 
	Maker varchar(255) NOT NULL,
	Category varchar(255) NOT NULL,

	PRIMARY KEY (ProductName)
	);

CREATE TABLE Orders (
	OrderID int NOT NULL IDENTITY(1,1),
	Shipping_address varchar(255) NOT NULL,
	Date_time datetime NOT NULL,
	UserID int NOT NULL,

	PRIMARY KEY (OrderID),
	FOREIGN KEY (UserID) REFERENCES Users(UserID)
	ON UPDATE CASCADE
	ON DELETE CASCADE
	);

CREATE TABLE Feedback (
	UserID int NOT NULL,
	Rating float NOT NULL,
	Date_time datetime NOT NULL,
	FeedbackComments varchar(255),
	ProductName varchar(255) NOT NULL,

	PRIMARY KEY (UserID, ProductName),
	FOREIGN KEY (UserID) REFERENCES Users(UserID)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (ProductName) REFERENCES Products(ProductName)
	ON UPDATE CASCADE
	ON DELETE CASCADE	   
	);

CREATE TABLE Complaint (
	ComplaintID int NOT NULL IDENTITY(1,1),
	ComplaintComments varchar(255) NOT NULL,
	ComplaintStatus varchar(255) NOT NULL DEFAULT 'pending',
	Filled_date_time datetime NOT NULL,
	Handled_date_time datetime,
	EmployeeID int,
	UserID int NOT NULL,

	PRIMARY KEY (ComplaintID),
	FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (UserID) REFERENCES Users(UserID)
	ON UPDATE CASCADE
	ON DELETE CASCADE
	);

CREATE TABLE Complaints_on_Shops (
	ComplaintID int NOT NULL,
	ShopName varchar(255) NOT NULL,

	PRIMARY KEY (ComplaintID, ShopName),
	FOREIGN KEY (ComplaintID) REFERENCES Complaint(ComplaintID)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (ShopName) REFERENCES Shops(ShopName)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	);

CREATE TABLE Complaints_on_Orders (
	ComplaintID int NOT NULL,
	OrderID int NOT NULL,

	PRIMARY KEY (ComplaintID, OrderID),
	FOREIGN KEY (ComplaintID) REFERENCES Complaint(ComplaintID)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	);

CREATE TABLE Products_in_Orders (
	ProductName varchar(255) NOT NULL, 
	Price float NOT NULL, 
	Quantity int NOT NULL,
	Delivery_date datetime, 
	DeliveryStatus varchar(255) NOT NULL DEFAULT 'being processed',
	ShopName varchar(255) NOT NULL,
	OrderID int NOT NULL,

	PRIMARY KEY (ProductName, OrderID, ShopName),
	FOREIGN KEY (ProductName) REFERENCES Products(ProductName)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (OrderID) REFERENCES Orders(OrderID)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION,
	FOREIGN KEY (ShopName) REFERENCES Shops(ShopName)
	ON UPDATE NO ACTION
	ON DELETE NO ACTION
	);

CREATE TABLE Products_in_Shops (
	ProductName varchar(255) NOT NULL, 
	Price float NOT NULL, 
	Quantity int NOT NULL,
	ShopName varchar(255) NOT NULL,

	PRIMARY KEY (ProductName, ShopName),
	FOREIGN KEY (ProductName) REFERENCES Products(ProductName)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (ShopName) REFERENCES Shops(ShopName)
	ON UPDATE CASCADE
	ON DELETE CASCADE
	);

CREATE TABLE Price_History (
	ProductName varchar(255) NOT NULL, 
	Price float NOT NULL,
	sDate date NOT NULL,
	eDate date,
	ShopName varchar(255) NOT NULL,

	PRIMARY KEY (ProductName, ShopName, sDate),
	FOREIGN KEY (ProductName) REFERENCES Products(ProductName)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (ShopName) REFERENCES Shops(ShopName)
	ON UPDATE CASCADE
	ON DELETE CASCADE
	);

CREATE TABLE Shops_Products_PriceVar (
	ShopName varchar(255) NOT NULL,
	ProductName varchar(255) NOT NULL, 
	sDate date NOT NULL,
	eDate date,
	Price_variation float NOT NULL,

	PRIMARY KEY (ProductName, ShopName),
	FOREIGN KEY (ProductName) REFERENCES Products(ProductName)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY (ShopName) REFERENCES Shops(ShopName)
	ON UPDATE CASCADE
	ON DELETE CASCADE
	);
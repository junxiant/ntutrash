/*
INSERT INTO Users (UserName)
VALUES ('Adam'),
		('Belle'),
		('Claire'),
		('David'),
		('Elaine'),
		('Jenna'); 
		*/

/* User */
DECLARE @i int = 1
DECLARE @j int = 51
while(@i < @j)
BEGIN
	INSERT INTO Users(UserName)
	VALUES ('Adam');
	SET @i += 1
END

DECLARE @ii int = 51
DECLARE @jj int = 101
while(@ii < @jj)
BEGIN
	INSERT INTO Users(UserName)
	VALUES ('Claire');
	SET @ii += 1
END

DECLARE @iii int = 101
DECLARE @jjj int = 151
while(@iii < @jjj)
BEGIN
	INSERT INTO Users(UserName)
	VALUES ('Jane');
	SET @iii += 1
END

/* Products */
INSERT INTO Products (ProductName, Maker, Category)
VALUES ('iPhone Xs', 'Apple', 'Electronics'),
		('Microwave', 'Panasonic', 'Kitchenware'),
		('Google Pixel 3', 'Google', 'Electronics'),
		('Macbook Pro', 'Apple', 'Electronics'),
		('Oven Toaster', 'Cornell', 'Kitchenware'),
		('Parker Sonnet', 'Parker', 'Stationery'),
		('Galaxy 10', 'Samsung', 'Electronics'),
		('Galaxy 11', 'Samsung', 'Electronics'),
		('Galaxy 12', 'Samsung', 'Electronics');

/* Employee */
INSERT INTO Employee (EmployeeName, Salary)
VALUES ('Sally', 2500),
		('Sarah', 2500),
		('Joel', 2000),
		('James', 3000),
		('Jeremy', 4000),
		('Caleb', 1500);

/* Shops */
INSERT INTO Shops (ShopName)
VALUES ('abcMobile'),
		('Motone'),
		('PenShop'),
		('Hourts'),
		('RealApple'),
		('CheapGoogle');

/* Products In Shops */
INSERT INTO Products_in_Shops (ProductName, Price, Quantity, ShopName)
VALUES ('iPhone Xs', 600, 200, 'abcMobile'),
		('Google Pixel 3', 850, 250, 'abcMobile'),
		('Microwave', 150, 50, 'Hourts'),
		('Oven Toaster', 60, 100, 'Hourts'),
		('Parker Sonnet', 250, 100, 'PenShop'),
		('iPhone Xs', 700, 300, 'RealApple'),
		('Google Pixel 3', 900, 300, 'CheapGoogle'),
		('Macbook Pro', 2000, 200, 'RealApple'),
		('Oven Toaster', 30, 400, 'Motone'),
		('Galaxy 10', 500, 400, 'abcMobile'),
		('Galaxy 10', 500, 500, 'Motone'),
		('Galaxy 11', 600, 200, 'abcMobile'),
		('Galaxy 12', 700, 200, 'abcMobile')

/* Complaint */
INSERT INTO Complaint (ComplaintComments, ComplaintStatus, Filled_date_time, Handled_date_time, EmployeeID, UserID)
VALUES ('Shop owner scammer', 'addressed', '2020-10-12', '2020-10-14', 1, 6),
		('Shop owner rude', 'being handled', '2020-10-14', null, 2, 5),
		('This shop have not shipped my order', 'addressed', '2020-07-14', '2020-07-15', 1, 4),
		('Galaxy broke', 'addressed', '2020-08-14', '2020-08-18', 1, 4),
		('Galaxy dead', 'addressed', '2020-09-14', '2020-09-15', 1, 4),
		('Galaxy die', 'addressed', '2020-10-14', '2020-10-16', 1, 4);

/* Complaints On Shops */
INSERT INTO Complaints_on_Shops (ComplaintID, ShopName)
VALUES (1, 'RealApple'),
		(2, 'Hourts'),
		(3, 'PenShop');

/* Orders */
DECLARE @x int = 1
DECLARE @y int = 101
while(@x < @y)
BEGIN
	INSERT INTO Orders (Shipping_address, Date_time, UserID)
	VALUES ('Blk 551 Paya Leba', '2020-08-11', @x);
	SET @x += 1
END

DECLARE @xx int = 1
DECLARE @yy int = 101
while(@xx < @yy)
BEGIN
	INSERT INTO Orders (Shipping_address, Date_time, UserID)
	VALUES ('Blk 550 Paya Leba', '2020-08-11', @xx);
	SET @xx += 1
END

DECLARE @q int = 1
DECLARE @w int = 11
while(@q < @w)
BEGIN
	INSERT INTO Orders (Shipping_address, Date_time, UserID)
	VALUES ('Blk 552 Paya Aber', '2020-06-11', @q);
	SET @q += 1
END

DECLARE @m int = 1
DECLARE @n int = 11
while(@m < @n)
BEGIN
	INSERT INTO Orders (Shipping_address, Date_time, UserID)
	VALUES ('Blk 223 Jurong', '2020-08-12', @m);
	SET @m += 1
END

DECLARE @mm int = 1
DECLARE @nn int = 31
while(@mm < @nn)
BEGIN
	INSERT INTO Orders (Shipping_address, Date_time, UserID)
	VALUES ('Blk 225 Jurong', '2020-07-12', @mm);
	SET @mm += 1
END

/* Complaint On Orders */
INSERT INTO Complaints_on_Orders (ComplaintID, OrderID)
VALUES (4, 4),
		(5, 5),
		(6, 6);

/* Products In Orders */
DECLARE @e int = 1
DECLARE @r int = 101
while(@e < @r)
BEGIN
	INSERT INTO Products_in_Orders (ProductName, Price, Quantity, Delivery_date, DeliveryStatus, ShopName, OrderID)
	VALUES ('Galaxy 10', 500, 1, '2020-08-14', 'delivered', 'abcMobile', @e)
	SET @e += 1
END

DECLARE @ee int = 101
DECLARE @rr int = 201
while(@ee < @rr)
BEGIN
	INSERT INTO Products_in_Orders (ProductName, Price, Quantity, Delivery_date, DeliveryStatus, ShopName, OrderID)
	VALUES ('iPhone Xs', 700, 1, '2020-08-15', 'delivered', 'RealApple', @ee)
	SET @ee += 1
END

DECLARE @t int = 201
DECLARE @u int = 211
while(@t < @u)
BEGIN
	INSERT INTO Products_in_Orders (ProductName, Price, Quantity, Delivery_date, DeliveryStatus, ShopName, OrderID)
	VALUES ('Galaxy 10', 500, 1, '2020-06-14', 'delivered', 'abcMobile', @t)
	SET @t += 1
END

DECLARE @g int = 211
DECLARE @h int = 221
while(@g < @h)
BEGIN
	INSERT INTO Products_in_Orders (ProductName, Price, Quantity, Delivery_date, DeliveryStatus, ShopName, OrderID)
	VALUES ('Galaxy 10', 500, 1, '2020-08-18', 'delivered', 'Motone', @g)
	SET @g += 1
END

DECLARE @gg int = 221
DECLARE @hh int = 251
while(@gg < @hh)
BEGIN
	INSERT INTO Products_in_Orders (ProductName, Price, Quantity, Delivery_date, DeliveryStatus, ShopName, OrderID)
	VALUES ('Galaxy 10', 500, 1, '2020-07-18', 'delivered', 'Motone', @gg)
	SET @gg += 1
END

/* Feedback */
DECLARE @a int = 1
DECLARE @s int = 101
while(@a < @s)
BEGIN
	INSERT INTO Feedback (UserID, Rating, Date_time, FeedbackComments, ProductName)
	VALUES (@a, 5, '2020-08-15', ' ', 'Macbook Pro')
	SET @a += 1
END

DECLARE @aa int = 1
DECLARE @ss int = 51
while(@aa < @ss)
BEGIN
	INSERT INTO Feedback (UserID, Rating, Date_time, FeedbackComments, ProductName)
	VALUES (@aa, 4, '2020-08-16', ' ', 'iPhone Xs')
	SET @aa += 1
END

DECLARE @aaa int = 51
DECLARE @sss int = 121
while(@aaa < @sss)
BEGIN
	INSERT INTO Feedback (UserID, Rating, Date_time, FeedbackComments, ProductName)
	VALUES (@aaa, 5, '2020-08-16', ' ', 'iPhone Xs')
	SET @aaa += 1
END

DECLARE @aaaa int = 121
DECLARE @ssss int = 151
while(@aaaa < @ssss)
BEGIN
	INSERT INTO Feedback (UserID, Rating, Date_time, FeedbackComments, ProductName)
	VALUES (@aaaa, 4, '2020-08-16', ' ', 'Macbook Pro')
	SET @aaaa += 1
END

INSERT INTO Price_History (ProductName, Price, sDate, eDate, ShopName)
VALUES  ('iPhone Xs', 650, '2020-08-10', '2020-08-11', 'abcMobile'),
		('iPhone Xs', 640, '2020-08-12', '2020-08-13', 'abcMobile'),
		('iPhone Xs', 630, '2020-08-14', '2020-08-15', 'abcMobile'),
		('iPhone Xs', 620, '2020-08-06', '2020-08-17', 'abcMobile'),
		('iPhone Xs', 610, '2020-10-09', '2020-10-10', 'abcMobile'),
		('iPhone Xs', 605, '2020-10-10', '2020-10-20', 'abcMobile'),
		('Macbook Pro', 2500, '2020-10-08', '2020-10-09', 'RealApple'),
		('Macbook Pro', 2100, '2020-10-09', '2020-10-20', 'RealApple');





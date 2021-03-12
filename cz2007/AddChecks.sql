ALTER TABLE Employee
ADD CHECK (Salary>0);

ALTER TABLE Feedback
ADD CHECK (Rating BETWEEN 1 AND 5);

ALTER TABLE Products_in_Orders
ADD CHECK (Price >= 0 and Quantity >= 0);

ALTER TABLE Products_in_Shops
ADD CHECK (Price >= 0 and Quantity >= 0);


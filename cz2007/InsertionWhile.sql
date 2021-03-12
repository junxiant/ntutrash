DECLARE @i int = 0
DECLARE @j int = 100
DECLARE @DT Datetime = '2020-06-01'

while(@i < @j)
BEGIN
	INSERT INTO Feedback (UserID, Rating, Date_time, Comment, ProductName)
	VALUES (@i, 5, '2020-08-12 11:20:00', '', 'Macbook Pro');
	SET @i += 1
END

while(@i < @j)
BEGIN
	INSERT INTO Users(UserName)
	VALUES ('Adam');
	SET @i += 1
END
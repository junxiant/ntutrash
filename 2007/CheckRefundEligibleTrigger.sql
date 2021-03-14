CREATE TRIGGER CheckRefundEligible
ON  Products_in_Orders
   AFTER  UPDATE
AS
BEGIN
IF EXISTS(SELECT x.Delivery_date FROM inserted x
WHERE x.DeliveryStatus = 'returned' AND DATEDIFF(DAY, x.Delivery_date, GETDATE()) > 30
GROUP BY x.Delivery_date
HAVING COUNT(*) > 0)
 ROLLBACK TRANSACTION
END
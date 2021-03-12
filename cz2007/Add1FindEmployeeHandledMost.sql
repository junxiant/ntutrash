SELECT E.EmployeeName, COUNT(C.EmployeeID) as 'Num_Of_Handled_Complaints'
FROM Employee as E, Complaint as C
WHERE C.ComplaintStatus = 'addressed'
AND E.EmployeeID = C.EmployeeID
GROUP BY E.EmployeeName
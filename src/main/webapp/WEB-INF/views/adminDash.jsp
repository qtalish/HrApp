<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin DashBoard</title>
</head>

<body>
	<%@include file="logout.jsp"%>
	<div align="center">
		<h1>Hello Hr</h1>

		<br> <br> <br> 
		<a href="/HrApp/viewEmployees">View	All Employees</a><br> <br> <a href="/HrApp/register">AddEmployee</a> <br><br> 
			<a href="/HrApp/attendance">View Attendance</a> <br><br> 
			<a href="/HrApp/messageEmployee">Message</a>
	</div>
</body>
</html>
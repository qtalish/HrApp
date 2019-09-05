<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<meta charset="ISO-8859-1">
<title>Admin DashBoard</title>
</head>

<body>
	<%@include file="logout.jsp"%>
	<div align="center">
	

	<!-- 	<br> <br> <br> <a href="/HrApp/viewEmployees">View
			All Employees</a><br> <br> <a href="/HrApp/register">Add
			Employee</a> <br> <a href="/HrApp/attendance">View Attendance</a>
			 -->
			 
			<nav class="navbar navbar-expand-lg navbar-light bg-light">
			  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/HrApp/viewEmployees">View All Employees <!-- <span class="sr-only">(current)</span> --></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/HrApp/register">Add Employee</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/HrApp/attendance">Attendence</a>
      </li>
	</div>
	</nav>
		<h1>Hello HR</h1>
</body>
</html>
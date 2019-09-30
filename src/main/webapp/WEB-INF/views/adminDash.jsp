<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" isErrorPage="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>

<script src="./resources/JS/jquery-3.3.1.min.js" type="text/javascript"></script>
<title>Employee List</title>
<script src="./resources/JS/emp.js" type="text/javascript"></script>
<link href="./resources/images/kgate.png" rel="shortcut icon"
	type="image/png" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


<meta charset="ISO-8859-1">
<title>Admin DashBoard</title>
</head>

<body>


		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<a class="navbar-brand" href ="https://www.kgate.in/">K-Gate</a>

			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/attendance">Attendence</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/messageEmployee">Message</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/workReport">Work Report</a></li>
					<li class="nav-item"><a class="nav-link"
					href="/HrApp/hrWorkReport">HR Work Report</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Employee </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/HrApp/register">Add Employee</a> <a
							class="dropdown-item" href="/HrApp/viewEmployees">View All
							Employees </a>
					</div></li>
			</ul>
			<div class="navbar-collapse collapse w-25 order-3 dual-collapse2">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<form action="logout" method="get">
							<button class="btn btn-link" style="color: white;">Log
								Out</button>
						</form>
					</li>
				</ul>
			</div>
		</nav>
		
		<div align="center">
		<h1 style="color: maroon">Employee List</h1>

		<br> <input id="myInput" type="text"
			placeholder="Search Employee Here.." /><br></br>
		<table border="1" id="tbl" class="table">

			<!-- <th style="color: red">First Name</th> -->
			<th style="color: red">Employee Code</th>
			<th style="color: red">Name</th>
			<th style="color: red">Email</th>
			<th style="color: red">Designation</th>
			<th style="color: red">Mobile No.</th>
			<th style="color: red">Action</th>
		</table>
		<br> <br>
		<div align="center">
			Pages:
			<p id="n"></p>
		</div>
	
	</div>




</body>
<footer class="page-footer font-small special-color-dark " >
 <div class="container">
</div>
 <div class="footer-copyright text-center py-5">© 2019 Copyright:
 <a href="http://www.kgate.in/">K-Gate</a>
 </div>
</footer>
</html>
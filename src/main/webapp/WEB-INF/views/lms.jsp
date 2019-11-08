<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leave Management System</title>

<script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>
<link href="./resources/css/bootstrap.css" rel="stylesheet" />
<script src="./resources/JS/sweetalert.min.js" type="text/javascript"></script>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">


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
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<!-- <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->

</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/attendance">Attendence</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/messageEmployee">Message</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/workReport">Report</a></li>
				<li class="nav-item"><a class="nav-link"
				href="/HrApp/lms">L.M.S</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"> Employee </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="/HrApp/register">Add Employee</a><a
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
	<br>







<div class="container">
<table class="table" border="1">
<th align="center">Code</th>
<th align="center">Name</th>
<th align="center">Subject :</th>
<th align="center">Dates</th>
<th align="center">Message</th>
<th align="center">Status</th>
<th align="center">Action</th>


<c:forEach items="${leaves}" var="l">
<tr>
<td> ${l.empCode}</td>
<td> ${l.fname} ${l.lname} </td>
<td> ${l.subject}</td>
<td> ${l.fromDate} To ${l.toDate}</td>
<td>${l.message}</td>
<td>${l.status}</td>
<td><form:form action="btn" method="get"><input name="id" type="hidden" value="${l.id}"><button name="button" value="Approved" class="btn btn-link">Approved</button><button name="button" value="Rejected" class="btn btn-link">Reject</button></form:form>
</td>
</tr>
</c:forEach>

</table>
	</div>
	
	
</body>
</html>
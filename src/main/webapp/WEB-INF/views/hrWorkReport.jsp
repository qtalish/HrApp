<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HR Work Report</title>
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
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
					href="/HrApp/workReport">Report</a></li>
					<li class="nav-item"><a class="nav-link"
				    href="/HrApp/lms">L.M.S</a></li>
				
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
		<br>
<div align="center">		
<a href="/HrApp/workReport">Developer Report</a> | <a href="/HrApp/hrWorkReport">HR Report</a></div>
<br>
<div align="center">
<table>
<form:form action="searchHrWorkReport" method="get">
<tr><td>
<select name="month" class="dropdown-header">
				<option>${mon}</option>
				<c:forEach items="${months}" var="months">
					<option>${months}</option>
				</c:forEach>
			</select></td>
<td>
			<select name="year" class="dropdown-header">
			<option>${year}</option>
				<c:forEach items="${years}" var="years" >
					<option>${years}</option>
				</c:forEach>
			</select></td>
<td>			
<input type="submit" class="btn btn-link" value="Search"/></td></tr>
</form:form></table>
</div>
<br>


<table class="table table-hover" border="1">
					<thead>
						<th>Name</th>
						<th>Resource Called</th>
						<th>Resource Interviewed</th>
						<th>Task For Tomorrow</th>
						<th>Interview Planned For Tomorrow</th>
						<th>Total Call</th>
						<th>Impediments</th>
						<th>Date</th>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="user">
							<tr>
								<td>${user.fname} ${user.lname}</td>
								<td>${user.resourcesCalled}</td>
								<td>${user.resourcesInterviewed}</td>
								<td>${user.taskForTomorrow}</td>
								<td>${user.interviewPlannedForTomorrow}</td>
								<td>${user.totalCalls}</td>
								<td>${user.impediments}</td>
								<c:set var="date" value="${user.date}"></c:set>
								<td><fmt:formatDate value="${date}" pattern="dd-MM-yyyy"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

</body>
</html>
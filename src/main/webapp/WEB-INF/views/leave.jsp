<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leave Application</title>

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

<script type="text/javascript">
            function leave(){
            	var fromDate = document.getElementById("fromDate").value;
            	var toDate= document.getElementById("toDate").value;
            var date1 = new Date(fromDate)
            var date2 = new Date(toDate)

            var oneDay = 24 * 60 * 60 * 1000;
            var days = Math.abs((date1.getTime() - date2.getTime()) / (oneDay));
            var diffDays = days + 1;
            	alert("You are applying for "+diffDays +" days of Leave");
            	if(diffDays > "${ul.balanceLeaves}"){
            		var extraleave = diffDays - ${ul.balanceLeaves};
            		return confirm("You have taken "+extraleave +" leave more than your balance leave. It will be counted as paid leave");
            	}
            }
        </script>
</head>
<body>
<c:if test="${type == 'HR'}">

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/hrDailyReport">Daily Report</a></li>
				<li class="nav-item"><a class="nav-link"
				href="/HrApp/hrCallingSheet">Calling Sheet</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/downloadSalarySlip">Salary Slip Download</a></li>
				

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
	</c:if>
		
		
		
<c:if test="${type == 'DEVELOPER'}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/dailyReport">Daily Report</a></li>

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
	</c:if>
	<br>
	<div align="right">
	<table border="1">
	<th style="color: red">Balance Leave</th>
	<tr>
	<td> &nbsp; &nbsp; &nbsp; &nbsp; ${ul.balanceLeaves}</td>
	</table>
	</div>
	<div class="container" align="center">
		<p style="color: red;">${msg}</p>


		<h2>Leave Application Form</h2>
		<table class="table">
			<form:form action="send" method="post" modelAttribute="leave"
				enctype="multipart/form-data" onsubmit="return leave()">
				<form:input path="id" type="hidden" />

				<tr>
					<td>Subject :</td>
					<td><form:input path="subject" class="form-control" /></td>
				</tr>
				<tr>
					<td>From :</td>
					<td><form:input path="fromDate" type="date"
							class="dropdown-header" id="fromDate"/></td>
				</tr>
				<tr>
					<td>To :</td>
					<td><form:input path="toDate" type="date"
							class="dropdown-header" id="toDate"/></td>
				</tr>
				<tr>
					<td>Message :</td>
					<td><form:textarea path="message" class="form-control" /></td>
				</tr>
				<tr>
					<td><form:label path="content">Document</form:label></td>
					<td><input type="file" name="file" id="file"></input></td>
				</tr>

				<tr>
					<td></td>
					<td><button class="btn btn-primary" id="submit">Submit</button>
					</td>
				</tr>
			</form:form>
		</table>
		<br>
	</div>
</body>
</html>
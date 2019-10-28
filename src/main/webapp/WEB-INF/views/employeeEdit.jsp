<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Employee Edit</title>
<link href="./resources/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>
<script src="./resources/JS/formvalid.js" type="text/javascript"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>


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

<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

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
	<br>
	</c:if>
	
	
	<c:if test="${type == 'OPERATIONS'}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/dailyReport">Daily Report</a></li>
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
	
	
	<c:if test="${type == 'ACCOUNTS'}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/dailyReport">Daily Report</a></li>
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
	
	
	
	<c:if test="${type == 'MARKETING'}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/dailyReport">Daily Report</a></li>
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
	
 
	<p align="center">
		<a href="editEmployeeProfile?mylocale=en">English</a> | <a
			href="editEmployeeProfile?mylocale=de"> मराठी </a> | <a
			href="editEmployeeProfile?mylocale=gj">ગુજરાતી</a>

	<div class="container">

		<h3>
			<spring:message code="user.headerMessage" />
		</h3>

		<table class="table">
			<p id="head"></p>

			<form:form action="saveEmployee" method="post" modelAttribute="user">
				<form:input path="id" type="hidden" class="form-control" />
				<tr>
					<td><spring:message code="user.empCode" /></td>
					<td><form:input path="empCode" class="form-control" /></td>
					<td></td>
				</tr>
				<tr>
					<td><spring:message code="user.fname" /></td>
					<td><form:input id="fname" path="fname" onblur="first()"
							class="form-control" /></td>
					<td><p id="fn"></p></td>
				</tr>
				<tr>
					<td><spring:message code="user.mname" /></td>
					<td><form:input id="mname" path="mname" onblur="middle()"
							class="form-control" /></td>
					<td><p id="mn"></p></td>
				</tr>

				<tr>
					<td><spring:message code="user.lname" /></td>
					<td><form:input id="laname" path="lname" onblur="lastName()"
							class="form-control" /></td>
					<td><p id="lastName"></p></td>
				</tr>
				<tr>
					<td><spring:message code="user.email" /></td>
					<td><form:input id="email" path="email" onblur="emailId()"
							class="form-control" /></td>
					<td><p id="em"></p></td>
				</tr>
				<tr>
					<td><spring:message code="user.dob" /></td>
					<td><form:input path="dob" type="text" id="datepicker"
							class="form-control" /></td>
					<td></td>

				</tr>
				<tr>
					<td><spring:message code="user.bloodGroup" /></td>
					<td><form:input path="bloodGroup" id="bloodGroup"
							onblur="bgValidate()" class="form-control" /></td>
					<td><p id="bg"></td>
				</tr>
				<tr>
					<td><spring:message code="user.aadhar" /></td>
					<td><form:input path="aadhar" id="aadhar"
							onblur="aadharValidate()" class="form-control" /></td>
					<td><p id="aa"></p></td>
				</tr>
				<tr>
					<td><spring:message code="user.pan" /></td>
					<td><form:input path="pan" id="pan" onblur="validatePAN()"
							class="form-control" /></td>
					<td><p id="pa"></p></td>
				</tr>

				<tr>
					<td><spring:message code="user.designation" /></td>
					<td><form:input path="designation" class="form-control" /></td>
					<td><p style="color: red" id="de"></p></td>
				</tr>


				<tr>
					<td><spring:message code="user.joiningDate" /></td>
					<td><form:input path="joiningDate" type="date"
							id="joiningDate" class="form-control" /></td>
					<td><p id="jd"></p></td>
				</tr>
				<tr>
					<td><spring:message code="user.address" /></td>
					<td><form:input id="address" path="address"
							class="form-control" /></td>
					<td><p id="ad"></p></td>
				</tr>

				<tr>
					<td><spring:message code="user.mob" /></td>
					<td><form:input path="mob" id="mob" onblur="mobile()"
							class="form-control" /></td>
					<td><p id="mo"></p></td>
				</tr>

				<tr>
					<td><spring:message code="user.salary" /></td>
					<td><form:input path="salary" id="salary" onblur="sal()"
							class="form-control" /></td>
					<td><p id="sa"></p></td>
				</tr>
				<tr>
					<td><spring:message code="user.password" /></td>
					<td><form:input path="password" id="password" onblur="psw()"
							class="form-control" /></td>
					<td><p id="psw"></p></td>
				</tr>


				<tr>
					<td><spring:message code="user.submit" var="submit" /></td>
					<td><input type="submit" class="btn btn-primary" id="submit"
						value="${submit}" /></td>
					<td></td>
				</tr>
			</form:form>
		</table>
	</div>





	<p style="color: red;">${msg}</p>
	<script type="text/javascript">
		$(function() {
			var date = new Date();
			var currentMonth = date.getMonth();
			var currentDate = date.getDate();
			var currentYear = date.getFullYear();
			$('#datepicker').datepicker({
				maxDate : new Date(currentYear, currentMonth, currentDate)
			});
		});
	</script>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HR Daily Report</title>

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

<div class="container">
<table class="table">
<form:form action="submitHrDailyReport" modelAttribute="hdr">
<form:input path="id" type="hidden" />

 <tr><td>Date </td>	<td><form:input type="date" onload="getDate()" path="date" id="date" class="form-control" readonly="true" /></td></tr> 

<tr><td>Total Calls </td>	<td><form:input path="totalCalls" class="form-control"/></td></tr>

<tr><td>Resource Called </td>	<td><form:textarea path="resourcesCalled" class="form-control"/></td></tr>

<tr><td>Resource Interviewed </td>	<td><form:textarea path="resourcesInterviewed" class="form-control"/></td></tr>

<tr><td>Task For Tomorrow </td>	<td><form:textarea path="taskForTomorrow" class="form-control"/></td></tr>

<tr><td>Interview Planned For Tomorrow </td>	 <td><form:textarea path="interviewPlannedForTomorrow" class="form-control"/></td></tr>

<tr><td>Impediments </td>	<td><form:textarea path="impediments" class="form-control"/></td></tr>

<tr><td></td><td><button class="btn btn-primary">Submit</button></td><td></td></tr>

</form:form>
</table>
</div>

<script type="text/javascript">
function getDate(){
    var today = new Date();

document.getElementById("date").value = today;
}

</script>
</body>
</html>
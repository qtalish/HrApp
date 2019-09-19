<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Documents</title>

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

<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
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
					href="/HrApp/workReport">Work Report</a></li>

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
 <div class="alert alert-success" align="center" >
 	<strong>${msg}</strong> 
	</div>
<table class="table">
	<form:form method="post" action="addAdminDocument"
		modelAttribute="userDocument" enctype="multipart/form-data" >
		<form:errors path="*" cssClass="error" /> 
		<form:input path="empCode" type="hidden" />

		
			<tr>
				<td><form:label path="dname">Name</form:label></td>
				<td><form:input path="dname" class="form-control"/></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:textarea path="description" class="form-control" /></td>
			</tr>
			
			<tr>
				<td><form:label path="description">Document</form:label></td>
				<td>
    			<input type="file" name="file" class="file-field" id="file"/>
				<td>
			</tr>
			<tr>
			<td></td>	
				<td><button class="btn btn-primary">Upload</button></td>
			</tr>
		
	</form:form>
	</table>  
	
</body>
</html>
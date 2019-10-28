<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Documents</title>

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
	
 <div class="alert alert-success" align="center" >
 	<strong>${msg}</strong> 
	</div>
<table class="table">
	<form:form method="post" action="addAdminDocument"
		modelAttribute="userDocument" enctype="multipart/form-data">
		<form:errors path="*" cssClass="error" />


		<form:input path="empCode" type="hidden" />

		
			<tr>
				<td><form:label path="dname">Name</form:label></td>
				<td><form:input path="dname"  class="form-control" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:textarea path="description"  class="form-control" /></td>
			</tr>
			<tr>
				<td><form:label path="document">Document</form:label></td>
				<td><input type="file" name="file" id="file" class="file-field"/></td>
			</tr>
			<tr>
			<td></td>
			<td><button class="btn btn-primary">Upload</button></td>
			</tr>
		
	</form:form>
	</table>
	<br> 
	
	<h1>List of Documents</h1>
	
			<table class="table" border="1">

					<th style="color: red">Id</th>
					<th style="color: red">Name</th>
					<th style="color: red">Description</th>
					<th style="color: red">Document Type</th>
					<th style="color: red">Action</th>

		
					  <c:forEach items="${uc}" var="doc">
						<tr>
							<td>${doc.id}</td>
							<td>${doc.dname}</td>
							<td>${doc.description}</td>
							<td>${doc.documentType}</td>
							
							<td><a href="<c:url value='/downloadDoc-${doc.id}' />"
								class="btn btn-success custom-width">Download</a>
							<a href="<c:url value='/delete-document-${doc.id}' />"
									class="btn btn-danger custom-width">Delete</a></td>
						</tr>
					</c:forEach>  
				</tbody>
			</table>
	
</body>
</html>

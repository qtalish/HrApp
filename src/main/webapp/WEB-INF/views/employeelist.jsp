<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Users List</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead"><h1>List of Users</h1> </span>
			</div>
			<div class="tablecontainer">
				<table class="table table-hover" border="1">
					<thead>

						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Employee Code</th>
						<th width="100">Update</th>
						<th width="100">Delete</th>
					</thead>
					<tbody>
						<c:forEach items="${users}" var="user">
							<tr>
								<td>${user.fname}</td>
								<td>${user.lname}</td>
								<td>${user.email}</td>
								<td>${user.empCode}</td>
								<td><a href="<c:url value='/edit-user-${user.id}' />"
									class="btn btn-success custom-width">edit</a></td>
								<td><a href="<c:url value='/delete-user-${user.id}' />"
									class="btn btn-danger custom-width">delete</a></td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="well">
			<a href="<c:url value='/newuser' />">Add New User</a>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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


<body bgcolor="#ADD8E6">
 <tr> <form:form action="backToAdmin" method="post"><input type="submit" value="Back"></form:form> </tr>
<%@include file="home.jsp" %>
<%@include file="logout.jsp" %>
	<div align="center">
		<h1 style="color: maroon">Employee List</h1>
		<div class="tablecontainer">
			<table class="table table-hover" border="1">

					<th style="color: red">Employee Code</th>
					<th style="color: red">Name</th>
					<th style="color: red">Email</th>
					<th style="color: red">Designation</th>
					<th style="color: red">Mobile No.</th>
					<th style="color: red">Action</th>

 					<c:forEach items="${listEmp}" var="user">
						<tr>
							<td>${user.empCode}</td>
							<td>${user.fname} ${user.lname}</td>
							<td>${user.email}</td>
							<td>${user.designation}</td>
							<td>${user.mob}</td>
							
							<td><a href="<c:url value='editEmployeeAjax?id=${user.id}' />"
								class="btn btn-success custom-width">Edit</a>
							<a href="<c:url value='#' onclick='deleteEmp(${user.id},${user.email});' />"
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
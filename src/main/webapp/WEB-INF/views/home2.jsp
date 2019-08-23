<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<title>Employee List</title>
<spring:url value="/resources/JS/emp.js" var="c"></spring:url>
<script src="${c}"></script>
</head>
<body bgcolor="#ADD8E6">
<%@include file="home.jsp" %>
<%@include file="logout.jsp" %>
	<div align="center">
		<h1 style="color: maroon">Employee List</h1>
		<table border="1" id="tbl">

			<!-- <th style="color: red">First Name</th> -->
			<th style="color: red">Employee Code</th>
			<th style="color: red">Name</th>
			<th style="color: red">Email</th>
			<th style="color: red">Designation</th>
			<th style="color: red">Mobile No.</th>
			<th style="color: red">Action</th>
		</table>
		<br>
		<br>
		<div align="center">
			Pages:
			<p id="n"></p>
		</div>
		<tr>

		</tr>
	</div>
</body>
</html>
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
	<script src="./resources/JS/jquery-3.3.1.min.js" type="text/javascript"></script>
<title>Employee List</title>
<spring:url value="/resources/JS/emp.js" var="c"></spring:url>
<script src="${c}"></script>
</head>
<body bgcolor="#ADD8E6">
 <tr> <form:form action="backToAdmin" method="post"><input type="submit" value="Back"></form:form> </tr>
<%@include file="home.jsp" %>
<%@include file="logout.jsp" %>

	<div align="center">
		<h1 style="color: maroon">Employee List</h1>
		
<%-- 		<form action="<spring:url value="/searchEmployee"/>">

		Search Employee: <input id="myInput" type="text" name="txt" placeholder="Enter Name To Search" value="${param.txt}" />

			<button>
				<div id="bt">Find</div>
			</button>
		</form> --%>
<br><br>		 <input id="myInput"  onkeyup="myFunction2()" type="text" placeholder="Search Employee Here.."  /><br></br> 
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
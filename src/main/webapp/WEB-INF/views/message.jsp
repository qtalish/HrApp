<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>
<script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>
<%-- <spring:url value="/resources/JS/emp.js" var="c"></spring:url>
 --%>
<!--  <link rel="stylesheet" href="./resources/css/bootstrap.css"> -->
</head>
<body bgcolor="#ADD8E6">
	<form:form action="backToEmployee" method="post">
		<input type="submit" value="Back">
	</form:form>
	<div class="container" align="center">
		<p style="color: red;">${msg}</p>
		<%@include file="home.jsp"%>
		<%@include file="logout.jsp"%>
		<%-- 	<form:form action="backToEmployee" method="post"><input type="submit" value="Back"></form:form>
 --%>
		<h2>Message Employee</h2>
		<table class="table">
			<form:form action="sendMail" method="post" modelAttribute="message">
				<form:input path="id" type="hidden" />

				<tr>
					<td>Subject :</td>
					<td><form:input path="subjectMessage" /></td>
				</tr>
				<tr>
					<td>Message :</td>
					<td><form:textarea path="messageEmp" /></td>
				</tr>
				<tr>
					<td>To :</td>
					<td><form:select id="myslct" path="to" items="${emp}"
							multiple="true" name="myselect"/>
				</tr>

				<tr>
					<td><input type="submit" id="submit" value="Send" /></td>
				</tr>
			</form:form>
		</table>
		<button class="btn btn-primary" onclick="selectAll();">Select
			All</button>
		<button class="btn btn-primary" onclick="deselectAll();">DeSelect
			All</button>
	</div>
	<script>
			selectAll = function() {
				$('#myslct option').prop('selected', true);
			}
			deselectAll = function() {
				$('#myslct option').prop('selected', false);
			}
	</script>
</body>
</html>
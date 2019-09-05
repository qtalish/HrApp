<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="backToEmployee" method="post"><input type="submit" value="Back"></form:form>
<div class="container" align="center">
	<p style="color: red;">${msg}</p>
	<%@include file="home.jsp"%>
	<%@include file="logout.jsp"%>
<%-- 	<form:form action="backToEmployee" method="post"><input type="submit" value="Back"></form:form>
 --%>	<h2>Leave Form</h2>
	<table class="table">
<form:form action="send" method="post" modelAttribute="leave" enctype="multipart/form-data">
			<form:input path="id" type="hidden" />
			
			<tr>
				<td>Subject :</td>
				<td><form:input path="subject" /></td>
			</tr>
			<tr>
				<td>From :</td>
				<td><form:input path="fromDate" type="date" /></td>
			</tr>
			
			<tr>
				<td>To :</td>
				<td><form:input path="toDate" type="date" /></td>
			</tr>
			<tr>
				<td>Message :</td>
				<td><form:textarea path="message" /></td>
			</tr>
			<tr>
				<td><form:label path="content">Document</form:label></td>
				<td><input type="file" name="file" id="file"></input></td>
</tr>
			
			<tr>
				<td><input type="submit" id="submit" value="Submit" /></td>
			</tr>
		</form:form>
		</table>
		</div>
		</body>
		</html>
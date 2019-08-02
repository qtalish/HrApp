<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<table align="center">
		<form:form action="authenticate" method="post" modelAttribute="user">

			<tr>
				<td>User:</td>
				<td><form:input path="email" /></td>
				<p>${msg}</p>
			</tr>
			<tr>
				<td>Password:</td>
				<td><form:input path="password" type="password" /></td>
			</tr>
			<tr>

				<td><form:select path="userType" name="userType">

						<form:options items="${userType}" />

					</form:select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Login" /></td>
			</tr>
		</form:form>
	</table>




</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script type="text/javascript">
	history.pushState(null, null, location.href);
	window.onpopstate = function() {
		history.go(1);
	};
	window.onbeforeunload = null;
</script>

<title>Login</title>
<script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="./resources/JS/emp.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>

<body bgcolor="#ADD8E6">
				<h3><center><p style="color: red">${msg}</p></center></h3>
	<table align="center">
		<form:form action="authenticate" method="post" modelAttribute="user">

			<tr>
				<td>User:</td>
				<td><form:input path="email" /></td>
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
		<a href="#" onclick="forgot()">Forgot Password</a>
	</table>




</body>
</html>
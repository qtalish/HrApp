<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
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
<script src="./resources/JS/script.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
 <!-- <link rel="stylesheet" href="./resources/css/bootstrap.min.css">
  <link rel="stylesheet" href="./resources/css/bootstrap.css"> -->
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
				<td></td>
				<td><input type="submit"   class="btn btn-success" value="Login" /></td>
				

			</tr>
		</form:form>
		<a href="#" onclick="forgot()">Forgot Password</a>
	</table>
<!--     <button style="display:none;" id="logout">Logout</button>           
    <div id="status"></div>
 -->


</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>


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

<!--  <link rel="stylesheet" href="./resources/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="./resources/css/login.css">
</head>

<body>

	<div class="sidenav">
		<div class="login-main-text">
			<h2>
				<img src="./resources/images/LOGO.png">
			</h2>
		</div>
	</div>
	<div class="main">
		<div class="col-md-5 col-sm-12">
			<div class="login-form">
				<p style="color: red" align="center">${msg}</p>
				<form:form action="authenticate" method="post" modelAttribute="user">
					<div class="form-group">
						<label>User Name</label>
						<form:input class="form-control" path="email" />
					</div>
					<div class="form-group">
						<label>Password</label>
						<form:input path="password" class="form-control" type="password" />
					</div>


					<button type="submit" class="btn btn-black">Login</button>
					<a href="#" onclick="forgot()">Forgot Password</a>

				</form:form>
			</div>
		</div>
	</div>
</body>
</html>
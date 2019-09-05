<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>     
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<!-- <script type="text/javascript" src="./resources/JS/bootstrap.min.js"></script> -->
<link href="./resources/css/bootstrap.css" rel="stylesheet"/>
<script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>

<script src="./resources/JS/formvalid.js" type="text/javascript"></script>

<!-- <script>
myFunction=function(id){
	var regex = /^[a-zA-Z]+$/;
	var name = id.value;
	console.log(name)
	console.log(id.value)
	 $( "p" ).remove( ":contains('Invalid Format')" );
	if(regex.test(name)){
		document.getElementById("p1").innerHTML = "Invalid Format"
	}
 
}
function validate(id)
{
    var regex = /^[a-zA-Z ]{2,30}$/;
    var ctrl =  document.getElemetnById(id);

    if (regex.test(ctrl.value)) {
        return true;
    }
    else {
        return false;
    }
}

</script> -->
</head>
<body>
<div class="container">
	<p style="color: red;">${msg}</p>
 <form:form action="backToAdmin" method="post"><input type="submit" value="Back"></form:form> 
	<%@include file="home.jsp"%>
	<%@include file="logout.jsp"%>
	<h2>Registration Form</h2>
	<table class="table">
<p id="head"></p>
		<form:form action="save" method="post" modelAttribute="user">
			<form:input path="id" type="hidden" />
			<tr>
				<td>Employee Code :</td>
				<td><form:input path="empCode" /></td>
			</tr>
			<tr>
				<td>First Name :</td><!-- vb ="myFunction(this)" -->
				<td><form:input  id="fname" path="fname" /><p id="p1" style="color:red;"></p></td>
			</tr>
			<tr>
				<td>Middle Name :</td>
				<td><form:input id="mname" path="mname" /></td>
			</tr>

			<tr>
				<td>Last Name :</td>
				<td><form:input id="lname" path="lname" /></td>
			</tr>
			<tr>
				<td>Email :</td>
				<td><form:input id="email" path="email" /><p id="p2" style="color:red;"></p></td>
			</tr>
			<tr>
				<td>D.O.B :</td>
				<td><form:input path="dob" type="date" /></td>
			</tr>
			<tr>
				<td>Blood Group :</td>
				<td><form:input path="bloodGroup" /></td>
			</tr>
			<tr>
				<td>Aadhar Number :</td>
				<td><form:input path="aadhar" /></td>
			</tr>
			<tr>
				<td>Pan Number :</td>
				<td><form:input path="pan" /></td>
			</tr>
			<tr>
				<td>Designation :</td>
				<td><form:input path="designation" /></td>
			</tr>
			<tr>
				<td>Joining Date :</td>
				<td><form:input path="joiningDate" type="date" /></td>
			</tr>
			<tr>
				<td>Address :</td>
				<td><form:input id="address" path="address" /><p id="p3" style="color:red;"></p></td>
			</tr>
			<tr>
				<td>Salary :</td>
				<td><form:input path="salary" /></td>
			</tr>
			<tr>
				<td>Password :</td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" id="submit" value="Register" /></td>
			</tr>
		</form:form>
	</table>
</div>
</body>
</html>
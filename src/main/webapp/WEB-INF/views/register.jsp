<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<!-- <script type="text/javascript" src="./resources/JS/bootstrap.min.js"></script> -->
<link href="./resources/css/bootstrap.css" rel="stylesheet"/>
<script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>

<script src="./resources/JS/formvalid.js" type="text/javascript"></script>

<script>
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

</script>
</head>
<body>


<p align="center"><a href="register?mylocale=en">English</a> | <a href="register?mylocale=de">
मराठी   </a> | <a href="register?mylocale=gj">ગુજરાતી</a> </p>
<div class="container">
	<p style="color: red;">${msg}</p>
	<center><h3><spring:message code="user.headerMessage"/></h3></center>
<%-- 	<%@include file="home.jsp"%>
	<%@include file="logout.jsp"%>  --%>
	
	<table class="table">
<p id="head"></p>
		<form:form action="save" method="post" modelAttribute="user">
			<form:input path="id" type="hidden" />
			<tr>
				<td><spring:message code="user.empCode"/></td>
				<td><form:input path="empCode" /></td>
			</tr>
			<tr>
				<td><spring:message code="user.fname"/></td>
				<td><form:input  id="fname" path="fname" vb ="myFunction(this)"/><p id="p1" style="color:red;"></p></td>
			</tr>
			<tr>
				<td><spring:message code="user.mname"/></td>
				<td><form:input id="mname" path="mname" /></td>
			</tr>

			<tr>
				<td><spring:message code="user.lname"/></td>
				<td><form:input id=" lname" path="lname" /></td>
			</tr>
			<tr>
				<td><spring:message code="user.email"/></td>
				<td><form:input id="email" path="email" /><p id="p2" style="color:red;"></p></td>
			</tr>
			<tr>
				<td><spring:message code="user.dob"/></td>
				<td><form:input path="dob" type="date" /></td>
			</tr>
			<tr>
				<td><spring:message code="user.bloodGroup"/></td>
				<td><form:input path="bloodGroup" /></td>
			</tr>
			<tr>
				<td><spring:message code="user.aadhar"/></td>
				<td><form:input path="aadhar" /></td>
			</tr>
			<tr>
				<td><spring:message code="user.pan"/></td>
				<td><form:input path="pan" /></td>
			</tr>
			<tr>
				<td><spring:message code="user.designation"/></td>
				<td><form:input path="designation" /></td>
			</tr>
			<tr>
			<td>	<spring:message code="user.joiningDate"/></td>
				<td><form:input path="joiningDate" type="date" /></td>
			</tr>
			<tr>
				<td><spring:message code="user.address"/></td>
				<td><form:input id="address" path="address" /><p id="p3" style="color:red;"></p></td>
			</tr>
			
			<tr>
				<td><spring:message code="user.mob"/></td>
				<td><form:input path="mob" /></td>
			</tr>
			
			<tr>
				<td><spring:message code="user.salary"/></td>
				<td><form:input path="salary" /></td>
			</tr>
			<tr>
				<td><spring:message code="user.password"/></td>
				<td><form:input path="password" /></td>
			</tr>
			<tr>
			<td><spring:message code="user.submit" var="submit"/></td>
				<td><input type="submit" id="submit" value="${submit}" /></td>
			</tr>
		</form:form>
	</table>
</div>
</body>
</html>
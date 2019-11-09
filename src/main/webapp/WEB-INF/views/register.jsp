<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Register</title>

<link href="./resources/css/bootstrap.css" rel="stylesheet"/>
<script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>
<script src="./resources/JS/formvalid.js" type="text/javascript"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.1/jquery-ui.js"></script>


<link href="./resources/images/kgate.png" rel="shortcut icon"
	type="image/png" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>


<body>
<%-- <c:if test="${type == 'ADMIN'}"> --%>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<a class="navbar-brand" href ="https://www.kgate.in/">K-Gate</a>

			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/attendance">Attendence</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/messageEmployee">Message</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/workReport">Report</a></li>
					<li class="nav-item"><a class="nav-link"
				href="/HrApp/lms">L.M.S</a></li>
					
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Employee </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/HrApp/register">Add Employee</a> <a
							class="dropdown-item" href="/HrApp/viewEmployees">View All
							Employees </a>
					</div></li>
			</ul>
			<div class="navbar-collapse collapse w-25 order-3 dual-collapse2">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<form action="logout" method="get">
							<button class="btn btn-link" style="color: white;">Log
								Out</button>
						</form>
					</li>
				</ul>
			</div>
		</nav>




<div class="container">

	<h3><spring:message code="user.headerMessage"/></h3>
	
	<table class="table">
    <p id="head"></p>

		<form:form action="save" method="post" modelAttribute="user2" >
			<tr><form:input path="id" class="form-control"   type="hidden" /></tr>
			<tr>
				<td><spring:message code="user.empCode" /></td>
				<td><form:input path="empCode" class="form-control" required="required" /></td>
				<td></td>
			</tr>
			<tr>
				<td><spring:message code="user.fname"/></td>
				<td><form:input  id="fname" path="fname" class="form-control" onblur="first()" required="required"/></td><td><p style="color: red" id="fn"></p></td>
			</tr>
			<tr>
				<td><spring:message code="user.mname"/></td>
				<td><form:input id="mname" path="mname" class="form-control" onblur="middle()"/></td><td><p style="color: red" id="mn"></p></td>
			</tr>

			<tr>
				<td><spring:message code="user.lname"/></td>
				<td><form:input id="laname" path="lname" class="form-control" onblur="lastName()" /> </td> <td><p style="color: red" id="lastName"></p></td>
			</tr>
			<tr>
				<td><spring:message code="user.email"/></td>
				<td><form:input id="email" path="email" class="form-control" onblur="emailId()" required="required"/></td> <td><p style="color: red" id="em"></p></td>
			</tr>
			<tr>
			
				<td><spring:message code="user.dob"/></td>
				<td><form:input path="dob" type="Date" class="form-control"  /></td>
			<td></td>
 	</tr>
			<tr>
				<td><spring:message code="user.bloodGroup"/></td>
				<td><form:input path="bloodGroup" id="bloodGroup" class="form-control" onblur="bgValidate()"/></td><td><p style="color: red" id="bg"></td>
			</tr>
			<tr>
				<td><spring:message code="user.aadhar"/></td>
				<td><form:input path="aadhar" id="aadhar" class="form-control" onblur="aadharValidate()"/></td><td><p style="color: red" id="aa"></p></td>
			</tr>
			<tr>
				<td><spring:message code="user.pan"/></td>
				<td><form:input path="pan" id="pan" class="form-control" onblur="validatePAN()"/></td><td><p style="color: red" id="pa"></p></td>
			</tr>
			
			
			
			<tr>
				<td><spring:message code="user.designation"/></td>
				<td><form:input path="designation" class="form-control"/></td>   <td><p style="color: red" id="de"></p></td>
			</tr>
			
			
			<tr>
			<td>	<spring:message code="user.joiningDate"/></td>
				<td><form:input path="joiningDate" type="date" class="form-control" id="joiningDate" required="required"/></td><td><p style="color: red" id="jd"></p></td>
			</tr>
			<tr>
				<td><spring:message code="user.address"/></td>
				<td><form:input id="address" class="form-control" path="address" /></td><td><p style="color: red" id="ad"></p></td>
			</tr>
			
			<tr>
				<td><spring:message code="user.mob"/></td>
				<td><form:input path="mob" id="mob" class="form-control" onblur="mobile()" /></td><td><p style="color: red" id="mo"></p></td>
			</tr>
			
			<tr>
				<td><spring:message code="user.salary"/></td>
				<td><form:input path="salary" id="salary" class="form-control" onblur="sal()" /></td><td><p style="color: red" id="sa"></p></td>
			</tr>
			<tr>
				<td><spring:message code="user.password"/></td>
				<td><form:input path="password" id="password" class="form-control" onblur="psw()" required="required" /></td><td><p style="color: red" id="psw"></p></td>
			</tr>
			
			<tr>
				<td>User Type</td>
				<td><form:select path="userType" class="dropdown-header" required="required"><form:options items="${userType}"/></form:select></td>   <td><p style="color: red" id="de"></p></td>
			</tr>
			
			
			<tr>
			<td><spring:message code="user.submit" var="submit"/></td>
				<td><input type="submit" class="btn btn-primary" id="submit" value="${submit}" /></td>
				<td></td>
			</tr>
		</form:form>
	</table>
</div>



<p style="color: red;">${msg}</p>

</body>
</html>>
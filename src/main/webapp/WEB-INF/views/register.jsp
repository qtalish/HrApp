<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
</head>
<body>
<form:form action="save" method="post" modelAttribute="user">
<form:input path="id" type="hidden"/><br>
Employee Code : <form:input path="empCode"/><br>
First Name : <form:input path="fname"/><br>
Middle Name : <form:input path="mname"/><br>
Last Name : <form:input path="lname"/><br>
Email : <form:input path="email"/><br>
D.O.B : <form:input path="dob" type="date"/><br>
Blood Group : <form:input path="bloodGroup"/><br>
Aadhar Number :<form:input path="aadhar"/><br>
Pan Number : <form:input path="pan"/><br>
Designation : <form:input path="designation"/><br>
Joining Date : <form:input path="joiningDate" type="date"/><br>
Address : <form:input path="address"/><br>
Salary : <form:input path="salary"/><br>
<%-- User Type : <form:input path="userType"/><br> --%>
Password : <form:input path="password"/><br>
<input type="submit" value="Register"/>


</form:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 300px;
  margin: auto;
  text-align: center;
  font-family: arial;
}

.title {
  color: grey;
  font-size: 18px;
}

button {
  border: none;
  outline: 0;
  display: inline-block;
  padding: 8px;
  color: white;
  background-color: #000;
  text-align: center;
  cursor: pointer;
  width: 100%;
  font-size: 18px;
}

a {
  text-decoration: none;
  font-size: 22px;
  color: black;
}

button:hover, a:hover {
  opacity: 0.7;
}
</style>
</head>
<body>
<h2 style="text-align:center">User Profile Card</h2>

<div class="card">
  <img src="./resources/images/steve.jpg" alt="steve" style="width:100%">
  <h1>${user.fname} ${user.lname}</h1>
  <p class="title">CEO & Founder, Example</p>
  <p>Harvard University</p>
  
  ${user.email}
  <div style="margin: 24px 0;">
    <a href="#"><i class="fa fa-dribbble"></i></a> 
    <a href="#"><i class="fa fa-twitter"></i></a>  
    <a href="#"><i class="fa fa-linkedin"></i></a>  
    <a href="#"><i class="fa fa-facebook"></i></a> 
  </div>
  <p><button>Contact</button></p>
  <p><a href="editEmployeeAjax?id=${id}">Edit</a></p>
</div>

</body>
</html>
<%--     <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Profile</title>
</head>
<body>
<%@include file="logout.jsp" %>
<h1>User profile Page</h1>
  <table>
    <tr>
      <td>First Name</td>
      <td>${user.fname}</td>
    </tr>
    <tr>
      <td>Middle Name  &nbsp;</td>
      <td>${user.mname}</td>
    </tr>
    <tr>
      <td>Last Name</td>
      <td>${user.lname}</td>
    </tr>
    <tr>
      <td>email</td>
      <td>${user.email}</td>
    </tr>
  </table>
</body>
</html> --%>
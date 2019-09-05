<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Salary</title>
<!-- <script type="text/javascript" src="./resources/JS/bootstrap.min.js"></script> -->
<link href="./resources/css/bootstrap.css" rel="stylesheet" />
<script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>

<script src="./resources/JS/formvalid.js" type="text/javascript"></script>


</head>
<body>
	${months} ${years}
	${userLeaves}
	<div class="container">
		<form:form action="searchSalary" method="get">
		<input name="empCode" type="hidden" value="${param.empCode}"/>
			<%-- <form:select path=""><form:options value="${months}"/></form:select> --%>
			<select name="month">
				<c:forEach items="${months}" var="months"  >
					<option>${months}</option>
				</c:forEach>
			</select>

			<select name="year">
				<c:forEach items="${years}" var="years" >
					<option>${years}</option>
				</c:forEach>
			</select>


			<input type="submit" value="Find" />
		</form:form>

	</div>
</body>
</html>
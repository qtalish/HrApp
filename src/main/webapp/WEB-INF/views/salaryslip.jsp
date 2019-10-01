<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body><center>
<h1>Salary Slip</h1>
<form:form action="downloadSlip" method="get">
<input name="empCode" type="hidden" value="${empCode}"/>
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
			<br><br>
			<input type="submit" value="Download" />
			</center>
				</form:form>
</body>
</html>
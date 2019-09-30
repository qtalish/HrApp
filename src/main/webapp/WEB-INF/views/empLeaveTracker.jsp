<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Leave Tracker</title>
</head>
<body>


				<table class="table">
					<thead>
						<th>Month</th>
						<th>Year</th>
						<th>Balance Leaves</th>	
					</thead>
					<tbody>
						<td>${leave.month}</td>
						<td>${leave.year}</td>
						<td>${leave.balanceLeaves}</td>	
					</tbody>
				</table>

</body>
</html>
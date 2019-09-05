<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Documents</title>
</head>
<body>

	<form:form method="post" action="addDocument"
		modelAttribute="userDocument" enctype="multipart/form-data">
		<form:errors path="*" cssClass="error" />


		<form:input path="empCode" type="hidden" />

		<table>
			<tr>
				<td><form:label path="dname">Name</form:label></td>
				<td><form:input path="dname" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:textarea path="description" /></td>
			</tr>
			<tr>
				<td><form:label path="document">Document</form:label></td>
				<td><input type="file" name="file" id="file"></input></td>

			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Upload" /></td>
			</tr>
		</table>
	</form:form>
	<br> ${msg} 
	<form:form action="backToEmployee" method="post">
		<input type="submit" value="Back">
	</form:form>
</body>
</html>
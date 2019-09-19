<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<form:input path="userType" type="hidden" />
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
<%-- 	<form:form action="backToEmployee" method="post">
		<input type="submit" value="Back">
	</form:form> --%>

	<h1>List of Documents Admin</h1>
	
	<div class="container">
			<table class="table" border="1">

					<th style="color: red">Id</th>
					<th style="color: red">Name</th>
					<th style="color: red">Description</th>
					<th style="color: red">Document Type</th>
					<th style="color: red">Action</th>

					<c:forEach items="${uc}" var="doc">
						<tr>
							<td>${doc.id}</td>
							<td>${doc.dname}</td>
							<td>${doc.description}</td>
							<td>${doc.documentType}</td>
								
						<td><a href="<c:url value='/downloadDoc-${doc.id}' />"
									class="btn btn-success custom-width">Download</a>
								<a href="<c:url value='/delete-document-${doc.id}' />"
									class="btn btn-danger custom-width">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div> 
</body>
</html>
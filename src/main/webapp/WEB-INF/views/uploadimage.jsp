<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <form:form method="post" action="uploadImage" enctype="multipart/form-data" modelAttribute="user">  
<p><label for="image"></label></p>  
<p><input name="file" id="file" type="file" /></p> 
 <img src="data:image/jpeg;base64,${userImage}" style="width:100%"> 
<p><input type="submit" value="Upload"></p>  
</form:form>  
</body>
</html>
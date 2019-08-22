<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
        pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attendance</title>
</head>
<body><center>
        <form action="SearchEmp" method="get">
                <input type="date" name="attDate" /> <input type="submit"
                        value="Search">
        </form>
                                <div class="tablecontainer">
                                <table class="table table-hover" border="1">
                                        <thead>

                                                <th>Name</th>
                                                <th>Status</th>
                                                <th>Remarks</th>
                                        </thead>
                                        <br>         
                                        <tbody>
                                                <c:forEach items="${list}" var="li">
                                                        <tr>
                                                        <td>${li.firstName} ${li.lastName}</td>

                                                         <td><form:form modelAttribute="a">
                                                         <form:select path ="status" items="${ustatus}" onchange="changeStatus(${li.id})" id="${li.id}" ></form:select>
</form:form>
                                                 </c:forEach>
                                        </tbody>
                                </table></div></center>
                                <script>
                                changeStatus=function(id) {
                                        /* console.log(a); */
                                /*         var a = $("#"+id).val(); */
                                        console.log(id);
                                }
                                </script>
</body>

</html>
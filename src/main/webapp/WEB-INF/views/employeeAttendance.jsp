
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Attendance</title>
<script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>
<link href="./resources/css/bootstrap.css" rel="stylesheet" />
<script src="./resources/JS/sweetalert.min.js" type="text/javascript"></script>
</head>
<body>
	<%@include file="home.jsp"%>
	<%@include file="logout.jsp"%>
	<form:form action="backToAdmin" method="post"><input type="submit" value="Back"></form:form> 
	
	<div class="container">
		<div align="center">
			<form:form action="SearchEmp" method="get" modelAttribute="attd">
				<form:input path="attDate" type="date" value="${dd}" />
				<input type="submit" value="Search">
			</form:form>
			<button class="btn btn-primary" onclick="saveRemarks(${list2})">Save</button>

			<table class="table">
				<thead>

					<th>Name</th>
					<th>Status</th>
					<th>Remarks</th>
				</thead>
				<br>
				<tbody>
					<form:form modelAttribute="attd">
						<c:forEach items="${list}" var="li" varStatus="s">
							<tr>
								<td>${li.firstName}${li.lastName}</td>
								<%-- 							<td>${li.status}</td> --%>
								<td><form:select path="status"
										onchange="getval(this,${li.id});" id="${li.id}">
										<form:option value="${li.status} " />
										<form:options items="${ustatus}" />
									</form:select></td>
								<td><input type="text" id="${li.id+1000}"
									value="${li.remarks}"></td>
						</c:forEach>
					</form:form>
				</tbody>
			</table>
		</div>
	</div>
	<script>

function saveRemarks(list){
// 	var text =list;
	console.log(list)
	for(var i = 0, size = list.length; i < size ; i++){
		   var item = list[i];
		   var item2 = item+1000;
		   var test = $("#"+item2).val();
		   console.log(test)
		   console.log(item+1000)
			$.ajax({
				url:"changeRemarks",
				type:"GET",
				data:{
				    "remarks" : test,"id":item
			    },
			    success: function(response){
			    	swal(response.msg, {
					      icon: "success",
					    });
                    }
			});
	}
}


	
		function getval(sel,id) {
// 			alert(sel.value);
			var status = sel.value;
			console.log(id);
			console.log(sel.value);
			$.ajax({
					url:"changeStatus",
					type:"GET",
					data:{
					    "status" : status,"id":id
				    },
				    success: function(msg){
	                    //we need to check if the value is the same
	
	                    //Receiving the result of search here
	                    }
				});
				
		};
		// 				changeStatus=function(id) {

		/* console.log(a); */
		/* 	var a = $("#"+id).val(); */
		// 				  	console.log(id);
		// 				}
	</script>
</body>

</html>

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

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css">


<link href="./resources/images/kgate.png" rel="shortcut icon"
	type="image/png" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>


</head>
<body>
	
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/attendance">Attendence</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/messageEmployee">Message</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/workReport">Work Report</a></li>

				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Employee </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/HrApp/register">Add Employee</a><a
							class="dropdown-item" href="/HrApp/viewEmployees">View All
							Employees </a>
					</div></li>
			</ul>
			<div class="navbar-collapse collapse w-25 order-3 dual-collapse2">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item">
						<form action="logout" method="get">
							<button class="btn btn-link" style="color: white;">Log
								Out</button>
						</form>
					</li>
				</ul>
			</div>
		</nav>
		<br> <br>
			
				<div class="input-group-append">
		 <form:form   class="input-group mb-2" action="SearchEmp" method="get" modelAttribute="attd">
				<%-- <form:input path="attDate" class="dropdown-header" type="date" value="${dd}" aria-describedby="basic-addon2" />  --%>
				
				<form:input  path="attDate" class="dropdown-header" value="${dd}" type="date" id="calender" aria-describedby="basic-addon2" />
				<button style='margin-right:16px' class="btn btn-primary">Search</button>
				<button style='margin-right:16px' class="btn btn-primary" onclick="saveRemarks(${list2})">Save Remarks</button>
			</form:form> 
			
				<button style='margin-right:16px' class="btn btn-primary" onclick="showFileDialog();">Import</button>
			
			<form id="fileFormAttendance" method="POST" enctype="multipart/form-data" >
					<input type="file" name="fileAttendance" id="fileAttendance" style="display:none" />
				</form>
				</div>
			

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
								<td>${li.firstName} ${li.lastName}</td>
								<%-- <td>${li.status}</td> --%>
								<td><form:select path="status" class="dropdown-header"
										onchange="getval(this,${li.id});" id="${li.id}">
										<form:option value="${li.status} " />
										<form:options items="${ustatus}" />
									</form:select></td>
								
									
									<td>
 		<input type="text" id="${li.id+1000}"
		value="${li.remarks}" class="form-control" aria-label="Recipient's username" />

		</td>
						</c:forEach>
					</form:form>
				</tbody>
			</table>
	

     <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script>
var isXlsx = function(name) {
    return name.match(/xlsx$/i)
    };
    $("#btnfile").click(function () {
	    $("#uploadfile").click();
	});
    
    function showFileDialog(){
	$("#fileAttendance").click();
	
	}
	    
	    $(document).ready(function() {
	    
	    var file = $('[name="fileAttendance"]');
	    
	    $('#uploadLink').on('click', function() {
		});
		
	    var fileU = document.getElementById('fileAttendance');
		fileU.addEventListener("change", function () {
			  if (fileU.files.length > 0) {
			   var filename = $.trim(file.val());
			
			if (!(isXlsx(filename) )) {
			    alert('Error', 'Please select an xlsx file to upload');
			    return;
			}
			
			$.ajax({
			   xhr: function() {
			    var xhr = new window.XMLHttpRequest();

			  

			    return xhr;
			  },
			   url: 'upload',
			    type: "POST",
			    data: new FormData(document.getElementById("fileFormAttendance")),
			    enctype: 'multipart/form-data',
			    processData: false,
			    contentType: false
			  }).done(function(data) {
			    alert('Success', 'File Upload Successful');
			   
			  }).fail(function(jqXHR, textStatus) {
			      alert('Failure', 'File Upload Failed. Please contact Administrator');
			  });
			  document.getElementById('fileAttendance').value = null;
			    return;
			  }
			 
			});
		  
		});
		
			
	    
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
		
		
	
		
		document.getElementById("datepickericon").onclick = function(e){
		    document.getElementById("calendar").focus();
		    // You could write code to toggle this
		}
		
	</script>
 </body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link href="./resources/css/font-awesome.min.css" rel="stylesheet"/>
<link href="./resources/css/style.css" rel="stylesheet"/>
<script src="./resources/JS/jquery-3.3.1.min.js" type="text/javascript"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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

<c:if test="${type == 'HR'}">

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/hrDailyReport">Daily Report</a></li>
				<li class="nav-item"><a class="nav-link"
				href="/HrApp/hrCallingSheet">Calling Sheet</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/downloadSalarySlip">Salary Slip Download</a></li>

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
<!-- 	<br>	 -->
	</c:if>
		
		
		
<c:if test="${type == 'DEVELOPER'}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/dailyReport">Daily Report</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/downloadSalarySlip">Salary Slip Download</a></li>

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
	<br>
	</c:if>
	
	
	<c:if test="${type == 'OPERATIONS'}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/dailyReport">Daily Report</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/downloadSalarySlip">Salary Slip Download</a></li>

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
	<br>
	</c:if>
	
	
	<c:if test="${type == 'ACCOUNTS'}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/dailyReport">Daily Report</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/downloadSalarySlip">Salary Slip Download</a></li>

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
	<br>
	</c:if>
	
	
	
	<c:if test="${type == 'MARKETING'}">
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="https://www.kgate.in/">K-Gate</a>

		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="/HrApp/profile">Profile</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/documents">Document</a></li>
			<li class="nav-item"><a class="nav-link" href="/HrApp/leave">Leave
					Application</a></li>
			<li class="nav-item"><a class="nav-link"
				href="/HrApp/dailyReport">Daily Report</a></li>
				<li class="nav-item"><a class="nav-link"
					href="/HrApp/downloadSalarySlip">Salary Slip Download</a></li>

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
	</c:if>
<!-- 	<br> -->
	
	<!-- Hero section start -->
	<section class="hero-section spad">
		<div class="container-fluid">
			<div class="row">
				<div class="col-xl-10 offset-xl-1">
					<div class="row">
						<div class="col-lg-6">
							<div class="hero-text">
								<h2 style="font-size: 52px;margin-bottom: 40px;">${user.fname} ${user.lname}</h2>
								<p></p><p>${user.designation}</p>
							</div>
							<div class="hero-info">
								<h2>General Info<button class="btn btn-link" onclick="location.href='editEmployeeProfile?id=${id}'" type="button">Edit</button> </h2>	
								<ul>
									<li><span>Date of Birth : </span>${user.dob}</li>
									<li><span>Address : </span>${user.address}</li>
									<li><span>E-mail : </span>${user.email}</li>
									<li><span>Phone : </span>${user.mob}</li>
								</ul>
								
							
							
							</div>
						</div>
						<div class="col-lg-6">
							<figure class="hero-image">
								<img style="width: 450px; height: 450px;" src="data:image/jpeg;base64,${userImage}" alt="5">
								 
							</figure>
					<button class="btn btn-link" style='margin-right:16px' onclick="showFileDialog();" >Upload Picture</button>
                  <form id="fileFormEmp" method="POST" enctype="multipart/form-data" >
					<input type="file" name="fileEmp" id="fileEmp" style="display:none" />
				</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>


	<script>  
    function showFileDialog(){
	$("#fileEmp").click();
	
	}
	    
	    $(document).ready(function() {
	    
	    var file = $('[name="fileEmp"]');
	    
	    $('#uploadLink').on('click', function() {
		});
		
	    var fileU = document.getElementById('fileEmp');
		fileU.addEventListener("change", function () {
			  if (fileU.files.length > 0) {
			   var filename = $.trim(file.val());
			
			
			$.ajax({
			   xhr: function() {
			    var xhr = new window.XMLHttpRequest();

			  

			    return xhr;
			  },
			   url: 'uploadImage',
			    type: "POST",
			    data: new FormData(document.getElementById("fileFormEmp")),
			    enctype: 'multipart/form-data',
			    processData: false,
			    contentType: false
			  }).done(function(data) {
			    alert('Success', 'File Upload Successful');
			   
			  }).fail(function(jqXHR, textStatus) {
			      alert('Failure', 'File Upload Failed. Please contact Administrator');
			  });
			  document.getElementById('fileEmp').value = null;
			    return;
			  }
			 
			});
		  
		});
	    </script>
</body>
</html>
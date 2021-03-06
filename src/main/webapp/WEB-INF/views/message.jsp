<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Message</title>
<!-- <script type="text/javascript" src="./resources/JS/jquery-3.3.1.min.js"></script>
<link href="./resources/css/bootstrap.css" rel="stylesheet" />
<script src="./resources/JS/sweetalert.min.js" type="text/javascript"></script> -->

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
				href="/HrApp/workReport">Report</a></li>
				<li class="nav-item"><a class="nav-link"
				href="/HrApp/lms">L.M.S</a></li>
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
	<br>


	<!-- <div class="container"> -->
	<h2 align="center">Employee Notification</h2>
	
	<br>
	<table class="table">
		<form:form action="sendMail" method="post" modelAttribute="message">
			<form:input path="id" type="hidden" />

			<tr>
				<td>Subject :</td>
				<td><form:input path="subjectMessage" class="form-control" /></td>
			</tr>
			<tr>
				<td>Message :</td>
				<td><form:textarea path="messageEmp" class="form-control" /></td>
			</tr>
			<tr>
				<td>To :</td>
				<td><form:select id="myslct" path="to" multiple="true"
						name="myselect" class="form-control">
						<form:options items="${emp}" id="emp"/>
					</form:select></td> 
			</tr>
			<tr>
				<td></td>
				<td>
					<span class="btn btn-link" onclick="selectAll();">Select
						All</span>
					
					<span class="btn btn-link" onclick="selectAllDeveloper();">Select
						All Developer</span>	
					
					<span class="btn btn-link" onclick="selectAllIntern();">Select
						All Intern</span>	
						
					<span class="btn btn-link" onclick="selectAllHr();">Select
						All HR</span>
					
					<span class="btn btn-link" onclick="selectAllMarketing();">Select
						All Marketing</span>
					
					<span class="btn btn-link" onclick="selectAllOperation();">Select
						All Operartion Manager</span>
						
					<span class="btn btn-link" onclick="selectAllAccoutant();">Select
						All Accountant</span>
						
					<span class="btn btn-link" onclick="deselectAll();">Deselect
						All</span>
				</td>
			</tr>
			<tr>
			
				<td></td>
				<td>
					<button class="btn btn-primary">Send</button>
				</td>
			</tr>
		</form:form>
	</table>


	<p style="color: red;">${msg}</p>


	<script>
		selectAll = function() {
			$('#myslct option').prop('selected', true);
		}
		deselectAll = function() {
			$('#myslct option').prop('selected', false);
		}
		
		selectAllDeveloper = function() {
		$( "option" ).each(function( index ) {
			  console.log(index + ": " + $( this ).text()  );
			   var check = $( this ).text(). includes('Developer');
			  console.log(check);
			  if(check) {
				  var list = $( this ).text() ;
				  console.log(list);
				  var id =   $( this ).attr('id');
				  
				  $('#'+id).prop('selected', true);
			  }
		});
		}
		
		selectAllHr = function() {
			$( "option" ).each(function( index ) {
				  console.log(index + ": " + $( this ).text()  );
				   var check = $( this ).text(). includes('HR');
				  console.log(check);
				  if(check) {
					  var list = $( this ).text() ;
					  console.log(list);
					  var id =   $( this ).attr('id');
					  
					  $('#'+id).prop('selected', true);
				  }
			});
			}
		
		
		selectAllMarketing = function() {
			$( "option" ).each(function( index ) {
				  console.log(index + ": " + $( this ).text()  );
				   var check = $( this ).text(). includes('Marketing');
				  console.log(check);
				  if(check) {
					  var list = $( this ).text() ;
					  console.log(list);
					  var id =   $( this ).attr('id');
					  
					  $('#'+id).prop('selected', true);
				  }
			});
			}
		
		selectAllOperation = function() {
			$( "option" ).each(function( index ) {
				  console.log(index + ": " + $( this ).text()  );
				   var check = $( this ).text(). includes('Operation');
				  console.log(check);
				  if(check) {
					  var list = $( this ).text() ;
					  console.log(list);
					  var id =   $( this ).attr('id');
					  
					  $('#'+id).prop('selected', true);
				  }
			});
			}
		
		
		selectAllAccoutant = function() {
			$( "option" ).each(function( index ) {
				  console.log(index + ": " + $( this ).text()  );
				   var check = $( this ).text(). includes('Accoutant');
				  console.log(check);
				  if(check) {
					  var list = $( this ).text() ;
					  console.log(list);
					  var id =   $( this ).attr('id');
					  
					  $('#'+id).prop('selected', true);
				  }
			});
			}
		
		selectAllIntern = function() {
			$( "option" ).each(function( index ) {
				  console.log(index + ": " + $( this ).text()  );
				    var check = $( this ).text(). includes('Intern');
				  console.log(check);
				  if(check) {
					  var list = $( this ).text() ;
					  console.log(list);
					  var id =   $( this ).attr('id');
					  
					  $('#'+id).prop('selected', true);
				  }
			});
			}
		</script>
</body>
</html>
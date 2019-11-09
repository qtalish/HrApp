<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HR Calling Sheet</title>
<script src="./resources/JS/jquery-3.3.1.min.js"></script>

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
<script src="./resources/JS/candidate.js" type="text/javascript"></script>	
<!-- <script -->
<!-- 	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<!-- <script -->
<!-- 	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>
<header>
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
	</header>
	<br><div align="center"><input id="myInput"  onkeyup="myFunction2()" type="text" placeholder="Search Candidate Here.."  /></div> <br></br>   <br>
	


	<div>
	<table class="table-responsive" id="tbl"  border="1">
		<form:form action="submitCallingSheet" method="post"
			modelAttribute="hcs">
			<form:input path="id" type="hidden" />
			
			<!-- <table class="table-responsive"  border="1"> -->
               
                <th style="color: red">Date</th>
				<th style="color: red">Candidate Name</th>
				<th style="color: red">Skills</th>
				<th style="color: red">Client Name</th>
				<th style="color: red">Mobile No.</th>
				<th style="color: red">Email</th>
				<th style="color: red">Total Exp.</th>
				<th style="color: red">Relevant Exp.</th>
				<th style="color: red">Current CTC</th>
				<th style="color: red">Expected CTC</th>
				<th style="color: red">Notice Period</th>
				<th style="color: red">Organization</th>
				<th style="color: red">Current Location</th>
				<th style="color: red">Preferred Location</th>
				<th style="color: red">Any Offer</th>
				<th style="color: red">Test Score</th>
				<th style="color: red">Status</th>
				<th style="color: red">Source</th>
				<th style="color: red">Remark</th>
				<th style="color: red">Interview Date</th>
				<th style="color: red">Action</th>
				
				<tr>
					<td><form:input type="date" path="date" required="required"/></td>
					<td><form:input path="candidateName" required="required"/></td>
					<td><form:input path="skills" /></td>
					<td><form:input path="clientName"  /></td>
					<td><form:input path="mob" /></td>
					<td><form:input path="email" /></td>
					<td><form:input path="totalExp" /></td>		
					<td><form:input path="relevantExp" /></td>
					<td><form:input path="currentCTC" /></td>
					<td><form:input path="expectedCTC" /></td>
					<td><form:input path="noticePeriod" /></td>
					<td><form:input path="organization" /></td>
					<td><form:input path="currentLocation" /></td>
					<td><form:input path="prefferedLocation" /></td>					
					<td><form:input path="anyOffer" /></td>
					<td><form:input path="testScore" /></td>
					<td><form:select path="status"><form:options items="${status}"/></form:select></td>
					<td><form:input path="source" /></td>
					<td><form:input path="remarks" /></td>
					<td><form:input path="interviewDate" type="date" /></td>
					<td><button class="btn btn-link">Submit</button></td>
				</tr> 
				</form:form>
				 
			</table>
		
	</div>


<%-- 	<div class="container">
		<form:form action="submitCallingSheet" method="post"
			modelAttribute="hcs">
			<form:input path="id" type="hidden" />
			<table class="table" border="1">
                <th style="color: black">Date</th>
				<th style="color: black">Candidate Name</th>
				<th style="color: black">Skills</th>
				<th style="color: black">Client Name</th>
				<th style="color: black">Mobile No.</th>
				<th style="color: black">Email</th>
				<th style="color: black">Total Exp.</th>
		
				<tr style="border: 3">
				<td><form:input type="date" path="date" /></td>
					<td><form:input path="candidateName" /></td>
					<td><form:input path="skills" /></td>
					<td><form:input path="clientName" /></td>
					<td><form:input path="mob" /></td>
					<td><form:input path="email" /></td>
					<td><form:input path="totalExp" /></td>
				</tr>
		
				<th style="color: black">Relevant Exp.</th>
				<th style="color: black">Current CTC</th>
				<th style="color: black">Expected CTC</th>
				<th style="color: black">Notice Period</th>
				<th style="color: black">Organization</th>
				<th style="color: black">Current Location</th>
				<th style="color: black">Preferred Location</th>
				
				<tr>
					<td><form:input path="relevantExp" /></td>
					<td><form:input path="currentCTC" /></td>
					<td><form:input path="expectedCTC" /></td>
					<td><form:input path="noticePeriod" /></td>
					<td><form:input path="organization" /></td>
					<td><form:input path="currentLocation" /></td>
					<td><form:input path="prefferedLocation" /></td>
				</tr>
	
		
				<th style="color: black">Any Offer</th>
				<th style="color: black">Test Score</th>
				<th style="color: black">Status</th>
				<th style="color: black">Source</th>
				<th style="color: black">Remark</th>
				<th style="color: black">Interview Date</th>
				<th style="color: black">Action</th>
				
				<tr>
				<td><form:input path="anyOffer" /></td>
					<td><form:input path="testScore" /></td>
					<td><form:input path="status" /></td>
					<td><form:input path="source" /></td>
					<td><form:input path="remarks" /></td>
					<td><form:input path="updateDate" /></td>
					</tr>
					
			</table>
		</form:form>
	</div> --%>
<%-- <br>
	<h1 align="center" style="color: green;">LIST OF CANDIDATES</h1>
<br>
<div>
<table class="table-responsive" id="tbl" border="1">
                <th style="color: red">Date</th>
				<th style="color: red">Candidate Name</th>
				<th style="color: red">Skills</th>
				<th style="color: red">Client Name</th>
				<th style="color: red">Mobile No.</th>
				<th style="color: red">Email</th>
				<th style="color: red">Total Exp.</th>
				<th style="color: red">Relevant Exp.</th>
				<th style="color: red">Current CTC</th>
				<th style="color: red">Expected CTC</th>
				<th style="color: red">Notice Period</th>
				<th style="color: red">Organization</th>
				<th style="color: red">Current Location</th>
				<th style="color: red">Preferred Location</th>
				<th style="color: red">Any Offer</th>
				<th style="color: red">Test Score</th>
				<th style="color: red">Status</th>
				<th style="color: red">Source</th>
				<th style="color: red">Remark</th>
				<th style="color: red">Interview Date</th>
				<th style="color: red">Action</th>
				<c:forEach items="${candidates}" var="can">
				<tr>
				<td>${can.date}</td>
				<td>${can.candidateName}</td>
				<td>${can.skills}</td>
				<td>${can.clientName}</td>
				<td>${can.mob}</td>
				<td>${can.email}</td>
				<td>${can.totalExp}</td>
				<td>${can.relevantExp}</td>
				<td>${can.currentCTC}</td>
				<td>${can.expectedCTC}</td>
				<td>${can.noticePeriod}</td>
				<td>${can.organization}</td>
				<td>${can.currentLocation}</td>
				<td>${can.prefferedLocation}</td>
				<td>${can.anyOffer}</td>
				<td>${can.testScore}</td>
				<td>${can.status}</td>
				<td>${can.source}</td>
				<td>${can.remarks}</td>
				<td>${can.interviewDate}</td>
				
				</tr> 
				</c:forEach> 		
			</table> --%>
			<br><br>
			<div align="center">
			
			<p id="n"></p>
			</div>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<title>Leaves & Salary</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<meta charset="ISO-8859-1">
<script src="./resources/JS/jquery-3.3.1.min.js" type="text/javascript"></script>
<title>Employee List</title>
<script src="./resources/JS/emp.js" type="text/javascript"></script>
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

<!-- <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>


<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<a class="navbar-brand" href ="https://www.kgate.in/">K-Gate</a>

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
						<a class="dropdown-item" href="/HrApp/register">Add Employee</a> <a
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


	<%-- ${userLeaves} --%>

	<div class="generic-container">
		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">
				<span class="lead"></span>
			</div>
			<div class="tablecontainer">
				<table class="table">
					<thead>
						<th>Working Day</th>
						<th>Total Days Worked</th>
						<th>Total Leaves</th>
						<th>Paid Leaves</th>
						<th>Balance Leaves</th>
						<th>Unpaid Leaves</th>
					</thead>
					<tbody>

						<td id="wd">${userLeaves.workingDays}</td>
						<td>${userLeaves.totalDaysWorked}</td>
						<td>${userLeaves.totalLeaves}</td>
						<td>${userLeaves.paidLeaves}</td>
						<td>${userLeaves.balanceLeaves}</td>
						<td id="upl">${userLeaves.unpaidLeaves}</td>
					</tbody>
				</table>
			</div>
		</div>
	</div>


	<form:form action="saveSalary" modelAttribute="salary" method="post" >
		<form:input path="empCode" type="hidden" />
		<form:input path="id" type="hidden" />
		<form:input path="month" type="hidden" />
		<form:input path="year" type="hidden" />
		<table>
			<tr>
				<td>Basic Salary :</td>
				<td><form:input path="basicSalary" id="bsalary" readonly="true"  class="form-control"/></td>
			</tr>

			<tr>
				<td>HRA :</td>
				<td><form:input path="hra" id="hra" readonly="true" class="form-control" /></td>
			</tr>

			<tr>
				<td>Conveyance Allowances :</td>
				<td><form:input path="conveyanceAllowances" id="ca"
						value="1600" readonly="true" class="form-control" /></td>
			</tr>

			<tr>
				<td>Medical Allowances :</td>
				<td><form:input path="medicalAllowances" id="ma" value="1250"
						readonly="true" class="form-control"/></td>
			</tr>

			<tr>
				<td>Other Allowances :</td>
				<td><form:input path="otherAllowances" id="oa" readonly="true" class="form-control" /></td>
			</tr>
			<tr>
				<td>Gross Salary :</td>
				<td><form:input path="monthlySalary" id="msalary"
						onkeyup="calculate()" class="form-control" /></td>
			</tr>

			<tr>
				<td>Additional Bonus :</td>
				<td><form:input path="additionalBonus" id="ab"
						onkeyup="totalSalary()" class="form-control"/></td>
			</tr>



			<tr>
				<td>Total Earnings For the Month :</td>
				<td><form:input path="totalMonthlySalary" id="tms"
						readonly="true" class="form-control"/></td>
			</tr>


			<tr>
				<td>professional Tax :</td>
				<td><form:input path="professionalTax" id="ptax" value="200"
						readonly="true" onkeyup="totalDeduct()" class="form-control" /></td>
			</tr>

			<tr>
				<td>Provident Fund :</td>
				<td><form:input path="providentFund" id="pf"
						onkeyup="totalDeduct1()" class="form-control" /></td>
			</tr>

			<tr>
				<td>Additional Deduction :</td>
				<td><form:input path="additionalDeduction" id="ad"
						onkeyup="totalDeduct2()" class="form-control" /></td>
			</tr>


			<tr>
				<td>Unpaid Leave:</td>
				<td><form:input path="unpaidLeave" id="ul" class="form-control" /></td>
			</tr>

			<tr>
				<td>Total Deduction :</td>
				<td><form:input path="totalDeduction" id="td" readonly="true" class="form-control" /></td>
			</tr>

			<tr>
				<td>Net Salary :</td>
				<td><form:input path="netSalary" id="nsal" readonly="true" class="form-control" /></td>
			</tr>
<tr><td><button class="btn btn-primary">Submit</button></td></tr>

		</table>
		

	</form:form>

	<script>
		$(document).ready(function() {
			var t = $("#upl").text();
			var wd = $('#wd').text();
			var gs = $('#msalary').val();
			var ul = (gs / wd) * t;
			ul=Math.ceil(ul);
			$('#ul').val(ul);
			var tms = $("#tms").val();
			tms = tms - $("#td").val() - ul;
			tms=Math.ceil(tms);
			$("#nsal").val(tms);

		});
	</script>
	<script>
		calculate = function() {
			var msal = $("#msalary").val()
			console.log(msal);
			var number = /^[0-9]+$/;
			if (msal.match(number)) {
				var bsal = (msal * 40) / 100;
				var hra = (bsal * 50) / 100;
				$("#bsalary").val(bsal);
				$("#hra").val(hra);
				var other = msal - bsal - hra - 1600 - 1250;
				$("#oa").val(other);

				var ab = $("#ab").val();
				ab = ab || 0
				ab = parseInt(ab);
				msal = parseInt(msal);
				var tms = msal + ab;
				$("#tms").val(tms)

				var t = $("#upl").text();
				var wd = $('#wd').text();
				var ul = (msal / wd) * t;
				ul =Math.ceil(ul);
				$('#ul').val(ul);
				/* var tms = $("#tms").val(); */
				var ptax2 = parseInt($("#ptax").val());
				console.log(typeof ptax2 + " ptax: " + ptax2);
				var pf2 = parseInt($("#pf").val());
				console.log(typeof pf2 + " pf: " + pf2);
				var ad2 = parseInt($("#ad").val());
				console.log(typeof ad2 + " ad: " + ad2);

				var tdd = ptax2 + pf2 + ad2+(msal / wd) * t;
				tdd=Math.ceil(tdd);
				console.log(typeof tdd + "tdd: " + tdd)
				$("#td").val(tdd);
				var tms22 = msal+ab-tdd;
				tms22=Math.ceil(tms22);
				$("#nsal").val(tms22); 

				console.log('correct')
				//alert('Correct')
				return true;
			} else {
				console.log('wrong')
				alert('Please Enter Valid Number')
				return false;
			}

		}

		totalSalary = function() {

			/* -------------------------------------- */
			/* var total2 = $("#msalary").val()
			var total = $("#ab").val();
			total = parseInt(total);
			total = total || 0
			total2 = parseInt(total2);
			total2 = total2 || 0
			total = total2 + total;
			$("#tms").val(total);

			var td = $("#td").val();
			td = parseInt(td)
			td = td || 0
			var tms = total - td;
			$("#nsal").val(tms); */
			/* 	-------------------------------------- */

			var total2 = $("#msalary").val()
			var total = $("#ab").val();
			var number = /^[0-9]+$/;
			if (total.match(number)) {
				total = parseInt(total);
				total = total || 0
				total2 = parseInt(total2);
				total2 = total2 || 0
				total = total2 + total;
				$("#tms").val(total);

				console.log('correct')
				//alert('Correct')
				//return true;
			} else {
				console.log('wrong')
				alert('Please Enter Valid Number')
				return false;
			}

			var td = $("#td").val();
			td = parseInt(td)
			td = td || 0
			var tms = total - td;
			$("#nsal").val(tms);
		}
		totalDeduct1 = function() {
			var number = /^[0-9]+$/;
			var pf = $("#pf").val();
			var ad = $("#ad").val();
			var ul = $("#ul").val();
			var ptax = $("#ptax").val();
			if (!pf.match(number)) {

				alert('Please Enter Valid Number')
				console.log('Incorrect pf')
				return false;
			}

			pf = parseInt(pf);
			pf = pf || 0
			ad = ad || 0
			ad = parseInt(ad);
			ul = ul || 0
			ul = parseInt(ul);
			ptax = ptax || 0
			ptax = parseInt(ptax);
			var deduct = parseInt(pf + ad + ul + ptax);
			deduct = deduct || 0
			deduct = parseInt(deduct);
			$("#td").val(deduct);
			var tms = $("#tms").val();
			tms = parseInt(tms);
			tms = tms || 0
			var totald = $("#td").val();
			totald = parseInt(totald);
			totald = totald || 0
			totald = tms - totald;
			$("#nsal").val(totald);
		}
		totalDeduct2 = function() {
			var number = /^[0-9]+$/;
			//	var number = /^[0-9]*[.][0-9]+$/;
			var pf = $("#pf").val();
			var ad = $("#ad").val();
			var ul = $("#ul").val();
			var ptax = $("#ptax").val();
			if (!ad.match(number)) {
				alert('Please Enter Valid Number')
				console.log('Incorrect ad')
				return false;
			}

			pf = parseInt(pf);
			pf = pf || 0
			ad = ad || 0
			ad = parseInt(ad);
			ul = ul || 0
			ul = parseInt(ul);
			ptax = ptax || 0
			ptax = parseInt(ptax);
			var deduct = parseInt(pf + ad + ul + ptax);
			deduct = deduct || 0
			deduct = parseInt(deduct);
			$("#td").val(deduct);
			var tms = $("#tms").val();
			tms = parseInt(tms);
			tms = tms || 0
			var totald = $("#td").val();
			totald = parseInt(totald);
			totald = totald || 0
			totald = tms - totald;
			$("#nsal").val(totald);
		}
	</script>

</body>
</html>
data = '';
$(document).ready(function() {
	loadCandidate();
});
loadCandidate = function(page) {
	console.log("called "+page)
	var url = "candidateList?page=" + page;
	$
			.ajax({
				url : url,
				type : 'GET',
				success : function(response) {
					data = response.candidate;
					$('.tr').remove();
					for (i = 0; i < response.candidate.length; i++) {
						var myObj = $
								.parseJSON('{"date_created":"1273185387"}'), myDate = new Date(
								1000 * myObj.date_created);

// console.log(myDate.toString());
// console.log("........................."
// + response.candidate[i].date);
						$('#tbl')
								.append(
										"<tr class='tr'><td>"
												+ response.candidate[i].date
												+ "</td><td>"
												+ response.candidate[i].candidateName
												+ "</td><td>"
												+ response.candidate[i].skills
												+ "</td><td>"
												+ response.candidate[i].clientName
												+ "</td><td>"
												+ response.candidate[i].mob
												+ "</td><td>"
												+ response.candidate[i].email
												+ "</td><td>"
												+ response.candidate[i].totalExp
												+ "</td><td>"
												+ response.candidate[i].relevantExp
												+ "</td><td>"
												+ response.candidate[i].currentCTC
												+ "</td><td>"
												+ response.candidate[i].expectedCTC
												+ "</td><td>"
												+ response.candidate[i].noticePeriod
												+ "</td><td>"
												+ response.candidate[i].organization
												+ "</td><td>"
												+ response.candidate[i].currentLocation
												+ "</td><td>"
												+ response.candidate[i].prefferedLocation
												+ "</td><td>"
												+ response.candidate[i].anyOffer
												+ "</td><td>"
												+ response.candidate[i].testScore
												+ "</td><td>"
												+ response.candidate[i].status
												+ "</td><td>"
												+ response.candidate[i].source
												+ "</td><td>"
												+ response.candidate[i].remarks
												+ "</td><td>"
												+ response.candidate[i].interviewDate
												+ "</td><td><a href='editCandidateAjax?id="
												+ response.candidate[i].id
												+ "'>Edit</a> |&nbsp&nbsp <a href='#' onclick='deleteCandidate("
												+ +response.candidate[i].id
												+ ");'>Delete</a>");
					}
					pageNumber = "";
					for (i = 0; i < response.pageno; i++) {
						j = i + 1;
						pageNumber += "<a href='#' onclick=loadCandidate(" + j + ");>" + j
								+ "</a>" + "&nbsp";
					}
					;
					$("#n").html(pageNumber);
				}
			});
}

deleteCandidate = function(id) {
	console.log("111")
	var con = confirm("Do you want to delete this Employee");
	if (con === true) {
		$.ajax({
			url : 'deleteCandidateAjax',
			type : 'POST',
			data : JSON.stringify({
				id : id,
			}),
			beforeSend : function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json");
			},
			success : function(data) {
				// alert(data.msg);
				loadCandidate();
			}
		});
	} else {
		return false;
	}
};

myFunction2 = function(page) {
	console.log("searchCandidate Function called");
	var val = $("#myInput").val();
	console.log("Entered Input " + val);
	$
			.ajax({
				url : 'searchCandidate?val=' + val + "&page=" + page,
				type : 'GET',
				success : function(response) {
					dd = response.candidate;
					console.log(".....Data@...."+dd)
					$('.tr').remove();
					for (i = 0; i < response.candidate.length; i++) {

						console.log(".........................");
						$('#tbl')
								.append(
										"<tr class='tr'><td>"
												+ response.candidate[i].date
												+ "</td><td>"
												+ response.candidate[i].candidateName
												+ "</td><td>"
												+ response.candidate[i].skills
												+ "</td><td>"
												+ response.candidate[i].clientName
												+ "</td><td>"
												+ response.candidate[i].mob
												+ "</td><td>"
												+ response.candidate[i].email
												+ "</td><td>"
												+ response.candidate[i].totalExp
												+ "</td><td>"
												+ response.candidate[i].relevantExp
												+ "</td><td>"
												+ response.candidate[i].currentCTC
												+ "</td><td>"
												+ response.candidate[i].expectedCTC
												+ "</td><td>"
												+ response.candidate[i].noticePeriod
												+ "</td><td>"
												+ response.candidate[i].organization
												+ "</td><td>"
												+ response.candidate[i].currentLocation
												+ "</td><td>"
												+ response.candidate[i].prefferedLocation
												+ "</td><td>"
												+ response.candidate[i].anyOffer
												+ "</td><td>"
												+ response.candidate[i].testScore
												+ "</td><td>"
												+ response.candidate[i].status
												+ "</td><td>"
												+ response.candidate[i].source
												+ "</td><td>"
												+ response.candidate[i].remarks
												+ "</td><td>"
												+ response.candidate[i].interviewDate
												+ "</td><td><a href='editCandidateAjax?id="
												+ response.candidate[i].id
												+ "'>Edit</a> |&nbsp&nbsp <a href='#' onclick='deleteCandidate("
												+ +response.candidate[i].id
												+ ");'>Delete</a>");
					}
					pageNumber = "";
					for (i = 0; i < response.pageno; i++) {
						j = i + 1;
						pageNumber += "<a href='#' onclick=myFunction2(" + j + ");>" + j
								+ "</a>" + "&nbsp";
						console.log(".......After Page....")
					}
					;
					$("#n").html(pageNumber);
				}
			});
}

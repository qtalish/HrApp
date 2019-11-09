data = '';
$(document).ready(function() {
	console.log("Test");
	load();
});

load = function(page) {
	var url2 = "listEmp?page=" + page;
	// alert("Hello")
	console.log(url2);
	$
			.ajax({
				url : url2,
				type : 'GET',
				success : function(response) {
					data = response.list;
					$('.tr').remove();
					for (i = 0; i < response.list.length; i++) {
						$('#tbl')
								.append(
										"<tr class='tr'><td>"
										        + response.list[i].empCode
										        + "</td><td>"
												+ response.list[i].fname+"  "+response.list[i].lname
												+ "</td><td>"
												+ response.list[i].email
												+ "</td><td>"
												+ response.list[i].designation
												+ "</td><td>"
												+ response.list[i].mob
												+ "</td><td><a href='editEmployeeAjax?id="
						
												+ response.list[i].id
												+ "' >Edit </a>| &nbsp&nbsp<a href='#' onclick='deleteEmp("
												+ response.list[i].id  
												+ ");'>Delete</a>"
												
												+ " |&nbsp&nbsp <a href='viewSalary?empCode="
												+ response.list[i].empCode
												+"' >Salary</a>"
												
												+ " |&nbsp&nbsp<a href='uploadDocumentAjax?empCode="
												+ response.list[i].empCode 
												+ "' >Upload </a></td>"
												);
					}
					 pagenumber = "";
 			            for (i = 0; i < response.pno; i++) {
			                j = i + 1;
			                pagenumber += "<a href='#' onclick=load(" + j + ");>" + j + "</a>" + "&nbsp";
			            }
			            $("#n").html(pagenumber);
				
				}
			
			});
};
/*
function myFunction() {
    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        console.log(value)
        load1();
        $(".tr1").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
//            load();
        });
    });
    
};
*/

deleteEmp = function(id) {
	console.log("111")
	var con = confirm("Do you want to delete this Employee");
	if (con === true) {
		$.ajax({
			url : 'deleteEmployeeAjax',
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
				load(1);
			}
		});
	} else {
		return false;
	}
};


myFunction2 = function(page){
	console.log(page);
	var val = $("#myInput").val();
	console.log(val)
	 $.ajax({
	        url: 'searchEmp?val='+val+  "&page="+page,
	        type: 'GET',
	        success: function (response) {
	            data = response.list;
	            $('.tr').remove();
	            for (i = 0; i < response.list.length; i++) {
	                $('#tbl')
					.append(
							"<tr class='tr'><td>"
							        + response.list[i].empCode
							        + "</td><td>"
									+ response.list[i].fname+"  "+response.list[i].lname
									+ "</td><td>"
									+ response.list[i].email
									+ "</td><td>"
									+ response.list[i].designation
									+ "</td><td>"
									+ response.list[i].mob
									+ "</td><td><a href='editEmployeeAjax?id="
									+ response.list[i].id
									+ "' >Edit</a>&nbsp&nbsp<a href='#' onclick='deleteEmp("
									+ response.list[i].id  
									+ ");'>|  Delete</a>"
									
									+ "|&nbsp&nbsp <a href='viewSalary?empCode="
									+ response.list[i].empCode
									+"' >Salary</a>"
									
									+ "|&nbsp&nbsp<a href='uploadDocumentAjax?empCode="
									+ response.list[i].empCode
									+ "' >Upload </a></td>"
									);
		}
	            pagenumber = "";
		            for (i = 0; i < response.pno; i++) {
	                j = i + 1;
	                pagenumber += "<a href='#' onclick=myFunction2(" + j + ");>" + j + "</a>" + "&nbsp";
	            }
	            $("#n").html(pagenumber);
	        }
	    });	
}
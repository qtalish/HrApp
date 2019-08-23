data = '';
$(document).ready(function() {
	console.log("test");
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
												+ "' >Edit |</a>&nbsp&nbsp<a href='#' onclick='deleteEmp("
												+ response.list[i].id + ",`"
												+ response.list[i].email
												+ "`);'>|  Delete</a></td>");
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
deleteEmp = function(id, email) {
	var con = confirm("Do you want to delete this Employee");
	if (con === true) {
		$.ajax({
			url : 'deleteEmployeeAjax',
			type : 'POST',
			data : JSON.stringify({
				id : id,
				email : email
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

forgot = function() {
	swal({
		  text: 'Please Enter your Email',
		  content: "input",
		  button: {
		    text: "Submit",
		    closeModal: false,
		  },
		})
	.then(name => {
// if (!name) throw null;
// console.log(name)
// console.log(name2)
		$.ajax({
		url : 'forgetPassword',
		type : 'POST',
		data : JSON.stringify({email : name}),  
		contentType:"application/json; charset=utf-8",
		dataType: 'json',
		success : function(data) {
			swal(data.msg,{
				icon : "success",
			});
		},
		error: function(err){
// console.log("AJAX error in request: " + JSON.stringify(err, null, 2));
// console.log("AJAX error in request: " + JSON.stringify(err.responseJSON.msg,
// null, 2));
			console.log("AJAX error in request: " +err.responseJSON.msg);
// console.log("failed")
// console.log(response)
// console.log(response.msg2)
			swal(err.responseJSON.msg,{
				icon : "error",
			});
		  }
	});
})
}
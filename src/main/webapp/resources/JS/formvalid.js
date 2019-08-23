$(document).ready(function() {
	$('#submit').click(function(e) {
		// Initializing Variables With Form Element Values
		var fname = $('#fname').val();
//		alert(fname)
		var addr = $('#address').val();
		var email = $('#email').val();
		var name_regex = /^[a-zA-Z\-]+$/;
		var email_regex =/^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		var add_regex = /^[0-9a-zA-Z]+$/;
		if (fname.length === 0) {
			$('#head').text("* All fields are mandatory *"); 
			$("#fname").focus();
//			alert("hi")
			return false;
		}
		// Validating Name Field.
		else if (!fname.match(name_regex) || fname.length == 0) {
			$('#p1').text("* For your name please use alphabets only *");
			$("#fname").focus();
			return false;
		}
		// Validating Email Field.
		else if (!email.match(email_regex) || email.length == 0) {
			$('#p2').text("* Please enter a valid email address *");
			$("#email").focus();
			return false;
		}
		// Validating Address Field.
		else if (!addr.match(add_regex) || addr.length == 0) {
			$('#p3').text("* For Address please use numbers and letters *"); 
			$("#address").focus();
			return false;
		}
	});
});
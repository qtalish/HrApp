$(document).ready(function() {
	$('#submit').click(function(e) {
		// Initializing Variables With Form Element Values
		alert("test")
		var firstname = $('#fname').val();
		var addr = $('#address').val();
		var email = $('#email').val();
		// Initializing Variables With Regular Expressions
		var name_regex = /^[a-zA-Z\-]+$/;
		var email_regex = /^[w-.+]+@[a-zA-Z0-9.-]+.[a-zA-z0-9]{2,4}$/;
		var add_regex = /^[0-9a-zA-Z]+$/;
		// To Check Empty Form Fields.
		if (firstname.length == 0) {
			$('#head').text("* All fields are mandatory *"); 
			$("#fname").focus();
			return false;
		}
		// Validating Name Field.
		else if (!firstname.match(name_regex) || firstname.length == 0) {
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
		} else {
			alert("Form Submitted Successfuly!");
			return true;
		}
	});
});
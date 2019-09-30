first = function()
{
var name_regex = /^[a-zA-Z]*$/;
var fname= $("#fname").val();
if(fname=="")
{
document.getElementById("fn").innerHTML="First Name Can Not Be Blank";
return false;
}
else if(fname.match(name_regex))
{
document.getElementById("fn").innerHTML="";
return true;
}
else
{
document.getElementById("fn").innerHTML="Enter Valid Name";
return false;
}
}




middle = function()
{
	var regex = /^[a-zA-Z]*$/;
	var mname= $("#mname").val();
	if(mname.match(regex))
	{
	document.getElementById("mn").innerHTML="";
	return true;
	}
	else
	{
	document.getElementById("mn").innerHTML="Enter Valid Name";
	return false;
	}	
}




emailId = function()
{
	var mail_regex=/^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$/
	var email= $("#email").val();
	if(email=="")
		{
		document.getElementById("em").innerHTML="Email Can Not Be Blank";
		return false;
		}
	else if(email.match(mail_regex))
		{
		document.getElementById("em").innerHTML="";
		return true;
		}
	else
		{
		document.getElementById("em").innerHTML="Please Enter Valid Email";
		return false;
		}
}




aadharValidate =function()
{

    var aadhar = $("#aadhar").val();
    var adharcardTwelveDigit = /^\d{12}$/;
    var adharSixteenDigit = /^\d{16}$/;

  if (aadhar=="") {
    	document.getElementById("aa").innerHTML="Aadhar Card Number Can Not Be Blank";
    	return false;
    }
  if (aadhar.match(adharcardTwelveDigit)) {
    	document.getElementById("aa").innerHTML="";
            return true;
        }
  else
	  {
	  document.getElementById("aa").innerHTML="Please Enter Valid Aadhar number";
	  return false;
	  }
    if (aadhar.match(adharSixteenDigit)) {
    	document.getElementById("aa").innerHTML="";
            return true;
        }
    else
    	{
    	document.getElementById("aa").innerHTML="Please Enter Valid Aadhar number";
    	return false;
    	}    
}








validatePAN = function()
{
	
	 var pan = $("#pan").val();
	 var pan_regex = /^([a-zA-Z]{5})(\d{4})([a-zA-Z]{1})$/;
	 if(pan=="")
		 {
		 document.getElementById("pa").innerHTML="PAN Card Number Can Not Be Blank";
	     return false;
		 }
	 if(pan.match(pan_regex))
		 {
		 document.getElementById("pa").innerHTML="";
		 return true;
		 }
	 else
		 {
		 document.getElementById("pa").innerHTML="Please Enter Valid PAN Card Number";
		 return false;
		 }	
}



psw = function()
{
 var password = $("#password").val();
 var psw_regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;
 
 if(password=="")
	 {
	 document.getElementById("psw").innerHTML="Password Can Not Be Blank";
     return false;
	 }
 if(password.match(psw_regex))
	 {
	 document.getElementById("psw").innerHTML="";
     return true;
	 }
 else
	 {
	 document.getElementById("psw").innerHTML="Must contain at least one number and one uppercase and lowercase letter, and at least 6 or more characters";
     return false;
	 }
}




mobile = function()
{
var mobile = $("#mob").val();
var num_regex= /^\d{10}$/;
   /*/^[0-9]*$/;*/
if(mobile=="")
	{
	 document.getElementById("mo").innerHTML="Mobile Number Can Not Be Blank";
     return false;
	 }
 if(mobile.match(num_regex))
	 {
	 document.getElementById("mo").innerHTML="";
	 return true;
	 }
 else
	 {
	 document.getElementById("mo").innerHTML="Please Enter Valid Mobile Number";
	 return false;
	 }		
}



sal = function()
{
	var salary = $("#salary").val();
	var num_regex=/^[0-9]*$/; 
	
	if(salary=="")
	{
	 document.getElementById("sa").innerHTML="Salary Can Not Be Blank";
     return false;
	 }
 if(salary.match(num_regex))
	 {
	 document.getElementById("sa").innerHTML="";
	 return true;
	 }
 else
	 {
	 document.getElementById("sa").innerHTML="Please Enter Valid Salary Amount ";
	 return false;
	 }		
}




lastName = function()
{
var lastName = $("#laname").val();
var name_regex = /^[a-zA-Z]*$/;
if(lastName=="")
	{
	 document.getElementById("lastName").innerHTML="Last Name Can Not Be Blank";
     return false;
	}
else if(lastName.match(name_regex))
{
document.getElementById("lastName").innerHTML="";
return true;
}
else
{
document.getElementById("lastName").innerHTML="Enter Valid Name";
return false;
}
}




bgValidate = function()
{
var bloodGroup = $("#bloodGroup").val();
var bg_regex = /^(A|B|AB|O)[+-]$/;
 if(bloodGroup.match(bg_regex))
{
document.getElementById("bg").innerHTML="";
return true;
}
else
{
document.getElementById("bg").innerHTML="Enter Valid Blood Group";
return false;
}
}

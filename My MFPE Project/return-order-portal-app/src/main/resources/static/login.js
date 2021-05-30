function validateLogin()
{
    return  validateUserName() && validatePassword();
}


function validateUserName()
{
 var userName=document.getElementById("username");
 var letters = /^[A-Za-z ]+$/;
  if(userName.value.trim()=="")
  {
	  userName.className="form-control is-invalid";
      errorUserName.innerHTML="Username cannot be blank";
	  successUserName.innerHTML="";
	  return false;
  }
  else if(!userName.value.match(letters))
  {
	  userName.className="form-control is-invalid";
	  errorUserName.innerHTML="User Name should be alphabet";
	  successUserName.innerHTML="";
	  return false;
  }
  else
  {
	userName.className="form-control is-valid";
	errorUserName.innerHTML="";
  }
  return true;
}


function validatePassword()
{
    var password=document.getElementById("password");
    var letters = /^[A-Za-z0-9]{6,10}$/;
     if(password.value.trim()=="")
     {
         password.className="form-control is-invalid";
         errorPassword.innerHTML="Password cannot be blank";
         successPassword.innerHTML="";
         return false;
     }
     else if(!password.value.match(letters))
     {
         password.className="form-control is-invalid";
         errorPassword.innerHTML="Password must have length between 6 to 10";
         successPassword.innerHTML="";
         return false;
     }
     else
     {
       password.className="form-control is-valid";
       errorPassword.innerHTML="";
     }
     return true;
   }

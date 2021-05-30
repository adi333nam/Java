function validateProcessRequest()
{
    return  validateUserName() && validateContactNumber() && validateCreditCardNumber() && validateComponentType()
    && validateComponentName() && validateQuantity();
}


function validateUserName()
{
 var userName=document.getElementById("userName");
 var letters = /^[A-Za-z ]+$/;
  if(userName.value.trim()=="")
  {
	  userName.className="form-control is-invalid";
	  errorUserName.innerHTML="User Name cannot be blank";
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


function validateContactNumber()
{
 var contactNumber=document.getElementById("contactNumber");
 var letters = /^[0-9]{10}$/;
  if(contactNumber.value.trim()=="")
  {
      contactNumber.className="form-control is-invalid";
	  errorContactNumber.innerHTML="Contact Number cannot be blank";
	  successContactNumber.innerHTML="";
	  return false;
  }
  else if(!contactNumber.value.match(letters))
  {
      contactNumber.className="form-control is-invalid";
	  errorContactNumber.innerHTML="Contact Number should contain number and its length should be 10 ";
	  successContactNumber.innerHTML="";
	  return false;
  }
  else
  {
	contactNumber.className="form-control is-valid";
	errorContactNumber.innerHTML="";
  }
  return true;
}
    

function validateComponentType()
{
 var componentType=document.getElementById("componentType");

  if(componentType.value.trim()=="")
  {
	  componentType.className="form-control is-invalid";
	  errorComponentType.innerHTML="Please select component type cannnot be blank";
	  successComponentType.innerHTML="";
	  return false;
  }
  else
  {
	componentType.className="form-control is-valid";
	errorComponentType.innerHTML="";
  }
  return true;
}

function validateCreditCardNumber()
{
	
    var validateCreditCardNumberRgx = /^(?:(4[0-9]{12}(?:[0-9]{3})?)|(5[1-5][0-9]{14})|(6(?:011|5[0-9]{2})[0-9]{12})|(3[47][0-9]{13})|(3(?:0[0-5]|[68][0-9])[0-9]{11})|((?:2131|1800|35[0-9]{3})[0-9]{11}))$/;
 
var creditCardNumber=document.getElementById("creditCardNumber");
    if(creditCardNumber.value.trim()=="")
    {
        creditCardNumber.className="form-control is-invalid";
        errorCreditCardNumber.innerHTML="Credit Card Number cannot be blank";
        successCreditCardNumber.innerHTML="";
        return false;
    }

    else if (!validateCreditCardNumberRgx.test(creditCardNumber.value))
        {
            creditCardNumber.className="form-control is-invalid";
            errorCreditCardNumber.innerHTML="Please Enter Valid Credit Card Number";
            successCreditCardNumber.innerHTML="";
            return false;                
        }

    else
    {
        creditCardNumber.className="form-control is-valid";
        errorCreditCardNumber.innerHTML="";
    }
    return true;
      
}
function validateComponentName()
{
 var componentName=document.getElementById("componentName");
 var letters = /^[A-Za-z ]+$/;
  if(componentName.value.trim()=="")
  {
	  componentName.className="form-control is-invalid";
	  errorComponentName.innerHTML="Component Name cannot be blank";
	  successComponentName.innerHTML="";
	  return false;
  }
  else if(!componentName.value.match(letters))
  {
	  componentName.className="form-control is-invalid";
	  errorComponentName.innerHTML="Component Name should be alphabet";
	  successComponentName.innerHTML="";
	  return false;
  }
  else
  {
    componentName.className="form-control is-valid";
	errorComponentName.innerHTML="";
  }
  return true;
}

function validateQuantity()
{
 var quantity=document.getElementById("quantity");
 var letters = /^\d{1,2}$/;
  if(quantity.value.trim()=="")
  {
    quantity.className="form-control is-invalid";
	  errorQuantity.innerHTML="Component Quantity cannot be blank";
	  successQuantity.innerHTML="";
	  return false;
  }
  else if(!quantity.value.match(letters))
  {
    quantity.className="form-control is-invalid";
	  errorQuantity.innerHTML="Component Quantity should contains only number";
	  successQuantity.innerHTML="";
	  return false;
  }
  else
  {
    quantity.className="form-control is-valid";
	errorQuantity.innerHTML="";
  }
  return true;
}

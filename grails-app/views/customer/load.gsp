<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Add A Customer</title>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
    <style>
		    *, *:before, *:after {
		  -moz-box-sizing: border-box;
		  -webkit-box-sizing: border-box;
		  box-sizing: border-box;
		}
		
		body {
		  font-family: 'Nunito', sans-serif;
		  color: #384047;
		}
		
		form {
		  max-width: 300px;
		  margin: 10px auto;
		  padding: 10px 20px;
		  background: #f4f7f8;
		  border-radius: 8px;
		}
		
		h1 {
		  margin: 0 0 30px 0;
		  text-align: center;
		}
		
		input[type="text"],
		input[type="password"],
		input[type="date"],
		input[type="datetime"],
		input[type="email"],
		input[type="number"],
		input[type="search"],
		input[type="tel"],
		input[type="time"],
		input[type="url"],
		textarea,
		select {
		  background: rgba(255,255,255,0.1);
		  border: none;
		  font-size: 16px;
		  height: auto;
		  margin: 0;
		  outline: 0;
		  padding: 15px;
		  width: 100%;
		  background-color: #e8eeef;
		  color: #8a97a0;
		  box-shadow: 0 1px 0 rgba(0,0,0,0.03) inset;
		  margin-bottom: 30px;
		}
		
		input[type="radio"],
		input[type="checkbox"] {
		  margin: 0 4px 8px 0;
		}
		
		select {
		  padding: 6px;
		  height: 32px;
		  border-radius: 2px;
		}
		
		button {
		  padding: 19px 39px 18px 39px;
		  color: #FFF;
		  background-color: #4bc970;
		  font-size: 18px;
		  text-align: center;
		  font-style: normal;
		  border-radius: 5px;
		  width: 100%;
		  border: 1px solid #3ac162;
		  border-width: 1px 1px 3px;
		  box-shadow: 0 -1px 0 rgba(255,255,255,0.1) inset;
		  margin-bottom: 10px;
		}
		
		fieldset {
		  margin-bottom: 30px;
		  border: none;
		}
		
		legend {
		  font-size: 1.4em;
		  margin-bottom: 10px;
		}
		
		label {
		  display: block;
		  margin-bottom: 8px;
		}
		
		label.light {
		  font-weight: 300;
		  display: inline;
		}
		
		.number {
		  background-color: #5fcf80;
		  color: #fff;
		  height: 30px;
		  width: 30px;
		  display: inline-block;
		  font-size: 0.8em;
		  margin-right: 4px;
		  line-height: 30px;
		  text-align: center;
		  text-shadow: 0 1px 0 rgba(255,255,255,0.2);
		  border-radius: 100%;
		}
		
		@media screen and (min-width: 480px) {
		
		  form {
		    max-width: 480px;
		  }
		
		}
    </style>
	</head>
	<body>
		<g:form name="edit_form" controller="customer"  >
		<title>edit_form</title>
		<div>
		    <g:hiddenField name="id" value="${aCustomer.customer_id}" />
		    <label for="lastName">Last Name</label>
		    <g:textField name="customer_last_name" value="${aCustomer.customer_last_name}"  required="false" disabled="disabled"/>
		    <br/>
		 </div>
		 <div>
		    <label for="firstName">First Name</label>
		    <g:textField name="customer_first_name" value="${aCustomer.customer_first_name}"  required="false" disabled="disabled"/>
		    <br/>
		 </div>
		 <div>
		    <label for="customer_id">ID</label>
		    <g:textField name="customer_id" value="${aCustomer.customer_id}"  required="false" disabled="disabled"/>
		    <br/>
		 </div>
		 <div>
		    <label for="companyname">Company Name</label>
		    <g:textField name="company_name"  value="${aCustomer.company_name}"/>
		    <br/>
		 </div>
		    <label for="address">Address</label>
		    <g:textField name="address" value="${aCustomer.address}" required="true"/>
		    <br/>
		    <g:actionSubmit  action ="editProfile" value="Save" />
		</g:form>
		<script>
			function validate(evt) {
				  var theEvent = evt || window.event;
				  var key = theEvent.keyCode || theEvent.which;
				  key = String.fromCharCode( key );
				  var regex = /[0-9]|\./;
				  if( !regex.test(key) ) {
				    theEvent.returnValue = false;
				    if(theEvent.preventDefault) theEvent.preventDefault();
				  }
				}
		</script>
	</body>
</html>
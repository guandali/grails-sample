<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Add A Customer</title>
	</head>
	<body>
		<g:form name="signup_form" controller="customer"  >
		<div>
		    <label for="lastName">Last Name</label>
		    <g:textField name="customer_last_name" value='' required="true"/>
		    <br/>
		 </div>
		 <div>
		    <label for="firstName">First Name</label>
		    <g:textField name="customer_first_name" value='' required="true"/>
		    <br/>
		 </div>
		 <div>
		    <label for="companyname">Company Name</label>
		    <g:textField name="company_name" placeholder="Optional "value=''/>
		    Opt out: <g:checkBox name="checkbox" value="no" />
		    <br/>
		 </div>
		    <label for="address">Address</label>
		    <g:textField name="address" value='' required="true"/>
		    <br/>
		    <label for="email_address">Email Address</label>
		    <input type="email"></><g:textField name="email_address" placeholder='Enter your Email' type='email' value='' required="true"/>
		    <br/>
		    <g:actionSubmit  action ="create"  name="create" value="Create An Account" />
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
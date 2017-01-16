<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Add A Customer</title>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.7/jquery.validate.min.js"></script>
	</head>
	<body>
	    <div>
	        <g:form   controller="customer" action="findbyid" onkeypress='validate(event)'>
            <g:textField name="q" value="${params.q}"/>
            <g:submitButton name="findbyid" value="Search"/>
        </g:form>
	    </div>
		<g:form name: "registerform" controller="customer" method="post" id='form-horizontal' class="form-horizontal">
		    <label for="lastName">Last Name</label>
		    <g:textField name="customer_last_name" value="${tem_customer.customer_last_name}" required="true"/>
		    <br/>
		    <label for="firstName">First Name</label>
		    <g:textField name="customer_first_name" value="${tem_customer.customer_first_name}" required="true"/>
		    <br/>
		    <label for="companyname">Company Name</label>
		    <g:textField name="company_name" placeholder="Optional "value="${tem_customer.customer_last_name}"/>
		    <br/>
		    <label for="address">Address</label>
		    <g:textField name="address" value="${tem_customer.customer_last_name}" required="true"/>
		    <br/>
		    <label for="email_address">Email Address</label>
		    <g:textField name="email_address" placeholder='Enter your Email' value='' required="true"/>
		    <br/>
		    <g:actionSubmit action="createauser" value="Create An Account" />
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
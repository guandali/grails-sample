<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Add A Customer</title>
	</head>
	<body>
		<g:form name: "registerform" controller="customer" method="post">
		    <label for="lastName">Last Name</label>
		    <g:textField name="customer_last_name" value="${tem_customer.customer_last_name}"/>
		    <br/>
		    <label for="firstName">First Name</label>
		    <g:textField name="customer_first_name" value="${tem_customer.customer_first_name}"/>
		    <br/>
		    <label for="companyname">Company Name</label>
		    <g:textField name="company_name" value="${tem_customer.customer_last_name}"/>
		    <br/>
		    <label for="address">Address</label>
		    <g:textField name="address" value="${tem_customer.customer_last_name}"/>
		    <br/>
		    <label for="emailaddress">Email Address</label>
		    <g:textField name="email_address" placeholder='Enter your Email',value='', "required"/>
		    <br/>
		    <g:actionSubmit action="createauser" value="Create An Account" />
		</g:form>
	</body>
</html>
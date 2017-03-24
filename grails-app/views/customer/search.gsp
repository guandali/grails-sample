<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Search A Customer By ID</title>
</head>
<body>
	<g:form controller="customer" action="findbyid">
		<g:textField name="q" value="${params.q}" />
		<g:submitButton name="findbyid" value="Search" />
	</g:form>
</body>
</html>
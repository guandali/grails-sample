<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main" />
<title>Customer List</title>
<style>
.button {
	background-color: #4CAF50;
	border: none;
	color: white;
	padding: 10px 24px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
}
</style>

</head>
<body>
	<table>
		<tr>
			<td>Full Name</td>
			<td>Email</td>
			<td>Customer ID</td>
		</tr>
		<g:each in="${list}" var="customer">
			<tr>
				<td>
					${customer.customer_last_name}, ${customer.customer_first_name}
				</td>
				<td>
					${customer.email_address}
				</td>
				<td>
					${customer.customer_id}
				</td>
				<td><g:form url="[resource:customer, action:'load']"
						method="PUT">
						<g:actionSubmit class="load" action="load" value="Update" />
					</g:form></td>>
			</tr>
		</g:each>
	</table>
</body>
</html>
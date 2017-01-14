<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Customer List</title>
</head>
<body>
    <table>
        <tr>
            <td>Full Name</td>
            <td>Email</td>
            <td>Customer ID </td>
        </tr>
        <g:each in="${list}" var="customer">
        <tr>
            <td>${customer.customer_last_name}, ${customer.customer_first_name}</td>
            <td>${customer.email_address}</td>
            <td>${customer.customer_id} </td>
        </tr>
        </g:each>        
    </table>
</body>
</html>
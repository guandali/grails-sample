<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
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
            <td>Customer ID </td>
        </tr>
        <g:each in="${list}" var="customer">
        <tr>
            <td>${customer.customer_last_name}, ${customer.customer_first_name}</td>
            <td>${customer.email_address}</td>
            <td>${customer.customer_id} </td>
            <td> <button  type="button" class="button"> <a href="/customer_manager/${customer.customer_id}/edit"> Edit </a></button> </td>>
        </tr>
        </g:each>        
    </table>
</body>
</html>
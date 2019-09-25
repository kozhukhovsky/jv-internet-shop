<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Index</title>
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/login">Login</a><br>
        <a href="${pageContext.request.contextPath}/registration">Registration</a><br>
        <a href="${pageContext.request.contextPath}/servlet/users">List of Users</a><br>
        <a href="${pageContext.request.contextPath}/servlet/items">List of Items</a><br>
        <a href="${pageContext.request.contextPath}/servlet/orders">List of Orders</a><br>
        <a href="${pageContext.request.contextPath}/servlet/bucket">Bucket</a><br>
    </body>
</html>

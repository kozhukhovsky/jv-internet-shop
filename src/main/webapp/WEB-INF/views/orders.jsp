<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Orders</title>
    </head>
    <body>
        <h1>List of Order:</h1>
        <table border="1" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>UserID</th>
                <th>Delete</th>
            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><c:out value="${order.id}"/></td>
                    <td><c:out value="${order.userId}"/></td>
                    <td><a href="${pageContext.request.contextPath}/servlet/deleteOrder?order_id=${order.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/">Back to Index Page</a>
    </body>
</html>

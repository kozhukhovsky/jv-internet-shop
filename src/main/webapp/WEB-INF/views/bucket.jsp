<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Bucket</title>
    </head>
    <body>
        <h1>List of Item in Bucket:</h1>
        <table border="1" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Remove</th>
            </tr>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td><c:out value="${item.id}"/></td>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.price}"/></td>
                    <td><a href="${pageContext.request.contextPath}/servlet/removeItemFromBucket?item_id=${item.id}">Remove</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/servlet/completeOrder">Complete Order</a><br>
        <a href="${pageContext.request.contextPath}/servlet/items">Back to List of Item</a>
    </body>
</html>

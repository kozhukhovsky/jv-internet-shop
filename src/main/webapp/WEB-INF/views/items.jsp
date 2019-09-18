<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Items</title>
    </head>
    <body>
        <h1>List of Item:</h1>
        <table border="1" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Delete</th>
                <th>Bucket</th>
            </tr>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td><c:out value="${item.id}"/></td>
                    <td><c:out value="${item.name}"/></td>
                    <td><c:out value="${item.price}"/></td>
                    <td><a href="${pageContext.request.contextPath}/deleteItem?item_id=${item.id}">Delete</a></td>
                    <td><a href="${pageContext.request.contextPath}/addItemToBucket?item_id=${item.id}">Add</a></td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/additionItems">Add Item</a>
        <a href="${pageContext.request.contextPath}/">Back to Index Page</a>
    </body>
</html>

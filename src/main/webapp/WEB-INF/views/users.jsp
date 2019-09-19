<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Users</title>
    </head>
    <body>
        <h1>List of Users:</h1>
        <table border="1" cellspacing="0">
            <tr>
                <th>ID</th>
                <th>Login</th>
                <th>Name</th>
                <th>Delete</th>
            </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><a href="${pageContext.request.contextPath}/deleteUser?user_id=${user.id}">Delete</a></td>
            </tr>
        </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/registration">Register User</a>
        <a href="${pageContext.request.contextPath}/">Back to Index Page</a>
    </body>
</html>

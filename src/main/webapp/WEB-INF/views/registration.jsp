<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Users Registration</title>
    </head>
    <body>
        <div>${errorMsg}</div>
        <form action="${pageContext.request.contextPath}/registration" method="post">
            Name: <input type="text" placeholder="Input your name" name="user_name" required><br>
            Login: <input type="text" placeholder="Input your login" name="user_login" required><br>
            Password: <input type="password" placeholder="Input your password"
                             name="user_password"
                             required><br>
            <button type="submit">Register</button>
            <a href="${pageContext.request.contextPath}/login">Login</a><br>
            <a href="${pageContext.request.contextPath}/">Back to Index Page</a>
        </form>
    </body>
</html>

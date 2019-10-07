<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
    </head>
    <body>
        <div>${errorMsg}</div>
        <form action="${pageContext.request.contextPath}/login" method="post">
            Login: <input type="text" placeholder="Input your login" name="user_login" required><br>
            Password: <input type="password" placeholder="Input your password"
                             name="user_password" required><br>
            <button type="submit">Login</button>
        </form>
        <a href="${pageContext.request.contextPath}/">Back to Index Page</a>
    </body>
</html>

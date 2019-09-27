<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Addition Items</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/servlet/createItem" method="post">
            Name: <input type="text" placeholder="Input name" name="item_name" required><br>
            Price: <input type="text" placeholder="Input price" name="item_price" required><br>
            <button type="submit">Add Item</button>
        </form>
    </body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Items</title>
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/bulma/0.7.5/css/bulma.min.css">
    </head>
    <body>
        <nav class="navbar is-light" role="navigation" aria-label="main navigation">
            <div id="navbarBasicExample" class="navbar-menu">
                <div class="navbar-start">
                    <a class="navbar-item" href="${pageContext.request.contextPath}/">
                        Home
                    </a>
                    <c:if test="${user == 'true'}">
                        <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/items">
                            Items
                        </a>
                        <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/orders">
                            Orders
                        </a>
                        <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/bucket">
                            Bucket
                        </a>
                    </c:if>
                    <c:if test="${admin == 'true'}">
                        <div class="navbar-item has-dropdown is-hoverable">
                            <a class="navbar-link">
                                Admin
                            </a>
                            <div class="navbar-dropdown">
                                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/admin/items">
                                    Edit items
                                </a>
                                <a class="navbar-item" href="${pageContext.request.contextPath}/servlet/admin/users">
                                    Users
                                </a>
                            </div>
                        </div>
                    </c:if>
                </div>
                <div class="navbar-end">
                    <c:choose>
                        <c:when test="${login == 'true'}">
                            <div class="navbar-item">
                                <a class="button is-primary is-outlined" href="${pageContext.request.contextPath}/logout">
                                    Logout
                                </a>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="navbar-item">
                                <div class="buttons">
                                    <a class="button is-primary" href="${pageContext.request.contextPath}/registration">
                                        <strong>Sign up</strong>
                                    </a>
                                    <a class="button is-primary is-outlined" href="${pageContext.request.contextPath}/login">
                                        Log in
                                    </a>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </nav>
        <section class="section">
            <div class="columns is-centered">
                <div class="column is-half">
                    <h1 class="title">Items</h1>
                    <table class="table is-striped is-fullwidth">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Name</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}">
                                <tr>
                                    <td><c:out value="${item.id}"/></td>
                                    <td><c:out value="${item.name}"/></td>
                                    <td><c:out value="${item.price}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
    </body>
</html>

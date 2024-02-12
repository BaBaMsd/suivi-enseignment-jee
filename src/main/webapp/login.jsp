<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Admin Login</h2>
    <%-- Afficher un message d'erreur s'il y en a un --%>
    <c:if test="${not empty errorMessage}">
        <p style="color: red;">${errorMessage}</p>
    </c:if>
    <form action="login" method="post">
        Username: <input type="text" name="username"><br /><br />
        Password: <input type="password" name="password"><br /><br />
        <input type="submit" value="Login">
    </form>
</body>
</html>

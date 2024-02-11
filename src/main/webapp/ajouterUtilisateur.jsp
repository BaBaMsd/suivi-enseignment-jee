<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Ajouter un utilisateur</title>
</head>
<body>
    <h1>Ajouter un nouvel utilisateur</h1>
    <form action="${pageContext.request.contextPath}/users/add" method="post">
        <label for="name">Username :</label>
        <input type="text" id="name" name="username" required><br><br>
        <label for="email">Password :</label>
        <input type="password" id="email" name="password" required><br><br>
        <input type="submit" value="Ajouter">
    </form>
</body>
</html>
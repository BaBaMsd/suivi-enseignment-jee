<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Actualiser l'Avancement</title>
</head>
<body>
    <h2>Actualiser l'Avancement</h2>
    <form action="avancement" method="post">
        <input type="hidden" name="idMatiere" value="<%= request.getParameter("idMatiere") %>">
        <label for="avancement">Avancement:</label>
        <input type="text" id="avancement" name="avancement"><br><br>
        <input type="submit" value="Mettre à Jour">
    </form>
</body>
</html>

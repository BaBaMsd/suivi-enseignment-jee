<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.List" %>
<%@ page import="model.Enseignant" %>
<%@ page import="model.Semestre" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des enseignants</title>
</head>
<body>
<h1>Liste des enseignants</h1>

<!-- Formulaire d'ajout -->
<form action="enseignants" method="post">
    <input type="hidden" name="action" value="add">
    Nom: <input type="text" name="nom">
    <input type="submit" value="Ajouter">
</form>

<!-- Liste des enseignants -->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
        		<th>Nom</th>
        		<th>Actions</th>
            </tr>
        </thead>
        <tbody>
         <%
         List<Enseignant> enseignants =  (List<Enseignant>)request.getAttribute("enseignants");
                
                for (Enseignant enseignant : enseignants) {
            %>
        <tr>
            <td><%= enseignant.getId() %></td>
            <td><%= enseignant.getNom() %></td>
            <td>
                <a href="edit?id=${enseignant.id}">Modifier</a>
                <a href="enseignants?action=delete&id=${enseignant.id}">Supprimer</a>
            </td>
          </tr>
            <% 
                    }
            %>
        </tbody>
</table>
</body>
</html>
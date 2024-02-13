<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Avancement" %>
<%@ page import="model.Matiere" %>

<!DOCTYPE html>
<html>
<head>
    <title>Liste des Matières</title>
</head>
<body>
    <h2>Liste des Matières</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Matière</th>
                <th>Avancement</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<Avancement> matieresAvancement = (List<Avancement>) request.getAttribute("matieresAvancement");
            
            for (Avancement avancement : matieresAvancement) { %>
                <tr>
                    <td><%= avancement.getMatiere().getNom() %></td>
                    <td><%= avancement.getAvancement() %></td>
                    <td>
                        <% if (avancement.getAvancement() < avancement.getMatiere().getChargeHorairesPlanifies()) { %>
                            <a href="ActualiserAvancement.jsp?idMatiere=<%= avancement.getMatiere().getId() %>">Actualiser</a>
                        <% } else { %>
                            Avancement complet
                        <% } %>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <br>
</body>
</html>

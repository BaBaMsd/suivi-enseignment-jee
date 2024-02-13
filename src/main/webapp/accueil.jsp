<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Matiere" %>
<%@ page import="model.Semestre" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
</head>
<body>
    <h2>Bienvenue sur la page d'accueil</h2>
    <p>Cette page est accessible après une authentification réussie.</p>
    
    <!-- Lien vers la page enseignants.jsp -->
    <a href="${pageContext.request.contextPath}/enseignants">Gérer les enseignants</a>
    <a href="${pageContext.request.contextPath}/matieres">Gestion des Pointage</a>
    <a href="${pageContext.request.contextPath}/avancement">Gérer l'avancement</a> <!-- Lien vers la page d'avancement -->
    
    <table border="1">
        <thead>
            <tr>
                <th>Semestre</th>
                <th>Matières</th>
                <th>Enseignant</th>
                <th>Type Enseignant</th>
                <th>Charge</th>
            </tr>
        </thead>
        <tbody>
            <% 
                List<Matiere> matiereList = (List<Matiere>) request.getAttribute("matiereList");
                
                for (Matiere matiere : matiereList) {
            %>
            <tr>
                <td><%= matiere.getSemestre() != null ? matiere.getSemestre().getSemestre(): "" %></td>
                <td><%= matiere.getNom() %></td>
                <td><%= matiere.getEnseignant() != null ? matiere.getEnseignant().getNom() : "" %></td>
                <td><%= matiere.getEnseignant() != null ? matiere.getEnseignant().getType() : "" %></td>
                <td><%= matiere.getChargeHorairesPlanifies() %></td>
            </tr>
            <% 
                    }
            %>
        </tbody>
    </table>
</body>
</html>

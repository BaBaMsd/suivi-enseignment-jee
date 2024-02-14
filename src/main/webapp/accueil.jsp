<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Matiere" %>
<%@ page import="model.Semestre" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body> 
    <!-- Liens vers d'autres pages -->
    <div class="container">
    <h2>Bienvenue sur la page d'accueil</h2>
   			 <p>Cette page est accessible après une authentification réussie.</p>
        <div class="row">
            <div class="col">
             
                <a href="${pageContext.request.contextPath}/enseignants" class="btn btn-primary">Gérer les enseignants</a>
            </div>
            <div class="col">
                <a href="${pageContext.request.contextPath}/matieres" class="btn btn-primary">Gestion des Pointage</a>
            </div>
            <div class="col">
                <a href="${pageContext.request.contextPath}/avancement" class="btn btn-primary">Gérer l'avancement</a>
            </div>
        </div>
    </div>


</body>
</html>

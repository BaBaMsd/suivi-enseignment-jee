<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Matiere" %>
<%@ page import="model.Semestre" %>
<%@ page import="model.Avancement" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Accueil</title>
     <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.7.0/chart.min.js"></script>
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
    
     <h2>État d'avancement par semestre</h2>
    <canvas id="avancementChart" width="400" height="400"></canvas>
    <script>
        // Récupérer les données d'avancement par semestre depuis les attributs de requête
        var semestres = [];
        var avancements = [];
        var dataset = [];

        <%
        List<Semestre> semestresAffiches = (List<Semestre>) request.getAttribute("semestresAffiches");

        List<Avancement> avancementsSemestre = (List<Avancement>) request.getAttribute("avancementsSemestre");
        for (Semestre semestre : semestresAffiches) {
        	%>
        	semestres.push("<%= semestre.getSemestre() %>");
        	<%
            for (Avancement avancement : avancementsSemestre) {
                if(avancement.getMatiere().getSemestre().getId() == semestre.getId()){
       		 %>
                
                avancements.push(<%= avancement.getAvancement() %>);

        		<% }
            }
         } %>

        dataset.push({
            label: 'Avancement',
            data: avancements,
            backgroundColor: 'rgba(255, 99, 132, 0.2)',
            borderColor: 'rgba(255, 99, 132, 1)',
            borderWidth: 1
        });

        // Créer le diagramme
        var ctx = document.getElementById('avancementChart').getContext('2d');
        var avancementChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: semestres,
                datasets: dataset
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    </script>

</body>
</html>

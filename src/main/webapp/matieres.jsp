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
<div class="container mt-5">
    <center><h3>Matieres par Semestre</h3></center>
    <hr>
        <%

        List<Semestre> semestresAffiches = (List<Semestre>) request.getAttribute("semestresAffiches");
    	List<Matiere> matiereList = (List<Matiere>) request.getAttribute("matiereList");
            // Parcourir chaque semestre
            for (Semestre semestre : semestresAffiches) {
                    // Générer un card pour chaque semestre
        %>
        <div class="col">
            <div class="card mb-3">
                <div class="card-header">
                   <center> <h4><%= semestre.getNiveau().getNiveau() +"("+ semestre.getSemestre() +")" %></h4></center>
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Matières</th>
                                <th>Enseignant</th>
                                <th>Type Enseignant</th>
                                <th>Charge</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                // Parcourir chaque matière pour ce semestre
                                for (Matiere matiereForSemestre : matiereList) {
                                    if (matiereForSemestre.getSemestre().getId() == semestre.getId()) {
                            %>
                            <tr>
                                <td><%= matiereForSemestre.getNom() %></td>
                                <td><%= matiereForSemestre.getEnseignant() != null ? matiereForSemestre.getEnseignant().getNom() : "" %></td>
                                <td><%= matiereForSemestre.getEnseignant() != null ? matiereForSemestre.getEnseignant().getType() : "" %></td>
                                <td><%= matiereForSemestre.getChargeHorairesPlanifies() %></td>
                            </tr>
                            <%
                                    }
                                }
                            %>
                        </tbody>
                    </table>
                </div> <!-- fermeture du card-body -->
            </div> <!-- fermeture du card -->
        </div> <!-- fermeture de la colonne -->
        <%
                }
        %>
    </div> <!-- fermeture de la nouvelle ligne -->
</div> <!-- fermeture du container -->


</body>
</html>

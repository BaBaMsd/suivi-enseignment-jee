<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Matières</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="mt-4 mb-4">Gestion des Matières</h1>

    <!-- Boucle pour chaque niveau et semestre -->
    <c:forEach var="niveau" items="${niveauList}">
        <c:forEach var="semestre" items="${niveau.semestres}">
            <div class="card mb-4">
                <div class="card-header">
                    ${niveau.niveau} - ${semestre.semestre}
                </div>
                <div class="card-body">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Nom de la Matière</th>
                            <th>Enseignant</th>
                            <th>Type d'Enseignant</th>
                            <th>Charge Horaire</th>
                            <th>Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Boucle pour chaque matière du semestre -->
                        <c:forEach var="matiere" items="${semestre.matieres}">
                            <tr>
                                <td>${matiere.nom}</td>
                                <td>${matiere.enseignant.nom}</td>
                                <td>${matiere.enseignant.type}</td>
                                <td>${matiere.chargeHorairesPlanifies}h</td>
                                <td>
                                    <!-- Formulaire pour actualiser les heures de charge -->
                                    <form action="<%= request.getContextPath() %>/matieres/update" method="post">
                                        <input type="hidden" name="id" value="${matiere.id}">
                                        <input type="number" name="nouveauCharge" required>
                                        <button type="submit" class="btn btn-primary">Actualiser</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </c:forEach>
    </c:forEach>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Matières</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
.custom-header {
  background-color: #008080; /* Teal */
  color: white;
  padding: 10px 20px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.menu-button {
  background-color: transparent;
  border: none;
  color: white;
  font-size: 24px;
  cursor: pointer;
}

.header-content h1 {
  margin: 0;
  font-size: 24px;
}
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 500px;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center; /* Centrer le contenu */
        }
        h2 {
            color: black;
        }
        form {
            margin-top: 20px;
        }
        label {
            font-weight: bold;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            background-color: #4CAF50; /* Vert */
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049; /* Légèrement plus foncé au survol */
        }
</style>
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
    <script>
function w3_open() {
  document.getElementById("main").style.marginLeft = "250px";
  document.getElementById("mySidebar").style.display = "block";
}
function w3_close() {
  document.getElementById("main").style.marginLeft = "0";
  document.getElementById("mySidebar").style.display = "none";
}

function toggleSubMenu(subMenuId) {
  var subMenu = document.getElementById(subMenuId);
  if (subMenu.style.display === "none") {
    subMenu.style.display = "block";
  } else {
    subMenu.style.display = "none";
  }
}



</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

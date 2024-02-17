
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Avancement" %>
<%@ page import="model.Matiere" %>
<%@ page import="model.Semestre" %>
<%@ page import="dao.CalculAvancement" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des enseignants</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous">
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
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        h1 {
            text-align: center;
            margin-top: 30px;
        }
          
          h2 {
            text-align: center;
            margin-top: 30px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            border: 1px solid #ddd;
            background-color: #fff;
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        tr:hover {
            background-color: #f9f9f9;
        }
        form {
            margin: 20px auto;
            width: 50%;
            padding: 20px;
            border: 1px solid #ddd;
            background-color: #fff;
        }
        input[type="text"] {
            width: 70%;
            padding: 8px;
            margin-right: 10px;
            box-sizing: border-box;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
        a {
            text-decoration: none;
            color: #007bff;
            margin-right: 10px;
        }
        a:hover {
            text-decoration: underline;
        }
        .action-buttons a {
            margin-right: 5px;
            display: inline-block;
            width: 30px;
            height: 30px;
            text-align: center;
            line-height: 30px;
            border-radius: 50%;
            color: gray;
        }
    </style>
</head>
<body>

<div class="w3-sidebar w3-bar-block w3-card w3-animate-left" style="display:none; width: 250px;" id="mySidebar">
  <p><img class="logo" src="https://iscae.mr/sites/default/files/logo-iscae.png" alt="ISCAE" style="width: 60px; height: auto;"><i>ISCAESUIV</i><img class="logo" src="https://i.pinimg.com/236x/9c/ad/29/9cad29e1f4e184ea52c313b31ba8d10c.jpg" alt="ISCAE" style="width: 20px; height: auto;"></p>

  <a href="test2.jsp" class="w3-bar-item w3-button" onclick="toggleSubMenu('homeSubMenu')"><i class="fas fa-home"></i>Accueil</a>

  <a href="#" class="w3-bar-item w3-button" onclick="toggleSubMenu('teachersSubMenu')"><i class="fas fa-user"></i> Professeurs</a>
  <div id="teachersSubMenu" style="display: none; background-color: #f9f9f9; box-shadow: 0 5px 7px rgba(0, 0, 0, 0.1); border-radius: 5px; padding: 15px;">
    <a href="${pageContext.request.contextPath}/enseignants" style="display: block; padding: 10px; color: #333; text-decoration: none; transition: background-color 0.3s;">Liste des enseignants</a>
</div>

  <a href="#" class="w3-bar-item w3-button" onclick="toggleSubMenu('subjectsSubMenu')"><i class="fas fa-book"></i> Matieres</a>
  <div id="subjectsSubMenu" style="display: none;">
        <a href="${pageContext.request.contextPath}/matieres" style="display: block; padding: 10px; color: #333; text-decoration: none; transition: background-color 0.3s;">Liste des matieres</a>
      </div>

    
<a href="#" class="w3-bar-item w3-button" onclick="toggleSubMenu('avancementSubMenu')"><i class="fas fa-chart-line"></i> Avancement</a>
<div id="avancementSubMenu" style="display: none; background-color: #f9f9f9; box-shadow: 0 5px 7px rgba(0, 0, 0, 0.1); border-radius: 5px; padding: 15px;">
    <a href="${pageContext.request.contextPath}/avancement" style="display: block; padding: 10px; color: #333; text-decoration: none; transition: background-color 0.3s;">Liste des avancements</a>
</div>
 
    <button class="w3-bar-item w3-button w3-large" onclick="w3_close()">Close &times;</button>
  
</div>

<div id="main">
<div class="custom-header">
  <button id="openNav" class="menu-button" onclick="w3_open()">&#9776;</button>
  <div class="header-content">
    <h1>Welcome to My Page</h1>
    
  </div>
</div>
       <h2>Liste des Matières</h2>
       
            <% 	List<Semestre> semestresAffiches = (List<Semestre>) request.getAttribute("semestresAffiches");
                 List<Avancement> matieresAvancement = (List<Avancement>) request.getAttribute("matieresAvancement");
                 for (Semestre semestre : semestresAffiches) { %>
                	 <div class="card mb-3">
                     <div class="card-header">
                        <center> <h4><%= semestre.getNiveau().getNiveau() +"("+ semestre.getSemestre() +")" %></h4></center>
                     </div>
                    <canvas id="chart_<%= semestre.getId() %>" width="400" height="400"></canvas>
                     
                  <table>
		            <thead>
		                <tr>
		                    <th>Matière</th>
		                    <th>Enseignant</th>
		                    <th>Avancement</th>
		                    <th>Action</th>
		                </tr>
		            </thead>
		            <tbody>
          		 <%     for (Avancement avancement : matieresAvancement) {
                     if (avancement.getMatiere().getSemestre().getId() == semestre.getId()) {

          			 %>
          		 
                    <tr>
                        <td><%= avancement.getMatiere().getNom() %></td>
                        <td><%= avancement.getMatiere().getEnseignant().getNom() %></td>
                        <td><%= avancement.getAvancement() %></td>
                
                        <td class="action-cell">
                            <% if (avancement.getAvancement() < avancement.getMatiere().getChargeHorairesPlanifies()) { %>
                                <a href="ActualiserAvancement.jsp?idMatiere=<%= avancement.getMatiere().getId() %>"><i class="fas fa-sync-alt"></i></a>
                            <% } else { %>
                                Avancement complet
                            <% } %>
                        </td>
                    </tr>
                <% } 
                }%>
            </tbody>
        </table>
        </div>
        <%
                }
        %>

    <!-- Pagination -->
   </div>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    
    <script>
    // Fonction pour ouvrir la barre latérale
    function w3_open() {
      document.getElementById("main").style.marginLeft = "250px";
      document.getElementById("mySidebar").style.display = "block";
    }

    // Fonction pour fermer la barre latérale
    function w3_close() {
      document.getElementById("main").style.marginLeft = "0";
      document.getElementById("mySidebar").style.display = "none";
    }

    // Fonction pour basculer l'affichage du sous-menu
    function toggleSubMenu(subMenuId) {
      var subMenu = document.getElementById(subMenuId);
      if (subMenu.style.display === "none") {
        subMenu.style.display = "block";
      } else {
        subMenu.style.display = "none";
      }
    }
    </script>
    <script>
    <% for (Semestre semestre : semestresAffiches) { %>
    var avancementSemestre_<%= semestre.getId() %> = <%= CalculAvancement.calculerAvancementSemestre(semestre.getId(), matieresAvancement) %>;
    var chargeInitialeSemestre_<%= semestre.getId() %> = <%= CalculAvancement.calculerChargeInitialeSemestre(semestre.getId(), matieresAvancement) %>;
        var pourcentageAvancement_<%= semestre.getId() %> = (avancementSemestre_<%= semestre.getId() %> / chargeInitialeSemestre_<%= semestre.getId() %>) * 100;

        var ctx = document.getElementById("chart_<%= semestre.getId() %>").getContext('2d');
        var myChart = new Chart(ctx, {
            type: 'doughnut',
            data: {
                labels: ["Avancement", "Reste"],
                datasets: [{
                    label: 'Avancement',
                    data: [pourcentageAvancement_<%= semestre.getId() %>, 100 - pourcentageAvancement_<%= semestre.getId() %>],
                    backgroundColor: [
                        'rgba(75, 192, 192, 0.2)',
                        'rgba(255, 99, 132, 0.2)'
                    ],
                    borderColor: [
                        'rgba(75, 192, 192, 1)',
                        'rgba(255, 99, 132, 1)'
                    ],
                    borderWidth: 1
                }]
            },
            options: {
                responsive: false
            }
        });
    <% } %>
</script>
    

</body>
</html>




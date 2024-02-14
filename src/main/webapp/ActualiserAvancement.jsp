<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous">
    <title>Actualiser l'Avancement</title>
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
  <div class="w3-sidebar w3-bar-block w3-card w3-animate-left" style="display:none; width: 250px;" id="mySidebar">
  <p><img class="logo" src="https://iscae.mr/sites/default/files/logo-iscae.png" alt="ISCAE" style="width: 60px; height: auto;"><i>ISCAESUIV</i><img class="logo" src="https://i.pinimg.com/236x/9c/ad/29/9cad29e1f4e184ea52c313b31ba8d10c.jpg" alt="ISCAE" style="width: 20px; height: auto;"></p>

  <a href="test2.jsp" class="w3-bar-item w3-button" onclick="toggleSubMenu('homeSubMenu')"><i class="fas fa-home"></i>Accueil</a>

  <a href="#" class="w3-bar-item w3-button" onclick="toggleSubMenu('teachersSubMenu')"><i class="fas fa-user"></i> Professeurs</a>
  <div id="teachersSubMenu" style="display: none; background-color: #f9f9f9; box-shadow: 0 5px 7px rgba(0, 0, 0, 0.1); border-radius: 5px; padding: 15px;">
    <a href="${pageContext.request.contextPath}/enseignants" style="display: block; padding: 10px; color: #333; text-decoration: none; transition: background-color 0.3s;">Liste des enseignants</a>
</div>

  <a href="#" class="w3-bar-item w3-button" onclick="toggleSubMenu('subjectsSubMenu')"><i class="fas fa-book"></i> Matieres</a>
  <div id="subjectsSubMenu" style="display: none;">
    <a href="#" class="w3-bar-item w3-button">Liste des matieres</a>
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




    <div class="container">
        <h2>Actualiser l'Avancement</h2>
        <form action="avancement" method="post">
            <input type="hidden" name="idMatiere" value="<%= request.getParameter("idMatiere") %>">
            <label for="avancement"></label>
            <input type="text" id="avancement" name="avancement" required><br><br>
            <input type="submit" value="Mettre à Jour">
        </form>
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
</body>
</html>

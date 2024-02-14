<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
}

#calendar {
  max-width: 400px;
  margin: 0 auto;
}

.calendar-header {
  text-align: center;
  margin-bottom: 10px;
}

.calendar-table {
  width: 100%;
  border-collapse: collapse;
}

.calendar-table th,
.calendar-table td {
  border: 1px solid #ccc;
  padding: 5px;
  text-align: center;
}

.calendar-table th {
  background-color: #f2f2f2;
}


</style>
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


<div id="calendar"></div>
  <script src="script.js"></script>
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

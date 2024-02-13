<!DOCTYPE html>
<html>
<title>W3.CSS</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous">

<body>

<div class="w3-sidebar w3-bar-block w3-card w3-animate-left" style="display:none; width: 250px;" id="mySidebar">
  <button class="w3-bar-item w3-button w3-large" onclick="w3_close()">Close &times;</button>
  <a href="#" class="w3-bar-item w3-button" onclick="toggleSubMenu('homeSubMenu')"><i class="fas fa-home"></i>Accueil</a>
  <div id="homeSubMenu" style="display: none;">
    <a href="#" class="w3-bar-item w3-button">Sous-menu 1</a>
    <a href="#" class="w3-bar-item w3-button">Sous-menu 2</a>
  </div>
  <a href="#" class="w3-bar-item w3-button" onclick="toggleSubMenu('teachersSubMenu')"><i class="fas fa-user"></i> Professeurs</a>
  <div id="teachersSubMenu" style="display: none;">
    <a href="#" class="w3-bar-item w3-button">Sous-menu 1</a>
    <a href="#" class="w3-bar-item w3-button">Sous-menu 2</a>
  </div>
  <a href="#" class="w3-bar-item w3-button" onclick="toggleSubMenu('subjectsSubMenu')"><i class="fas fa-book"></i> Matieres</a>
  <div id="subjectsSubMenu" style="display: none;">
    <a href="#" class="w3-bar-item w3-button">Sous-menu 1</a>
    <a href="#" class="w3-bar-item w3-button">Sous-menu 2</a>
  </div>
  <a href="#" class="w3-bar-item w3-button" onclick="toggleSubMenu('coursesSubMenu')"><i class="fas fa-graduation-cap"></i> Filieres</a>
  <div id="coursesSubMenu" style="display: none;">
    <a href="#" class="w3-bar-item w3-button">Sous-menu 1</a>
    <a href="#" class="w3-bar-item w3-button">Sous-menu 2</a>
  </div>
</div>

<div id="main">

<div class="w3-teal">
  <button id="openNav" class="w3-button w3-teal w3-xlarge" onclick="w3_open()">&#9776;</button>
  <div class="w3-container">
    <h1>My Page</h1>
  </div>
</div>

<!-- Cartes (Cards) -->
<div class="w3-container w3-margin-top">
  <div class="w3-row-padding">
    <div class="w3-third">
      <div class="w3-card">
        <header class="w3-container w3-light-grey">
          <h3>Card 1</h3>
        </header>
        <div class="w3-container">
          
        </div>
      </div>
    </div>
    <div class="w3-third">
      <div class="w3-card">
        <header class="w3-container w3-light-grey">
          <h3>Card 2</h3>
        </header>
        <div class="w3-container">
         
        </div>
      </div>
    </div>
    <div class="w3-third">
      <div class="w3-card">
        <header class="w3-container w3-light-grey">
          <h3>Card 3</h3>
        </header>
        <div class="w3-container">
          
        </div>
      </div>
    </div>
  </div>
</div>

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

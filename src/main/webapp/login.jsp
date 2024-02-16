<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Lien vers Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <!-- Lien vers Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous">
    <style>
        body {
            background-color: #f8f9fa; /* Blanc cassé */
        }
        
        .card {
            background-color: #fff; /* Fond blanc */
        }

        .card-header {
            background: none;
            border-bottom: none;
            text-align: center;
            /* Centrer le contenu du header */
        }

        /* Ajout de style pour l'image */
        .logo {
            max-height: 100px;
            /* Hauteur maximale de l'image */
            width: auto;
            /* Largeur automatique pour conserver les proportions */
            margin: 0 auto; /* Centrage horizontal */
            display: block; /* Pour que les marges fonctionnent */
        }

        /* Style pour le bouton */
        .btn-success {
            background-color: green;
            /* Couleur bleue */
            border-color: green;
            /* Bordure bleue */
        }
    </style>
</head>

<body>
    <div class="container-fluid">
        <div class="row justify-content-center align-items-center vh-100">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-header">
                        <p><b>Instutit Superieur de Comptabilite et administration des entrprises</b></p>
                        <p><b>Departement Informatique(MQI)</b></p>
                        <!-- Ajout du logo avec la classe .logo -->
                        <img src="https://iscae.mr/sites/default/files/logo-iscae.png" alt="Car" class="logo">
                    </div>
                    <div class="card-body">
                        <form action="login" method="post">
                            <div class="mb-3">
                                <input type="text" class="form-control" id="username" name="username" placeholder="Nom" required>
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required>
                            </div>
                            <div class="text-center"> <!-- Ajout de la classe text-center pour centrer le bouton -->
                                <button type="submit" class="btn btn-success btn-block">S'authentifier</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Lien vers Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>

</html>

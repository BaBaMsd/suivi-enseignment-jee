<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
   <meta charset="UTF-8">
    <title>Login</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Lien vers Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" crossorigin="anonymous">
    <!-- Lien vers Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" crossorigin="anonymous" />
    <!-- Lien vers le fichier de style principal -->
    <link rel="stylesheet" href="/Suivi-Enseigment/css/main.css">
    <!-- Script de détection des fonctionnalités du navigateur -->
    <script src="/Suivi-Enseigment/js/vendor/modernizr-2.8.3.min.js"></script>
    <!-- Lien vers le fichier de style personnalisé -->
    <link rel="stylesheet" href="/Suivi-Enseigment/style.css">
    <style>
        .card-header {
            background: none;
            border-bottom: none;
        }
        /* Ajout de style pour l'image */
        .logo {
            max-height: 100px; /* Hauteur maximale de l'image */
            width: auto; /* Largeur automatique pour conserver les proportions */
        }
    </style>
</head>

<body>
    <div class="container-fluid">
        <div class="row justify-content-center align-items-center vh-100">
            <div class="col-md-4">
                <div class="card">
                 <div class="card-header text-center">
    <!-- Ajout du logo avec la classe .logo -->
<img class="main-logo" src="/Suivi-Enseigment/img/logo-iscae-removebg-preview.png" alt="" />
</div>

                    <div class="card-body">
                        <form action="login" method="post">
                            <div class="mb-3">
                              
                                <input type="text" class="form-control" id="username" name="username" placeholder="Nom" required>
                            </div>
                            <div class="mb-3">
                                <input type="password" class="form-control" id="password" name="password" placeholder="Mot de passe" required>
                            </div>
                            <button type="submit" class="btn btn-success btn-block">S'authentifier</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- jQuery -->
    <script src="/Suivi-Enseigment/js/vendor/jquery-3.6.0.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="/Suivi-Enseigment/js/bootstrap.bundle.min.js"></script>
</body>

</html>

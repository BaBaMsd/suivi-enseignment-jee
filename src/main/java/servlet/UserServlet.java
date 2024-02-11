package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

@WebServlet("/users/*")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDao;

    public void init() {
        userDao = new UserDAOImpl(); // Initialisation du DAO Utilisateur
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo(); // Récupérer le chemin de la demande

        if (action == null) {
            // Si aucune action n'est spécifiée, afficher une erreur
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action manquante");
            return;
        }

        switch (action) {
            case "/add":
                addUser(request, response);
                break;
            default:
                // Si l'action demandée n'est pas prise en charge, afficher une erreur
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non trouvée");
                break;
        }
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Créer un nouvel utilisateur
        User newUser = new User(username, password);

        // Ajouter l'utilisateur à la base de données via le DAO
        String m = userDao.addUser(newUser);
        System.out.print(m);

        // Rediriger vers une page de confirmation ou une autre page appropriée
        response.sendRedirect(request.getContextPath() + "/users");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    String action = request.getPathInfo(); // Récupérer le chemin de la demande

    if (action == null || action.equals("/")) {
        // Si aucune action n'est spécifiée ou si c'est la racine, afficher la liste des utilisateurs
        List<User> userList = userDao.getAllUsers();
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("/listeUtilisateurs.jsp").forward(request, response);
    } else {
        // Si une action est spécifiée, afficher une erreur
        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non trouvée");
    }
}

}

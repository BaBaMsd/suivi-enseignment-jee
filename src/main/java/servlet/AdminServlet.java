package servlet;

import dao.AdminDAOImp;
import dao.MatiereDAO;
import dao.MatiereDAOImpl;
import model.Matiere;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAOImp adminDao;
    private MatiereDAO matiereDao; // Ajout du DAO des matières

    public void init() {
        adminDao = new AdminDAOImp(); // Initialisation du DAO Admin
        matiereDao = new MatiereDAOImpl(); // Initialisation du DAO des matières
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les identifiants saisis par l'administrateur depuis la requête
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Vérifier les identifiants en utilisant AdminDAOImp
        boolean isAuthenticated = adminDao.authenticate(username, password);

        if (isAuthenticated) {
        	  List<Matiere> matiereList = matiereDao.getAllMatieres();
        	  
        	  for (Matiere matiere : matiereList) {
        		  System.out.println(matiere.getNom());
        	  }

        	    // Ajouter la liste des matières comme attribut de la requête
//        	    request.setAttribute("matiereList", matiereList);

        	    // Dispatcher vers la page d'accueil (accueil.jsp)
        	  request.getRequestDispatcher("/test2.jsp").forward(request, response);//            // Si l'authentification réussit, récupérer la liste des matières
//            List<Matiere> matiereList = matiereDao.getAllMatieresWithDetails();
//            // Passer la liste des matières comme attribut de requête
//            request.setAttribute("matiereList", matiereList);
//            // Rediriger vers la page d'accueil (accueil.jsp)
//            request.getRequestDispatcher("/accueil.jsp").forward(request, response);
        } else {
            // Si l'authentification échoue, rediriger vers la page de connexion avec un message d'erreur
            request.setAttribute("errorMessage", "Identifiants incorrects. Veuillez réessayer.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}

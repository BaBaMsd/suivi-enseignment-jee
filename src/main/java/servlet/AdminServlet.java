package servlet;

import dao.AdminDAOImp;
import dao.MatiereDAO;
import dao.MatiereDAOImpl;
import dao.SemestreDAO;
import dao.SemestreDAOImpl;
import model.Matiere;
import model.Semestre;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/login")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAOImp adminDao;
    private MatiereDAO matiereDao; 
    private SemestreDAO semestreDao;// Ajout du DAO des matières

    public void init() {
        adminDao = new AdminDAOImp(); // Initialisation du DAO Admin
        matiereDao = new MatiereDAOImpl(); // Initialisation du DAO des matières
        semestreDao = new SemestreDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les identifiants saisis par l'administrateur depuis la requête
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Vérifier les identifiants en utilisant AdminDAOImp
        boolean isAuthenticated = adminDao.authenticate(username, password);

        if (isAuthenticated) {
        	// Suppose que vous avez récupéré les listes de matières et de semestres depuis la base de données
        	List<Matiere> matiereList = matiereDao.getAllMatieres();
        	List<Semestre> semestresAffiches = semestreDao.getAllSemestres();

        	request.setAttribute("matiereList", matiereList);
        	request.setAttribute("semestresAffiches", semestresAffiches);


        	// Dispatch vers la JSP
        	request.getRequestDispatcher("accueil.jsp").forward(request, response);

        } else {
            // Si l'authentification échoue, rediriger vers la page de connexion avec un message d'erreur
            request.setAttribute("errorMessage", "Identifiants incorrects. Veuillez réessayer.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}

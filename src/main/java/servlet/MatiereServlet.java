package servlet;

import dao.MatiereDAO;
import dao.MatiereDAOImpl;
import dao.SemestreDAO;
import dao.SemestreDAOImpl;
import model.Matiere;
import model.Semestre;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/matieres/*")
public class MatiereServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MatiereDAO matiereDao;
    private SemestreDAO semestreDao;
    public void init() {
        matiereDao = new MatiereDAOImpl(); // Initialisation du DAO Matiere
        semestreDao = new SemestreDAOImpl();
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
                addMatiere(request, response);
                break;
            case "/update":
                updateMatiere(request, response);
                break;
            default:
                // Si l'action demandée n'est pas prise en charge, afficher une erreur
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non trouvée");
                break;
        }
    }

    private void addMatiere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String nom = request.getParameter("nom");
        // Récupérer d'autres paramètres du formulaire si nécessaire

        // Créer une nouvelle matière
        Matiere newMatiere = new Matiere(nom, null, null, null, 0);
        // Initialiser les autres attributs de la matière si nécessaire

        // Ajouter la matière à la base de données via le DAO
        matiereDao.addMatiere(newMatiere);

        // Rediriger vers une page de confirmation ou une autre page appropriée
        response.sendRedirect(request.getContextPath() + "/matieres");
    }

    private void updateMatiere(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les paramètres de la requête pour l'ID de la matière et la nouvelle charge horaire
        String idParam = request.getParameter("id");
        String chargeParam = request.getParameter("charge");
        
        if(idParam == null || chargeParam == null || idParam.isEmpty() || chargeParam.isEmpty()) {
            // Gérer le cas où les paramètres sont manquants ou vides
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètres manquants");
            return;
        }
        
        int id = Integer.parseInt(idParam);
        int newCharge = Integer.parseInt(chargeParam);

        // Obtenir la matière à partir de la base de données
        Matiere matiere = matiereDao.getMatiereById(id);

        // Mettre à jour la charge horaire de la matière
        matiere.setChargeHorairesPlanifies(newCharge);

        // Mettre à jour la matière dans la base de données via le DAO
        matiereDao.updateMatiere(matiere);

        // Rediriger vers une page de confirmation ou une autre page appropriée
        response.sendRedirect(request.getContextPath() + "/matieres");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo(); // Récupérer le chemin de la demande

        if (action == null || action.equals("/")) {
            // Si aucune action n'est spécifiée ou si c'est la racine, afficher la liste des matières
        	List<Matiere> matiereList = matiereDao.getAllMatieres();
        	List<Semestre> semestresAffiches = semestreDao.getAllSemestres();

        	request.setAttribute("matiereList", matiereList);
        	request.setAttribute("semestresAffiches", semestresAffiches);


        	// Dispatch vers la JSP
        	request.getRequestDispatcher("matieres.jsp").forward(request, response);
        } else {
            // Si une action est spécifiée, afficher une erreur
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action non trouvée");
        }
    }
}

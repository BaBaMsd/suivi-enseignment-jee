package servlet;

import dao.AvancementDAO;
import dao.AvancementDAOImp;
import dao.MatiereDAO;
import dao.MatiereDAOImpl;
import dao.SemestreDAO;
import dao.SemestreDAOImpl;
import model.Avancement;
import model.Matiere;
import model.Semestre;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/avancement")
public class AvancementServlet extends HttpServlet {
    private AvancementDAO avancementDao;
    private SemestreDAO semestreDao;

    public void init() {
        avancementDao = new AvancementDAOImp(); // Initialisation du DAO Matiere
        semestreDao = new SemestreDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Avancement> matieresAvancement = avancementDao.getMatieresAvancement();
        List<Semestre> semestresAffiches = semestreDao.getAllSemestres();

    	
    	request.setAttribute("semestresAffiches", semestresAffiches);
		request.setAttribute("matieresAvancement", matieresAvancement);
		request.getRequestDispatcher("/avancement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Récupérer les données du formulaire
            int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
            int nouvelAvancement = Integer.parseInt(request.getParameter("avancement"));

            // Mettre à jour l'avancement de la matière dans la base de données
            String message = avancementDao.updateAvancementMatiere(idMatiere, nouvelAvancement);

            // Rediriger vers la page de résultats avec un message
            request.setAttribute("message", message);
            response.sendRedirect(request.getContextPath() + "/avancement");
        } catch (NumberFormatException e) {
            // En cas d'erreur, afficher une page d'erreur avec un message approprié
            request.setAttribute("errorMessage", "Erreur lors de la mise à jour de l'avancement : " + e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}

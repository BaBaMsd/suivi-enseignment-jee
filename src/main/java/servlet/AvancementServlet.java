package servlet;

import dao.AvancementDAO;
import dao.AvancementDAOImp;
import dao.MatiereDAO;
import dao.MatiereDAOImpl;
import model.Avancement;
import model.Matiere;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/avancement")
public class AvancementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AvancementDAO avancementDao;

    public void init() {
    	avancementDao = new AvancementDAOImp(); // Initialisation du DAO Matiere
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        AvancementDAO avancementDAO = new AvancementDAOImp();
        List<Avancement> matieresAvancement = avancementDao.getMatieresAvancement();
        
     
        request.setAttribute("matieresAvancement", matieresAvancement);
        request.getRequestDispatcher("/avancement.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les données du formulaire
        int idMatiere = Integer.parseInt(request.getParameter("idMatiere"));
        int nouvelAvancement = Integer.parseInt(request.getParameter("avancement"));

        // Mettre à jour l'avancement de la matière dans la base de données
        AvancementDAO avancementDAO = new AvancementDAOImp();
        String message = avancementDAO.updateAvancementMatiere(idMatiere, nouvelAvancement);

        // Rediriger vers la page de résultats avec un message
        request.setAttribute("message", message);
        // Utiliser la méthode doGet pour réafficher la page avec les mises à jour
        response.sendRedirect(request.getContextPath() + "/avancement");
    }
}

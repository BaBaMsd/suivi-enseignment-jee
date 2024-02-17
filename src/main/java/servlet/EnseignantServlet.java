package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Enseignant;
import dao.EnseignantDAOImp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/enseignants")
public class EnseignantServlet extends HttpServlet {
    private EnseignantDAOImp enseignantDAO;

    @Override
    public void init() throws ServletException {
        enseignantDAO = new EnseignantDAOImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Enseignant> enseignants = enseignantDAO.getAllEnseignants();
        req.setAttribute("enseignants", enseignants);
        req.getRequestDispatcher("/enseignants.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if (action != null) {
                switch (action) {
                    case "add":
                        addEnseignant(req, resp);
                        break;
                    case "edit":
                        editEnseignant(req, resp);
                        break;
                    default:
                        // Gérer les autres actions si nécessaire
                        break;
                }
            }
        } catch (SQLException e) {
            // Gérer l'erreur en redirigeant vers une page d'erreur appropriée
            req.setAttribute("error", "Erreur lors de l'opération sur les enseignants : " + e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

    private void addEnseignant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String nom = req.getParameter("nom");
        if (nom != null && !nom.isEmpty()) {
            Enseignant enseignant = new Enseignant();
            enseignant.setNom(nom);
            enseignantDAO.addEnseignant(enseignant);
        }
        resp.sendRedirect(req.getContextPath() + "/enseignants");
    }

    private void editEnseignant(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String idParam = req.getParameter("id");
        String nom = req.getParameter("nom");
        if (idParam != null && nom != null && !nom.isEmpty()) {
            int id = Integer.parseInt(idParam);
            Enseignant enseignant = new Enseignant();
            enseignant.setId(id);
            enseignant.setNom(nom);
            enseignantDAO.updateEnseignant(enseignant);
        }
        resp.sendRedirect(req.getContextPath() + "/enseignants");
    }
}

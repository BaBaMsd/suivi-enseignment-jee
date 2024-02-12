package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Enseignant;
import model.Filiere;
import model.Matiere;
import model.Semestre;

public class MatiereDAOImpl implements MatiereDAO {

	private String jdbcURL = "jdbc:mysql://localhost:3306/suividb?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
    
    protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

    // Méthode pour récupérer toutes les matières depuis la base de données
    @Override
    public List<Matiere> getAllMatieres() {
        List<Matiere> matieres = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM matiere");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int enseignantId = resultSet.getInt("enseignant_id");
                int semestreId = resultSet.getInt("semestre_id");
                int filiereId = resultSet.getInt("filiere_id");
                int chargeHorairesPlanifies = resultSet.getInt("charge_horaires_planifies");

                EnseignantDAOImp enseignantDAO = new EnseignantDAOImp();
                Enseignant enseignant = enseignantDAO.getEnseignantById(enseignantId);

                SemestreDAOImpl semestreDAO = new SemestreDAOImpl();
                Semestre semestre = semestreDAO.getSemestreById(semestreId);

                FiliereDAOImpl filiereDAO = new FiliereDAOImpl();
                Filiere filiere = filiereDAO.getFiliereById(filiereId);

                Matiere matiere = new Matiere(nom, enseignant, semestre, filiere, chargeHorairesPlanifies);
                matiere.setId(id);
                matieres.add(matiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matieres;
    }

    // Méthode pour ajouter une nouvelle matière dans la base de données
    @Override
    public String addMatiere(Matiere matiere) {
        String message = "";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO matiere (nom, enseignant_id, semestre_id, filiere_id, charge_horaires_planifies) VALUES (?, ?, ?, ?, ?)")) {

            statement.setString(1, matiere.getNom());
            statement.setInt(2, matiere.getEnseignant().getId());
            statement.setInt(3, matiere.getSemestre().getId());
            statement.setInt(4, matiere.getFiliere().getId());
            statement.setInt(5, matiere.getChargeHorairesPlanifies());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                message = "La matière a été ajoutée avec succès.";
            } else {
                message = "Erreur lors de l'ajout de la matière.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Erreur lors de l'ajout de la matière : " + e.getMessage();
        }
        return message;
    }

    // Méthode pour mettre à jour une matière dans la base de données
    @Override
    public String updateMatiere(Matiere matiere) {
        String message = "";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE matiere SET nom=?, enseignant_id=?, semestre_id=?, filiere_id=?, charge_horaires_planifies=? WHERE id=?")) {

            statement.setString(1, matiere.getNom());
            statement.setInt(2, matiere.getEnseignant().getId());
            statement.setInt(3, matiere.getSemestre().getId());
            statement.setInt(4, matiere.getFiliere().getId());
            statement.setInt(5, matiere.getChargeHorairesPlanifies());
            statement.setInt(6, matiere.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                message = "La matière a été mise à jour avec succès.";
            } else {
                message = "Aucune matière trouvée avec l'ID fourni, la mise à jour a échoué.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Erreur lors de la mise à jour de la matière : " + e.getMessage();
        }
        return message;
    }

    // Méthode pour récupérer une matière à partir de son ID
    @Override
    public Matiere getMatiereById(int id) {
        Matiere matiere = null;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM matiere WHERE id=?")) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    int enseignantId = resultSet.getInt("enseignant_id");
                    int semestreId = resultSet.getInt("semestre_id");
                    int filiereId = resultSet.getInt("filiere_id");
                    int chargeHorairesPlanifies = resultSet.getInt("charge_horaires_planifies");

                    EnseignantDAOImp enseignantDAO = new EnseignantDAOImp();
                    Enseignant enseignant = enseignantDAO.getEnseignantById(enseignantId);

                    SemestreDAOImpl semestreDAO = new SemestreDAOImpl();
                    Semestre semestre = semestreDAO.getSemestreById(semestreId);

                    FiliereDAOImpl filiereDAO = new FiliereDAOImpl();
                    Filiere filiere = filiereDAO.getFiliereById(filiereId);

                    matiere = new Matiere(nom, enseignant, semestre, filiere, chargeHorairesPlanifies);
                    matiere.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matiere;
    }

 // Méthode pour récupérer toutes les matières avec leurs informations liées
    @Override
    public List<Matiere> getAllMatieresWithDetails() {
        List<Matiere> matieres = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM matiere");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nom = resultSet.getString(2);
                
                int chargeHorairesPlanifies = resultSet.getInt(6);
                int enseignant_id = resultSet.getInt(3);
                int semestre_id = resultSet.getInt(4);
                int filiere_id = resultSet.getInt(5);

                Matiere matiere = new Matiere();
                matiere.setId(id);
                matiere.setNom(nom);
                matiere.setChargeHorairesPlanifies(chargeHorairesPlanifies);

                EnseignantDAOImp enseignantDAO = new EnseignantDAOImp();
                Enseignant enseignant = enseignantDAO.getEnseignantById(enseignant_id);

                matiere.setEnseignant(enseignant);
                
                SemestreDAOImpl semestreDAO = new SemestreDAOImpl();
                Semestre semestre = semestreDAO.getSemestreById(semestre_id);
                matiere.setSemestre(semestre);

                FiliereDAOImpl filiereDAO = new FiliereDAOImpl();
                Filiere filiere = filiereDAO.getFiliereById(filiere_id);
        
                matiere.setFiliere(filiere);

                matieres.add(matiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matieres;
    }

}

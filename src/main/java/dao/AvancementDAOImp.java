package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Avancement;
import model.Matiere;

public class AvancementDAOImp implements AvancementDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/suividb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public String updateAvancementMatiere(int idMatiere, int avancement) {
        String message = "";
        try (Connection connection = getConnection();
             PreparedStatement selectStatement = connection.prepareStatement("SELECT avancement FROM avancement_matiere WHERE id_matiere=?")) {

            selectStatement.setInt(1, idMatiere);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int avancementExistante = resultSet.getInt(1);
                    int nouvelAvancement = avancementExistante + avancement;
                    try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE avancement_matiere SET avancement=? WHERE id_matiere=?")) {
                        updateStatement.setInt(1, nouvelAvancement);
                        updateStatement.setInt(2, idMatiere);

                        int rowsUpdated = updateStatement.executeUpdate();
                        if (rowsUpdated > 0) {
                            message = "L'avancement de la matière a été mis à jour avec succès.";
                        } else {
                            message = "Erreur lors de la mise à jour de l'avancement de la matière.";
                        }
                    }
                } else {
                    try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO avancement_matiere (id_matiere, avancement) VALUES (?, ?)")) {
                        insertStatement.setInt(1, idMatiere);
                        insertStatement.setInt(2, avancement);

                        int rowsInserted = insertStatement.executeUpdate();
                        if (rowsInserted > 0) {
                            message = "L'avancement de la matière a été enregistré avec succès.";
                        } else {
                            message = "Erreur lors de l'enregistrement de l'avancement de la matière.";
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Erreur lors de la mise à jour de l'avancement de la matière : " + e.getMessage();
        }
        return message;
    }

    @Override
    public List<Avancement> getMatieresAvancement() {
        List<Avancement> matieresAvancement = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM matiere WHERE id NOT IN (SELECT id_matiere FROM avancement_matiere)")) {

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Matiere matiere = new Matiere();
                    matiere.setId(resultSet.getInt("id"));
                    matiere.setNom(resultSet.getString("nom"));

                    // Insérer les nouvelles matières dans la table d'avancement
                    try (PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO avancement_matiere (id_matiere, avancement) VALUES (?, ?)")) {
                        insertStatement.setInt(1, matiere.getId());
                        insertStatement.setInt(2, 0);
                        insertStatement.executeUpdate();
                    }

                    // Créer un objet Avancement et l'ajouter à la liste
                    Avancement avancement = new Avancement();
                    avancement.setMatiere(matiere);
                    avancement.setAvancement(0);
                    matieresAvancement.add(avancement);
                }
            }

            // Récupérer les matières avec leur avancement
            try (PreparedStatement statement2 = connection.prepareStatement("SELECT * FROM avancement_matiere");
                 ResultSet resultSet2 = statement2.executeQuery()) {

                while (resultSet2.next()) {
                    Avancement avancement = new Avancement();
                    avancement.setId(resultSet2.getInt(1));

                    // Récupérer la matière associée à l'avancement
                    MatiereDAO matiereDAO = new MatiereDAOImpl();
                    Matiere matiere = matiereDAO.getMatiereById(resultSet2.getInt(2));
                    avancement.setMatiere(matiere);

                    avancement.setAvancement(resultSet2.getInt(3));
                    matieresAvancement.add(avancement);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return matieresAvancement;
    }
}

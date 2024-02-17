package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Avancement;
import model.Matiere;

public class AvancementDAOImp implements AvancementDAO {

    @Override
    public String updateAvancementMatiere(int idMatiere, int avancement) {
        String message = "";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement("SELECT charge_horaires_planifies, avancement FROM matiere JOIN avancement_matiere ON matiere.id = avancement_matiere.id_matiere WHERE matiere.id=?")) {

            selectStatement.setInt(1, idMatiere);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int charge = resultSet.getInt("charge_horaires_planifies");
                    int avancementExistante = resultSet.getInt("avancement");
                    int nouvelAvancement = avancementExistante + avancement;
                    if (charge >= nouvelAvancement) {
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
                        message = "Erreur lors de la mise à jour de l'avancement : le nouvel avancement dépasse le volume planifié.";
                    }
                } else {
                    message = "Erreur lors de la mise à jour de l'avancement : la matière n'a pas été trouvée.";
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
        Connection connection = null;
        PreparedStatement statement = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        ResultSet resultSet2 = null;

        try {
            connection = Conexion.getConnection();

            // Fetch data from the 'matiere' table
            statement = connection.prepareStatement("SELECT * FROM matiere WHERE id NOT IN (SELECT id_matiere FROM avancement_matiere)");
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Matiere matiere = new Matiere();
                matiere.setId(resultSet.getInt("id"));
                matiere.setNom(resultSet.getString("nom"));

                Avancement avancement = new Avancement();
                avancement.setMatiere(matiere);
                avancement.setAvancement(0);
                matieresAvancement.add(avancement);
            }

            // Fetch data from the 'avancement_matiere' table
            statement2 = connection.prepareStatement("SELECT * FROM avancement_matiere");
            resultSet2 = statement2.executeQuery();

            while (resultSet2.next()) {
                Avancement avancement = new Avancement();
                avancement.setId(resultSet2.getInt(1));

                MatiereDAO matiereDAO = new MatiereDAOImpl();
                Matiere matiere = matiereDAO.getMatiereById(resultSet2.getInt(2));
                if (matiere != null) {
                    avancement.setMatiere(matiere);
                    avancement.setAvancement(resultSet2.getInt(3));
                    matieresAvancement.add(avancement);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // ou logguer l'erreur
            // Gérer l'erreur de manière appropriée, par exemple, en lançant une nouvelle exception
            throw new RuntimeException("Erreur lors de la récupération des données d'avancement des matières.", e);
        } finally {
            // Close resources in reverse order of their creation
            try {
                if (resultSet2 != null) resultSet2.close();
                if (statement2 != null) statement2.close();
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace(); // ou logguer l'erreur
            }
        }

        return matieresAvancement;
    }

}

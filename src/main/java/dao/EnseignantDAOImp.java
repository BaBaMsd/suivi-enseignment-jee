package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Enseignant;

public class EnseignantDAOImp implements EnseignantDAO {

	@Override
    public List<Enseignant> getAllEnseignants() {
        List<Enseignant> enseignants = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Obtention de la connexion à la base de données
            connection = Conexion.getConnection();
            // Préparation de la requête SQL
            statement = connection.prepareStatement("SELECT * FROM enseignant");
            // Exécution de la requête et récupération du résultat
            resultSet = statement.executeQuery();

            // Parcours du résultat et création des objets Enseignant
            while (resultSet.next()) {
                Enseignant enseignant = new Enseignant();
                enseignant.setId(resultSet.getInt("id"));
                enseignant.setNom(resultSet.getString("nom"));
                enseignant.setType(resultSet.getString("type"));
                enseignants.add(enseignant);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        } finally {
            // Fermeture des ressources dans un bloc finally pour s'assurer qu'elles sont fermées même en cas d'exception
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return enseignants;
    }


    @Override
    public Enseignant getEnseignantById(int id) {
        Enseignant enseignant = null;
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id, nom, type FROM enseignant WHERE id=?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    enseignant = new Enseignant();
                    enseignant.setId(resultSet.getInt("id"));
                    enseignant.setNom(resultSet.getString("nom"));
                    enseignant.setType(resultSet.getString("type"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
        return enseignant;
    }

    @Override
    public String addEnseignant(Enseignant enseignant) {
        String message = "";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO enseignant (nom, type) VALUES (?,?)")) {
            statement.setString(1, enseignant.getNom());
            statement.setString(2, enseignant.getType());
            statement.executeUpdate();
            message = "L'enseignant a été ajouté avec succès.";
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Erreur lors de l'ajout de l'enseignant : " + e.getMessage();
        }
        return message;
    }

    @Override
    public String updateEnseignant(Enseignant enseignant) {
        String message = "";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE enseignant SET nom=? WHERE id=?")) {
            statement.setString(1, enseignant.getNom());
            statement.setInt(2, enseignant.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                message = "L'enseignant a été mis à jour avec succès.";
            } else {
                message = "Aucun enseignant trouvé avec l'ID fourni, la mise à jour a échoué.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Erreur lors de la mise à jour de l'enseignant : " + e.getMessage();
        }
        return message;
    }
}

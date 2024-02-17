package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Niveau;

public class NiveauDAOImp {

    public List<Niveau> getAllNiveaus() {
        List<Niveau> niveaus = new ArrayList<>();
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id, niveau FROM Niveau");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Niveau niveau = new Niveau();
                niveau.setId(resultSet.getInt("id"));
                niveau.setNiveau(resultSet.getString("niveau"));
                niveaus.add(niveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
        return niveaus;
    }

    public Niveau getNiveauById(int id) {
        Niveau niveau = null;
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id, niveau FROM Niveau WHERE id=?")) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    niveau = new Niveau();
                    niveau.setId(resultSet.getInt("id"));
                    niveau.setNiveau(resultSet.getString("niveau"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
        return niveau;
    }

    public void addNiveau(Niveau niveau) {
        String query = "INSERT INTO niveau (niveau) VALUES (?)";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, niveau.getNiveau());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
    }

    public void updateNiveau(Niveau niveau) {
        String query = "UPDATE niveau SET niveau=? WHERE id=?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, niveau.getNiveau());
            statement.setInt(2, niveau.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
    }

    public void deleteNiveau(int id) {
        String query = "DELETE FROM Niveau WHERE id=?";
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
    }
}

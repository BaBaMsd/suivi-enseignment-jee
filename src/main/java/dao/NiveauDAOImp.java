package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Niveau;

public class NiveauDAOImp {
    private Connection connection;

    public NiveauDAOImp(Connection connection) {
        this.connection = connection;
    }

    public List<Niveau> getAllNiveaus() throws SQLException {
        List<Niveau> Niveaus = new ArrayList<>();
        String query = "SELECT id, niveau FROM Niveau";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Niveau Niveau = new Niveau();
                Niveau.setId(resultSet.getInt("id"));
                Niveau.setNiveau(resultSet.getString("niveau"));
                Niveaus.add(Niveau);
            }
        }
        return Niveaus;
    }

    public Niveau getNiveauById(int id) throws SQLException {
        String query = "SELECT id, niveau FROM Niveau WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Niveau Niveau = new Niveau();
                    Niveau.setId(resultSet.getInt("id"));
                    Niveau.setNiveau(resultSet.getString("niveau"));
                    return Niveau;
                }
            }
        }
        return null;
    }

    public void addNiveau(Niveau Niveau) throws SQLException {
        String query = "INSERT INTO niveau (niveau) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Niveau.getNiveau());
            statement.executeUpdate();
        }
    }

    public void updateNiveau(Niveau Niveau) throws SQLException {
        String query = "UPDATE niveau SET niveau=? WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Niveau.getNiveau());
            statement.setInt(3, Niveau.getId());
            statement.executeUpdate();
        }
    }

    public void deleteNiveau(int id) throws SQLException {
        String query = "DELETE FROM Niveau WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}


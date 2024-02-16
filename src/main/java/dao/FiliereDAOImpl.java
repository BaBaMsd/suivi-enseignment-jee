package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Filiere;
import model.Niveau;

public class FiliereDAOImpl implements FiliereDAO {

    @Override
    public List<Filiere> getAllFilieres() {
        List<Filiere> filieres = new ArrayList<>();

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM filiere");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int niveauId = resultSet.getInt("niveau_id");
                NiveauDAOImp nvIm = new NiveauDAOImp();
                Niveau nv = nvIm.getNiveauById(niveauId);

                Filiere filiere = new Filiere(nom, nv);
                filiere.setId(id);
                filieres.add(filiere);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filieres;
    }

    @Override
    public void addFiliere(Filiere filiere) {
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO filiere (nom, niveau_id) VALUES (?, ?)")) {

            statement.setString(1, filiere.getNom());
            statement.setInt(2, filiere.getNiveau().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFiliere(Filiere filiere) {
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE filiere SET nom=?, niveau_id=? WHERE id=?")) {

            statement.setString(1, filiere.getNom());
            statement.setInt(2, filiere.getNiveau().getId());
            statement.setInt(3, filiere.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Filiere getFiliereById(int id) {
        Filiere filiere = null;

        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM filiere WHERE id=?")) {

            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    int niveauId = resultSet.getInt("niveau_id");
                    NiveauDAOImp nvIm = new NiveauDAOImp();
                    Niveau nv = nvIm.getNiveauById(niveauId);
                    filiere = new Filiere(nom, nv);
                    filiere.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filiere;
    }
}

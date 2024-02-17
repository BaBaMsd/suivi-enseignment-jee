package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Niveau;
import model.Semestre;

public class SemestreDAOImpl implements SemestreDAO {

    @Override
    public void addSemestre(Semestre semestre) {
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO semestre (semestre, niveau_id) VALUES (?, ?)")) {
            statement.setString(1, semestre.getSemestre());
            statement.setInt(2, semestre.getNiveau().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
    }

    @Override
    public void updateSemestre(Semestre semestre) {
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE semestre SET semestre = ?, niveau_id = ? WHERE id = ?")) {
            statement.setString(1, semestre.getSemestre());
            statement.setInt(2, semestre.getNiveau().getId());
            statement.setInt(3, semestre.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
    }

    @Override
    public Semestre getSemestreById(int id) {
        Semestre semestre = null;
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM semestre WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String semestreNom = resultSet.getString("semestre");
                    int niveauId = resultSet.getInt("niveau_id");
                    NiveauDAOImp niveauDAO = new NiveauDAOImp();
                    Niveau niveau = niveauDAO.getNiveauById(niveauId);
                    semestre = new Semestre(semestreNom, niveau);
                    semestre.setId(id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
        return semestre;
    }

    @Override
    public List<Semestre> getAllSemestres() {
        List<Semestre> semestres = new ArrayList<>();
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM semestre");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String semestreNom = resultSet.getString("semestre");
                int niveauId = resultSet.getInt("niveau_id");
                NiveauDAOImp niveauDAO = new NiveauDAOImp();
                Niveau niveau = niveauDAO.getNiveauById(niveauId);
                Semestre semestre = new Semestre(semestreNom, niveau);
                semestre.setId(id);
                semestres.add(semestre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Gérer l'exception de manière appropriée
        }
        return semestres;
    }
}

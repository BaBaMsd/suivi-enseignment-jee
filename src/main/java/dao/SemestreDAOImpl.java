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
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = Conexion.getConnection();
            String query = "INSERT INTO semestre (semestre, niveau_id) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, semestre.getSemestre());
            statement.setInt(2, semestre.getNiveau().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(connection, statement);
        }
    }

    @Override
    public void updateSemestre(Semestre semestre) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = Conexion.getConnection();
            String query = "UPDATE semestre SET semestre = ?, niveau_id = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, semestre.getSemestre());
            statement.setInt(2, semestre.getNiveau().getId());
            statement.setInt(3, semestre.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(connection, statement);
        }
    }

    @Override
    public Semestre getSemestreById(int id) {
        Semestre semestre = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.getConnection();
            String query = "SELECT * FROM semestre WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String semestreNom = resultSet.getString("semestre");
                int niveauId = resultSet.getInt("niveau_id");

                // Récupérer le niveau associé à partir de son ID
                NiveauDAOImp niveauDAO = new NiveauDAOImp();
                Niveau niveau = niveauDAO.getNiveauById(niveauId);

                semestre = new Semestre(semestreNom, niveau);
                semestre.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(connection, statement, resultSet);
        }
        return semestre;
    }

    @Override
    public List<Semestre> getAllSemestres() {
        List<Semestre> semestres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Conexion.getConnection();
            String query = "SELECT * FROM semestre";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String semestreNom = resultSet.getString("semestre");
                int niveauId = resultSet.getInt("niveau_id");

                // Récupérer le niveau associé à partir de son ID
                NiveauDAOImp niveauDAO = new NiveauDAOImp();
                Niveau niveau = niveauDAO.getNiveauById(niveauId);

                Semestre semestre = new Semestre(semestreNom, niveau);
                semestre.setId(id);
                semestres.add(semestre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Conexion.close(connection, statement, resultSet);
        }
        return semestres;
    }
}

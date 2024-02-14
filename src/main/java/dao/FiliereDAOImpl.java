package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Filiere;
import model.Niveau;

public class FiliereDAOImpl implements FiliereDAO {

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

    @Override
    public List<Filiere> getAllFilieres() {
        List<Filiere> filieres = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM filiere";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                int niveauId = resultSet.getInt("niveau_id");
                NiveauDAOImp nvIm = new NiveauDAOImp(connection);
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
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO filiere (nom, niveau_id) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, filiere.getNom());
            statement.setInt(2, filiere.getNiveau().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    @Override
    public void updateFiliere(Filiere filiere) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            String query = "UPDATE filiere SET nom=?, niveau_id=? WHERE id=?";
            statement = connection.prepareStatement(query);
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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM filiere WHERE id=?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nom = resultSet.getString("nom");
                int niveauId = resultSet.getInt("niveau_id");
                NiveauDAOImp nvIm = new NiveauDAOImp(connection);
                Niveau nv = nvIm.getNiveauById(niveauId);
                filiere = new Filiere(nom, nv);
                filiere.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return filiere;
    }
}

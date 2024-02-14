package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Niveau;
import model.Semestre;

public class SemestreDAOImpl implements SemestreDAO {
	
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
    public void addSemestre(Semestre semestre) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO semestre (semestre, niveau_id) VALUES (?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, semestre.getSemestre());
            statement.setInt(2, semestre.getNiveau().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSemestre(Semestre semestre) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            String query = "UPDATE semestre SET semestre = ?, niveau_id = ? WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, semestre.getSemestre());
            statement.setInt(2, semestre.getNiveau().getId());
            statement.setInt(3, semestre.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Semestre getSemestreById(int id) {
        Semestre semestre = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM semestre WHERE id = ?";
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String semestreNom = resultSet.getString("semestre");
                int niveauId = resultSet.getInt("niveau_id");

                // Récupérer le niveau associé à partir de son ID
                NiveauDAOImp niveauDAO = new NiveauDAOImp(connection);
                Niveau niveau = niveauDAO.getNiveauById(niveauId);

                semestre = new Semestre(semestreNom, niveau);
                semestre.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
            connection = getConnection();
            String query = "SELECT * FROM semestre";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String semestreNom = resultSet.getString("semestre");
                int niveauId = resultSet.getInt("niveau_id");

                // Récupérer le niveau associé à partir de son ID
                NiveauDAOImp niveauDAO = new NiveauDAOImp(connection);
                Niveau niveau = niveauDAO.getNiveauById(niveauId);

                Semestre semestre = new Semestre(semestreNom, niveau);
                semestre.setId(id);
                semestres.add(semestre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return semestres;
    }
}

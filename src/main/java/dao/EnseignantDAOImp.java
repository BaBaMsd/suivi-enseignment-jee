package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Enseignant;

public class EnseignantDAOImp implements EnseignantDAO{
	
//	private String jdbcURL = "jdbc:mysql://localhost:3306/suividb?useSSL=false";
//	private String jdbcUsername = "root";
//	private String jdbcPassword = "";
	
	public EnseignantDAOImp() {
		// TODO Auto-generated constructor stub
	}



//	protected Connection getConnection() {
//		Connection connection = null;
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return connection;
//	}
//	
	

    public List<Enseignant> getAllEnseignants() throws SQLException {
    	Connection connection = Conexion.getConnection();
        List<Enseignant> enseignants = new ArrayList<>();
        String query = "SELECT * FROM enseignant";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Enseignant enseignant = new Enseignant();
                enseignant.setId(resultSet.getInt("id"));
                enseignant.setNom(resultSet.getString("nom"));
                enseignant.setType(resultSet.getString("type"));
                enseignants.add(enseignant);
            }
        }
        return enseignants;
    }

    public Enseignant getEnseignantById(int id) throws SQLException {
    	Connection connection = Conexion.getConnection();
        String query = "SELECT id, nom, type FROM enseignant WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Enseignant enseignant = new Enseignant();
                    enseignant.setId(resultSet.getInt("id"));
                    enseignant.setNom(resultSet.getString("nom"));
                    enseignant.setType(resultSet.getString("type"));
                    return enseignant;
                }
            }
        }
        return null;
    }

    public String addEnseignant(Enseignant enseignant){
    	String message = "";
    	Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // Obtenir une connexion à la base de données
            connection = Conexion.getConnection();
            String query = "INSERT INTO enseignant (nom, type) VALUES (?,?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, enseignant.getNom());
            statement.setString(2, enseignant.getType());
            statement.executeUpdate();
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                message = "La matière a été ajoutée avec succès.";
            } else {
                message = "Erreur lors de l'ajout de la matière.";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Erreur lors de l'ajout de la matière : " + e.getMessage();
        }
        return message;
    }

    public String updateEnseignant(Enseignant enseignant){
    	String message = "";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Obtenir une connexion à la base de données
	        connection = Conexion.getConnection();
	        String query = "UPDATE enseignant SET nom=? WHERE id=?";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, enseignant.getNom());
	        statement.setInt(2, enseignant.getId());
	        statement.setString(3, enseignant.getType());
	        
	        int rowsUpdated = statement.executeUpdate();
	        if (rowsUpdated > 0) {
	            message = "L'enseignant a été mise à jour avec succès.";
	        } else {
	            message = "Aucune enseignant trouvée avec l'ID fourni, la mise à jour a échoué.";
	        }
        } catch (SQLException e) {
            e.printStackTrace();
            message = "Erreur lors de la mise à jour de la matière : " + e.getMessage();
        }
        return message;
    }

//    public void deleteEnseignant(int id){
//        String query = "DELETE FROM enseignant WHERE id=?";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            statement.setInt(1, id);
//            statement.executeUpdate();
//        }
//    }
}

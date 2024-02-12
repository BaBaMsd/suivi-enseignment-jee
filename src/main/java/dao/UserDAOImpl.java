package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.User;

public class UserDAOImpl implements UserDAO {
	
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

	private static final String INSERT_Admin_SQL = "INSERT INTO admin" + "  (username, password) VALUES (?, ?);";
	// Mettez à jour la requête SQL pour sélectionner également l'ID de l'utilisateur
	private static final String SELECT_ALL_Admin = "SELECT id, username, password FROM admin";

   

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_Admin);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                users.add(new User(username, password));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public String addUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_Admin_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            return "Utilisateur ajouté avec succès";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erreur lors de l'ajout de l'utilisateur : " + e.getMessage();
        }
    }
}


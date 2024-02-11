package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAOImpl implements UserDAO {
	
	
	private static final String INSERT_Admin_SQL = "INSERT INTO admin" + "  (username, password) VALUES (?, ?);";
    private static final String SELECT_ALL_Admin = "SELECT * FROM admin";


   

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Conexion.getConnection();
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
        try (Connection connection = Conexion.getConnection();
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


package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImp implements AdminDAO {

    @Override
    public boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;

        // Utilisation de blocs try-with-resources pour gérer automatiquement la fermeture des ressources JDBC
        try (Connection connection = Conexion.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM admin WHERE username = ? AND password = ?")) {

            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                // Si l'administrateur est trouvé dans la base de données, isAuthenticated est vrai
                if (resultSet.next()) {
                    isAuthenticated = true;
                }
            }
        } catch (SQLException e) {
            // Gérer l'exception de manière appropriée, par exemple, en la journalisant ou en la relançant
            e.printStackTrace();
            // Vous pouvez également jeter une nouvelle exception personnalisée pour indiquer un problème d'authentification
            // throw new AuthenticationException("Erreur lors de l'authentification de l'administrateur", e);
        }
        return isAuthenticated;
    }
}

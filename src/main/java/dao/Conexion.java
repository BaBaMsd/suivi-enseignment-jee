package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/suividb?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection con = null;

    static {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private Conexion() {
        // Private constructor to prevent instantiation
    }

    // Méthode pour obtenir une connexion à la base de données
    public static Connection getConnection() {
        return con;
    }

    // Méthode pour fermer la connexion
    public static void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour fermer la connexion et les ressources associées
    public static void close(Connection connection, java.sql.Statement statement) {
        close(connection);
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour fermer la connexion, le statement et le resultSet
    public static void close(Connection connection, java.sql.Statement statement, java.sql.ResultSet resultSet) {
        close(connection, statement);
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

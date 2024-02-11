package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	 private static final String URL = "jdbc:mysql://localhost:3306/suividb";
	 private static final String USERNAME = "root";
	 private static final String PASSWORD = "";
	 static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	    // Méthode pour obtenir une connexion à la base de données
	 public static Connection getConnection() throws SQLException {
	     try {
	            // Chargement du pilote JDBC
	            Class.forName(DRIVER);
	            // Établissement de la connexion à la base de données
	            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	     } catch (ClassNotFoundException e) {
	         e.printStackTrace();
	         throw new SQLException("JDBC Driver not found", e);
	     }
	    }

}

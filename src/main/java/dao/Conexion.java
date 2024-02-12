package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
	
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String URL = "jdbc:mysql://localhost:3306/suividb";
	static final String USER = "root";
	static final String PASSWORD = "";
	static Statement stm=null;
	static Connection con=null;
	
	Conexion(){
		try{
			Class.forName(DRIVER);
			
			con=DriverManager.getConnection(URL, USER, PASSWORD);
			
		}catch(ClassNotFoundException | SQLException e){
			
			System.out.println(e);
		}
	}

	    // Méthode pour obtenir une connexion à la base de données
	public static Connection getConnection() {
		
		 if(con==null) {
			new Conexion();
		 }
		 return con;
	}	

	    // Méthode pour fermer la connexion, la déclaration et le résultat
	    public static void closeConnection(Connection connection, Statement statement) {
	        try {
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}


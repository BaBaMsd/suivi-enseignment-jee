package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Niveau;

public class NiveauDAOImp {

//	private String jdbcURL = "jdbc:mysql://localhost:3306/suividb?useSSL=false";
//	private String jdbcUsername = "root";
//	private String jdbcPassword = "";
//    
//    protected Connection getConnection() {
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

 

	public List<Niveau> getAllNiveaus() throws SQLException {
        List<Niveau> Niveaus = new ArrayList<>();
        
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
        	connection = Conexion.getConnection();
        	String query = "SELECT id, niveau FROM Niveau";
        	statement = connection.prepareStatement(query);
        	resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Niveau Niveau = new Niveau();
                Niveau.setId(resultSet.getInt("id"));
                Niveau.setNiveau(resultSet.getString("niveau"));
                Niveaus.add(Niveau);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//        finally {
//            // Fermez les ressources dans un bloc finally pour vous assurer qu'elles sont toujours fermées, même en cas d'exception.
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
        return Niveaus;
    }

    public Niveau getNiveauById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
//        ResultSet resultSet = null;
        
        try {
        	connection = Conexion.getConnection();
        	String query = "SELECT id, niveau FROM Niveau WHERE id=?";
        	statement = connection.prepareStatement(query);
        	statement.setInt(1, id);
        	
        	try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Niveau Niveau = new Niveau();
                    Niveau.setId(resultSet.getInt("id"));
                    Niveau.setNiveau(resultSet.getString("niveau"));
                    return Niveau;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
//        finally {
//            // Fermez les ressources dans un bloc finally pour vous assurer qu'elles sont toujours fermées, même en cas d'exception.
//            try {
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        
        return null;
    }

    public void addNiveau(Niveau Niveau) throws SQLException {
        String query = "INSERT INTO niveau (niveau) VALUES (?)";
        try (Connection connection = Conexion.getConnection();
        		PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, Niveau.getNiveau());
            statement.executeUpdate();
        }
    }

    public void updateNiveau(Niveau Niveau) throws SQLException {
       
        Connection connection = null;
        PreparedStatement statement = null;
        try {
        	connection = Conexion.getConnection();
        	String query = "UPDATE niveau SET niveau=? WHERE id=?";
        	statement = connection.prepareStatement(query);
            statement.setString(1, Niveau.getNiveau());
            statement.setInt(3, Niveau.getId());
            statement.executeUpdate();
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
        }
//        finally {
//            // Fermez les ressources dans un bloc finally pour vous assurer qu'elles sont toujours fermées, même en cas d'exception.
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }

    public void deleteNiveau(int id) throws SQLException {
        
        Connection connection = null;
        PreparedStatement statement = null;
   
        try {
        	connection = Conexion.getConnection();
        	String query = "DELETE FROM Niveau WHERE id=?";
        	statement = connection.prepareStatement(query);
        	statement.setInt(1, id);
            statement.executeUpdate();
        }catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
        }
//        finally {
//            // Fermez les ressources dans un bloc finally pour vous assurer qu'elles sont toujours fermées, même en cas d'exception.
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (statement != null) {
//                    statement.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
    }
}


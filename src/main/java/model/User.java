package model;

public class User {
    private int id;
    private String username;
    private String password;
    // Ajoutez d'autres propriétés et les méthodes getters/setters nécessaires
    
	public int getId() {
		return id;
	}
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
	
    
    
}
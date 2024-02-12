package model;

public class Filiere {
    private int id;
    private String nom;
    private Niveau niveau;
    
	public Filiere(String nom, Niveau niveau) {
		this.nom = nom;
		this.niveau = niveau;
	}

	

	public Filiere() {

	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	
	

    // Constructeur, getters et setters
    
}

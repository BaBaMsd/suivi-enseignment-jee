package model;

public class Semestre {
    private int id;
    private String semestre;
    private Niveau niveau;
    
	public Semestre(String semestre, Niveau niveau) {
		super();
		this.semestre = semestre;
		this.niveau = niveau;
	}

	public Semestre() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSemestre() {
		return semestre;
	}

	public void setSemestre(String semestre) {
		this.semestre = semestre;
	}

	public Niveau getNiveau() {
		return niveau;
	}

	public void setNiveau(Niveau niveau) {
		this.niveau = niveau;
	}
	
	
    
    

    // Constructeur, getters et setters
}

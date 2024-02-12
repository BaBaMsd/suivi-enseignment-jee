package model;

public class Matiere {
    private int id;
    private String nom;
    private Enseignant enseignant;
    private Semestre semestre;
    private Filiere filiere;
    private int chargeHorairesPlanifies;
    
    
	public Matiere() {
		
	}
	public Matiere(String nom, Enseignant enseignant, Semestre semestre, Filiere filiere, int chargeHorairesPlanifies) {
		super();
		this.nom = nom;
		this.enseignant = enseignant;
		this.semestre = semestre;
		this.filiere = filiere;
		this.chargeHorairesPlanifies = chargeHorairesPlanifies;
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
	public Enseignant getEnseignant() {
		return enseignant;
	}
	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}
	public Semestre getSemestre() {
		return semestre;
	}
	public void setSemestre(Semestre semestre) {
		this.semestre = semestre;
	}
	public Filiere getFiliere() {
		return filiere;
	}
	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	public int getChargeHorairesPlanifies() {
		return chargeHorairesPlanifies;
	}
	public void setChargeHorairesPlanifies(int chargeHorairesPlanifies) {
		this.chargeHorairesPlanifies = chargeHorairesPlanifies;
	}
    
    

    // Constructeur, getters et setters
}
package model;

public class Avancement {
    private int id;
    private Matiere matiere;
    private int avancement;
    
    
	public Avancement() {
	
	}
	public Avancement(Matiere matiere, int avancement) {
		super();
		this.matiere = matiere;
		this.avancement = avancement;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public int getAvancement() {
		return avancement;
	}
	public void setAvancement(int avancement) {
		this.avancement = avancement;
	}
    
}


package dao;


import java.util.List;
import model.Matiere;

public interface MatiereDAO {
    // Méthodes CRUD
    public List<Matiere> getAllMatieres();
    public String addMatiere(Matiere matiere);
    public String updateMatiere(Matiere matiere);
    public Matiere getMatiereById(int id);
    public List<Matiere> getAllMatieresWithDetails();
}


package dao;


import java.util.List;
import model.Semestre;

public interface SemestreDAO {
    void addSemestre(Semestre semestre);
    void updateSemestre(Semestre semestre);
    Semestre getSemestreById(int id);
    List<Semestre> getAllSemestres();
    // Ajoutez d'autres méthodes CRUD si nécessaire
}

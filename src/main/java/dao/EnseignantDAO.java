package dao;

import java.sql.SQLException;
import java.util.List;
import model.Enseignant;

public interface EnseignantDAO {

    List<Enseignant> getAllEnseignants() throws SQLException;

    Enseignant getEnseignantById(int id) throws SQLException;

    String addEnseignant(Enseignant enseignant);

    String updateEnseignant(Enseignant enseignant);
}


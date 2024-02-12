package dao;


import java.util.List;
import model.Filiere;

public interface FiliereDAO {
    List<Filiere> getAllFilieres();
    void addFiliere(Filiere filiere);
    void updateFiliere(Filiere filiere);
    Filiere getFiliereById(int id);
}


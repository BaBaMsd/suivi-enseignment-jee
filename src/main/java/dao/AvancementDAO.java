package dao;
import java.util.List;

import model.Avancement;
public interface AvancementDAO {
    String updateAvancementMatiere(int idMatiere, int avancement);
    public List<Avancement> getMatieresAvancement();
}


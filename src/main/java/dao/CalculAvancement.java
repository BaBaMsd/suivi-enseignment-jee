package dao;

import java.util.List;

import model.Avancement;

public class CalculAvancement {

    public static double calculerAvancementSemestre(long idSemestre, List<Avancement> matieresAvancement) {
        double avancementTotal = 0.0;
        for (Avancement avancement : matieresAvancement) {
            if (avancement.getMatiere().getSemestre().getId() == idSemestre) {
                avancementTotal += avancement.getAvancement();
            }
        }
        return avancementTotal;
    }

    public static double calculerChargeInitialeSemestre(long idSemestre, List<Avancement> matieresAvancement) {
        double chargeInitialeTotal = 0.0;
        for (Avancement avancement : matieresAvancement) {
            if (avancement.getMatiere().getSemestre().getId() == idSemestre) {
                chargeInitialeTotal += avancement.getMatiere().getChargeHorairesPlanifies();
            }
        }
        return chargeInitialeTotal;
    }
}


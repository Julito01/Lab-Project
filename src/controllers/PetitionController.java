package controllers;

import classes.Petition;
import dtos.PetitionDTO;
import dtos.PracticeDTO;

import java.time.LocalDate;
import java.util.List;

public class PetitionController {
    private static PetitionController pcObject;
    private PetitionController() {}

    public static PetitionController getInstance() {
        if (pcObject == null) {
            pcObject = new PetitionController();
        }
        return pcObject;
    }
    public void setPetition(String medInsurance, LocalDate loadDate, List<PracticeDTO> practices) {
        PetitionDTO petitionDTO = new PetitionDTO(medInsurance, loadDate, practices);
    }
}

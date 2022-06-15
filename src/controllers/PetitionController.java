package controllers;

import classes.Petition;
import dtos.PetitionDTO;
import dtos.PracticeDTO;

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

    public static void createPatientPetition(String patientId, int petitionId, String practiceName, List<PracticeDTO> practices) {
        for (int i = 0; i < practices.size(); i++) {
            Petition.createPatientPetition(patientId, petitionId, practiceName);
        }
    }

    public void setPetition(PetitionDTO petitionDTO) {
        Petition petition = new Petition(petitionDTO);
    }

    public void deletePetition(PetitionDTO petitionDTO) {
        Petition.deletePetition();
    }
}

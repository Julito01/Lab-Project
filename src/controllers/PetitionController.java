package controllers;

import classes.Petition;

public class PetitionController {
    private static PetitionController pcObject;
    private PetitionController() {}

    public static PetitionController getInstance() {
        if (pcObject == null) {
            pcObject = new PetitionController();
        }
        return pcObject;
    }
    public void setPetition(String patientId, int petitionId, String practiceName) {
            Petition.createPatientPetition(patientId, petitionId, practiceName);
    }
}

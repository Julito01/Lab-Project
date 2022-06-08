package controllers;

import classes.Patient;
import classes.Petition;

public class PetitionController {
    public static void setPetition(String patientId, int petitionId, String practiceName) {
            Petition.createPatientPetition(patientId, petitionId, practiceName);
    }
}

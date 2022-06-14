package controllers;

import classes.Petition;
import dtos.PetitionDTO;

public class PetitionController {
    private static PetitionController pcObject;
    private PetitionController() {}

    public static PetitionController getInstance() {
        if (pcObject == null) {
            pcObject = new PetitionController();
        }
        return pcObject;
    }

    public void setPetition(PetitionDTO petitionDTO) {
        Petition petition = new Petition(petitionDTO);
    }

    public void deletePetition(PetitionDTO petitionDTO) {
        Petition.deletePetition();
    }
}

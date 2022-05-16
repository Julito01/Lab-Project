package classes;

import java.util.*;

public class Petition {
    private int petitionId;
    private String medInsurance;
    private Date loadDate;
    private Date deliverDate;
    private ArrayList practices;
    private boolean petitionCompleted;

    public Petition(int petitionId, String medInsurance, Date loadDate, Date deliverDate, ArrayList practices, boolean petitionCompleted) {
        this.petitionId = petitionId;
        this.medInsurance = medInsurance;
        this.loadDate = loadDate;
        this.deliverDate = deliverDate;
        this.practices = practices;
        this.petitionCompleted = petitionCompleted;
    }

    public static void main(String[] args) {
        // TO DO something
    }

    private void createPetition() {
        // Creates a new petition
    }

    private void deletePetition() {
        // Deletes a desired petition through the petition id
    }

    private void editPetition() {
        // Modifies the data of a petition through the petition id
    }


}

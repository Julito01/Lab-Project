package classes;

import config.Database;
import dtos.*;

import java.time.LocalDate;
import java.util.*;

public class Petition {
    private int petitionId;
    private static String medInsurance;
    private static LocalDate loadDate;
    private static LocalDate deliverDate;
    private static List<PracticeDTO> practices;
    private boolean petitionCompleted;
    private static int counter = 1;

    public Petition(PetitionDTO petition) {
        this.petitionId = this.counter;
        this.medInsurance = petition.getMedInsurance();
        this.loadDate = petition.getLoadDate();
        this.practices = petition.getPractices();
        this.deliverDate = getDeliveryDate(this.loadDate, this.practices);
        this.petitionCompleted = false;
        createPetition(this);
        this.counter++;
    }

    private void createPetition(Petition petition) {
        Database.createPetition(petition);
    }

    public static void createPatientPetition(String patientId, int petitionId, String practiceName) {
        for (int i = 0; i < practices.size(); i++) {
            int practId = Practice.getCurrPracticeId(practiceName);
            Database.createPatientPetition(patientId, petitionId, practId);
        }
    }

    public static void deletePetition() {
        // Deletes a desired petition through the petition id
//        Database.deletePetition();
    }

    private void editPetition() {
        // Modifies the data of a petition through the petition id
    }

    public static int getPetitionId() {
        return counter;
    }

    public static String getMedInsurance() {
        return medInsurance;
    }

    public static String getLoadDate() {
        return loadDate.toString();
    }

    public static String getEtaDate() {
        return deliverDate.toString();
    }

    private static LocalDate getDeliveryDate(LocalDate date, List<PracticeDTO> practicesArray) {
        for (int i = 0; i < practicesArray.size(); i++) {
            date.plusDays(2);
        }
        return date;
    }

}

package dtos;

import classes.Petition;
import classes.Practice;
import config.Database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PetitionDTO {
    private int petitionId;
    private static String medInsurance;
    private static LocalDate loadDate;
    private static LocalDate deliverDate;
    private static List<PracticeDTO> practices;
    private boolean petitionCompleted;
    private static int counter = 1;
    private PetitionDTO self;

    public PetitionDTO(String medInsurance, LocalDate loadDate, List<PracticeDTO> practices) {
        this.petitionId = this.counter;
        this.medInsurance = medInsurance;
        this.loadDate = loadDate;
        this.practices = practices;
        this.deliverDate = getDeliveryDate(this.loadDate, this.practices);
        this.petitionCompleted = false;
        createPetition(this);
        this.counter++;
    }

    private void createPetition(PetitionDTO petitionDTO) {
        String medInsurance = petitionDTO.getMedInsurance();
        LocalDate loadDate = petitionDTO.getLoadDate();
        List<PracticeDTO> practices = petitionDTO.getPractices();

        Petition petition = new Petition(medInsurance, loadDate, practices);
    }

    public static void createPatientPetition(String patientId, int petitionId, String practiceName) {
        for (int i = 0; i < practices.size(); i++) {
            int practId = Practice.getCurrPracticeId(practiceName);
            Database.createPatientPetition(patientId, petitionId, practId);
        }
    }

    private void deletePetition() {
        // Deletes a desired petition through the petition id
    }

    private void editPetition() {
        // Modifies the data of a petition through the petition id
    }

    public static int getPetitionId() {
        return counter;
    }

    private static List<PracticeDTO> getPractices() {
        return practices;
    }

    public static String getMedInsurance() {
        return medInsurance;
    }

    public static LocalDate getLoadDate() {
        return loadDate;
    }

    public static LocalDate getEtaDate() {
        return deliverDate;
    }

    private static LocalDate getDeliveryDate(LocalDate date, List<PracticeDTO> practicesArray) {
        for (int i = 0; i < practicesArray.size(); i++) {
            date.plusDays(2);
        }
        return date;
    }
}

package classes;

import config.Database;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Petition {
    private int petitionId;
    private static String medInsurance;
    private static LocalDate loadDate;
    private static LocalDate deliverDate;
    private List<String> practices;
    private boolean petitionCompleted;
    private static int counter = 1;

    public Petition(String medInsurance, LocalDate loadDate, List<String> practices) {
        this.petitionId = this.counter;
        this.medInsurance = medInsurance;
        this.loadDate = loadDate;
        this.practices = practices;
        this.deliverDate = getDeliveryDate(this.loadDate, this.practices);
        this.petitionCompleted = false;
        createPetition(this);
        this.counter++;
    }

    private void createPetition(Petition petition) {
        Database.createPetition(petition);
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

    public static String getMedInsurance() {
        return medInsurance;
    }

    public static String getLoadDate() {
        return loadDate.toString();
    }

    public static String getEtaDate() {
        return deliverDate.toString();
    }

    private static LocalDate getDeliveryDate(LocalDate date, List<String> practicesArray) {
        for (int i = 0; i < practicesArray.size(); i++) {
            date.plusDays(2);
        }
        return date;
    }

}

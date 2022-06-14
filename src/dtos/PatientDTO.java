package dtos;

import classes.Patient;
import config.Database;
import controllers.PatientController;

import java.util.ArrayList;
import java.util.List;

public class PatientDTO {
    private String patientId;
    private String name;
    private String address;
    private String mail;
    private String genre;
    private String age;
    private PatientDTO self;
    private PatientController patInstance;
    private List<PetitionDTO> petitionsArray = new ArrayList<>();

    public PatientDTO(String patientId, String name, String address, String mail, String genre, String age) {
        this.patInstance = PatientController.getInstance();
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.genre = genre;
        this.age = age;
        createPatient(this);
    }

    public void addPetitions(PetitionDTO petition) {
        petitionsArray.add(petition);
    }

    @Override
    public String toString() {
        return name;
    }

    public void createPatient(PatientDTO patientDTO) {
        Patient patient = new Patient(patientDTO);
    }

    public void deletePatient(String patientId) {
        // Deletes a desired patient through the patient id
        patInstance.deletePatient(patientId);
    }

    private void editPatient() {
        // Modifies the data of a desired patient through the patient id
    }

    // Getters
    public String getPatientId() {
        return this.patientId;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getMail() {
        return this.mail;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getAge() {
        return this.age;
    }
}

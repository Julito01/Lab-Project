package dtos;

import classes.Patient;
import controllers.PatientController;

import java.util.ArrayList;
import java.util.List;

public class PatientDTO {
    private int patientId;
    private String patientDni;
    private String name;
    private String address;
    private String mail;
    private String genre;
    private String age;
    private PatientController patInstance;
    private List<PetitionDTO> petitionsArray = new ArrayList<>();
    private PatientDTO self;
    private static int counter = 1;

    public PatientDTO(String patientDni, String name, String address, String mail, String genre, String age) {
        this.patientId = counter;
        this.patInstance = PatientController.getInstance();
        this.patientDni = patientDni;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.genre = genre;
        this.age = age;
        this.self = this;
        counter++;
    }

    public PatientDTO getPatientDTO() {
        return this.self;
    }

    public void addPetitions(PetitionDTO petition) {
        petitionsArray.add(petition);
    }

    public int getPatientId() {
        return this.patientId;
    }

    @Override
    public String toString() {
        return name;
    }

    public void createPatient(PatientDTO patientDTO) {
        Patient patient = new Patient(patientDTO);
    }

    private void editPatient() {
        // Modifies the data of a desired patient through the patient id
    }

    // Getters
    public String getPatientDni() {
        return this.patientDni;
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

    // Setters

    public void setPatientDni(String patientDni) {
        this.patientDni = patientDni;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

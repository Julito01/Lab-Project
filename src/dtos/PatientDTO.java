package dtos;

import classes.Patient;
import config.Database;
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
    private List<PetitionDTO> petitionsArray = new ArrayList<>();

    public PatientDTO(String patientId, String name, String address, String mail, String genre, String age) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.genre = genre;
        this.age = age;
        this.self = self;
    }

    public void addPetitions(PetitionDTO petition) {
        petitionsArray.add(petition);
    }

    @Override
    public String toString() {
        return name;
    }

    public static List<PatientDTO> getPatients() {
        List<PatientDTO> patients = Database.getAllPatients();
        return patients;
    }

    public static void createPatient(PatientDTO patientDTO) {
        String patientId = patientDTO.getPatientId();
        String name = patientDTO.getName();
        String address = patientDTO.getAddress();
        String mail = patientDTO.getMail();
        String genre = patientDTO.genre;
        String age = patientDTO.getAge();

        Patient patient = new Patient(patientId, name, address, mail, genre, age);
        Database.createPatient(patient);
    }

    public static void deletePatient(String patientId) {
        // Deletes a desired patient through the patient id
        Database.deletePatient(patientId);
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

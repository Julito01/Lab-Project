package classes;
import config.Database;

import java.util.List;

public class Patient extends Person {
    private String patientId;
    private String name;
    private String address;
    private String mail;
    private String genre;
    private String age;
    private Patient self;

    public Patient(String patientId, String name, String address, String mail, String genre, String age) {
        this.patientId = patientId;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.genre = genre;
        this.age = age;
        this.self = this;
    }

    public static List<Patient> getPatients() {
        List<Patient> patients = Database.getAllPatients();
        return patients;
    }

    public static void createPatient(Patient patient) {
        Database.createPatient(patient);
    }

    private void deletePatient() {
        // Deletes a desired patient through the patient id
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

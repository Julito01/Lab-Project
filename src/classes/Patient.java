package classes;
import config.Database;
import dtos.PatientDTO;

import java.util.List;

public class Patient extends Person {
    private String patientId;
    private String name;
    private String address;
    private String mail;
    private String genre;
    private String age;
    private Patient self;

    public Patient(PatientDTO patient) {
        this.patientId = patient.getPatientId();
        this.name = patient.getName();
        this.address = patient.getAddress();
        this.mail = patient.getMail();
        this.genre = patient.getGenre();
        this.age = patient.getAge();
        this.self = this;
        createPatient(this);
    }
    private void createPatient(Patient patient) {
        Database.createPatient(patient);
    }

    public static void deletePatient(String patientId) {
        // Deletes a desired patient through the patient id
        Database.deletePatient(patientId);
    }

    private void editPatient() {
        // Modifies the data of a desired patient through the patient id
    }

    public static List<PatientDTO> getAllPatients() {
        List<PatientDTO> patients = Database.getAllPatients();
        return patients;
    }

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

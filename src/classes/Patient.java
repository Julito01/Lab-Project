package classes;
import config.Database;
import dtos.PatientDTO;

import java.util.List;

public class Patient extends Person {
    private int patientId;
    private String patientDni;
    private String name;
    private String address;
    private String mail;
    private String genre;
    private String age;
    private Patient self;

    public Patient(PatientDTO patient) {
        this.patientId = patient.getPatientId();
        this.patientDni = patient.getPatientDni();
        this.name = patient.getName();
        this.address = patient.getAddress();
        this.mail = patient.getMail();
        this.genre = patient.getGenre();
        this.age = patient.getAge();
        this.self = this;
        System.out.println(patientId);
    }
    public void createPatient() {
        Database.createPatient(self);
    }

    public static void deletePatient(int patientId) {
        // Deletes a desired patient through the patient id
        Database.deletePatient(patientId);
    }

    public static void updatePatient(PatientDTO patient) {
        // Modifies the data of a desired patient through the patient id
        Database.updatePatient(patient);
    }

    public static List<PatientDTO> getAllPatients() {
        List<PatientDTO> patients = Database.getAllPatients();
        return patients;
    }

    // Getters
    public int getPatientId() {
        return this.patientId;
    }

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
}

package controllers;

import classes.Patient;
import dtos.PatientDTO;
import vista.AdminMainFrame;

import java.util.List;

public class PatientController {
    private static PatientController pcObject;

    private PatientController() {}

    public static PatientController getInstance() {
        if (pcObject == null) {
            pcObject = new PatientController();
        }
        return pcObject;
    }

    public boolean setPatient(PatientDTO patient) {
        if (verifyPatientId(patient.getPatientDni()) && verifyName(patient.getName()) && verifyAddress(patient.getAddress()) && verifyEmail(patient.getMail()) && verifyAge(patient.getAge())) {
            Patient newPatient = new Patient(patient);
            newPatient.createPatient();
            AdminMainFrame.setDefaultValuesArray(patient);
            return true;
        }
        else {
            return false;
        }
        /* Ejemplo para preguntarle al profe
           public int addPersona(PersonaDTO unaPersona) {
                listPersonas.add(new Persona(unaPersona));
                return listaPersonas.size();
           }
        */

    }

    public void updatePatient(PatientDTO patient) {
        Patient.updatePatient(patient);
    }

    public List<PatientDTO> getAllPatients() {
        return Patient.getAllPatients();
    }

    public void deletePatient(int patientId) {
        Patient.deletePatient(patientId);
    }

    private boolean verifyPatientId(String id) {
        if (id.length() > 0 && id.length() <= 8) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean verifyName(String name) {
        if (name.length() > 7) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean verifyAddress(String address) {
        if (address.length() > 5) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean verifyEmail(String email) {
        if (email.length() > 10) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean verifyAge(String age) {
        int patientAge = Integer.parseInt(age);
        if (patientAge > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
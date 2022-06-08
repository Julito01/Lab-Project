package controllers;

import dtos.PatientDTO;
import vista.AdminMainFrame;

public class PatientController {
    private static PatientController pcObject;

    private PatientController() {}

    public static PatientController getInstance() {
        if (pcObject == null) {
            pcObject = new PatientController();
        }
        return pcObject;
    }

    public boolean setPatient(String patientId, String name, String address, String email, String genre, String age) {
        if (verifyPatientId(patientId) && verifyName(name) && verifyAddress(address) && verifyEmail(email) && verifyAge(age)) {
            PatientDTO patientDTO = new PatientDTO(patientId, name, address, email, genre, age);
            AdminMainFrame.setDefaultValuesArray(patientDTO);
            return true;
        }
        else {
            return false;
        }
    }

    public void deletePatient(String patientId) {
        PatientDTO.deletePatient(patientId);
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
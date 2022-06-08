package controllers;

import classes.Patient;

import javax.swing.*;

public class PatientController {
    public static boolean setPatient(String patientId, String name, String address, String email, String genre, String age) {
        if (verifyPatientId(patientId) && verifyName(name) && verifyAddress(address) && verifyEmail(email) && verifyAge(age)) {
            Patient patient = new Patient(patientId, name, address, email, genre, age);
            Patient.createPatient(patient);
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean verifyPatientId(String id) {
        if (id.length() > 7) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean verifyName(String name) {
        if (name.length() > 7) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean verifyAddress(String address) {
        if (address.length() > 5) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean verifyEmail(String email) {
        if (email.length() > 10) {
            return true;
        }
        else {
            return false;
        }
    }

    private static boolean verifyAge(String age) {
        int patientAge = Integer.parseInt(age);
        if (patientAge > 0){
            return true;
        }
        else {
            return false;
        }
    }
}
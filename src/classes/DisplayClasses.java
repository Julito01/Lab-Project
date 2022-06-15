package classes;

import controllers.*;
import dtos.*;

import javax.swing.*;
import java.util.List;

public class DisplayClasses {
    private static boolean firstQuery = true;
    private static PatientController patInstance = PatientController.getInstance();
    private static StationController staInstance = StationController.getInstance();
    private static UserController usrInstance = UserController.getInstance();
//    private static ResultController resInstance = ResultController.getInstance();
    private static PracticeController practInstance = PracticeController.getInstance();
    private static PetitionController petInstance = PetitionController.getInstance();

    public static DefaultListModel<PatientDTO> displayPatients(List<PatientDTO> patientsArray, List<PatientDTO> defaultPatient, DefaultListModel<PatientDTO> patientListModel) {
        if (firstQuery) {
            patientsArray = patInstance.getAllPatients();
            for (PatientDTO patientDTO : patientsArray) {
                defaultPatient.add(patientDTO);
                patientListModel.addElement(patientDTO);
            }
            firstQuery = false;
        }
        else {
            patientListModel.removeAllElements();
            for (PatientDTO patient : defaultPatient) {
                patientListModel.addElement(patient);
            }
        }
        return patientListModel;
    }

    public static DefaultListModel<StationDTO> displayStations(List<StationDTO> stationsArray, List<StationDTO> defaultPatient, DefaultListModel<StationDTO> stationListModel) {
        if (firstQuery) {
            stationsArray = staInstance.getAllStations();
            for (StationDTO stationDTO : stationsArray) {
                defaultPatient.add(stationDTO);
                stationListModel.addElement(stationDTO);
            }
            firstQuery = false;
        }
        else {
            stationListModel.removeAllElements();
            for (StationDTO station : defaultPatient) {
                stationListModel.addElement(station);
            }
        }
        return stationListModel;
    }

    public static DefaultListModel<SystemUserDTO> displayUsers(List<SystemUserDTO> usersArray, List<SystemUserDTO> defaultUser, DefaultListModel<SystemUserDTO> userListModel) {
        if (firstQuery) {
            usersArray = usrInstance.getAllUsers();
            for (SystemUserDTO userDTO : usersArray) {
                defaultUser.add(userDTO);
                userListModel.addElement(userDTO);
            }
            firstQuery = false;
        }
        else {
            userListModel.removeAllElements();
            for (SystemUserDTO userDTO : defaultUser) {
                userListModel.addElement(userDTO);
            }
        }
        return userListModel;
    }

    public static DefaultListModel<PracticeDTO> displayPractices(List<PracticeDTO> practicesArray, List<PracticeDTO> defaultPractice, DefaultListModel<PracticeDTO> practiceListModel) {
        if (firstQuery) {
            practicesArray = practInstance.getAllPractices();
            for (PracticeDTO practiceDTO : practicesArray) {
                defaultPractice.add(practiceDTO);
                practiceListModel.addElement(practiceDTO);
            }
            firstQuery = false;
        }
        else {
            practiceListModel.removeAllElements();
            for (PracticeDTO practiceDTO : defaultPractice) {
                practiceListModel.addElement(practiceDTO);
            }
        }
        return practiceListModel;
    }

    public static DefaultListModel<ResultDTO> displayResults(List<ResultDTO> resultsArray, List<ResultDTO> defaultResult, DefaultListModel<ResultDTO> resultListModel) {
        if (firstQuery) {
//            resultsArray = resInstance.getAllResults();
            for (ResultDTO resultDTO : resultsArray) {
                defaultResult.add(resultDTO);
                resultListModel.addElement(resultDTO);
            }
            firstQuery = false;
        }
        else {
            resultListModel.removeAllElements();
            for (ResultDTO resultDTO : defaultResult) {
                resultListModel.addElement(resultDTO);
            }
        }
        return resultListModel;
    }

    public static DefaultListModel<PetitionDTO> displayPetitions(List<PetitionDTO> petitionsArray, List<PetitionDTO> defaultPetition, DefaultListModel<PetitionDTO> petitionListModel) {
        if (firstQuery) {
//            petitionsArray = petInstance.getAllPetitions();
            for (PetitionDTO petitionDTO : petitionsArray) {
                defaultPetition.add(petitionDTO);
                petitionListModel.addElement(petitionDTO);
            }
            firstQuery = false;
        }
        else {
            petitionListModel.removeAllElements();
            for (PetitionDTO petitionDTO : defaultPetition) {
                petitionListModel.addElement(petitionDTO);
            }
        }
        return petitionListModel;
    }
}

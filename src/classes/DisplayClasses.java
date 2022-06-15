package classes;

import controllers.*;
import dtos.*;

import javax.swing.*;
import java.util.List;

public class DisplayClasses {
    private static boolean firstQueryPatient = true;
    private static boolean firstQueryStation = true;
    private static boolean firstQueryPetition = true;
    private static boolean firstQueryResult = true;
    private static boolean firstQueryUser = true;
    private static boolean firstQueryPractice = true;
    private static PatientController patInstance = PatientController.getInstance();
    private static StationController staInstance = StationController.getInstance();
    private static UserController usrInstance = UserController.getInstance();
//    private static ResultController resInstance = ResultController.getInstance();
    private static PracticeController practInstance = PracticeController.getInstance();
    private static PetitionController petInstance = PetitionController.getInstance();

    public static DefaultListModel<PatientDTO> displayPatients(List<PatientDTO> patientsArray, List<PatientDTO> defaultPatient, DefaultListModel<PatientDTO> patientListModel) {
        if (firstQueryPatient) {
            patientsArray = patInstance.getAllPatients();
            for (PatientDTO patientDTO : patientsArray) {
                defaultPatient.add(patientDTO);
                patientListModel.addElement(patientDTO);
            }
            firstQueryPatient = false;
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
        if (firstQueryStation) {
            stationsArray = staInstance.getAllStations();
            for (StationDTO stationDTO : stationsArray) {
                defaultPatient.add(stationDTO);
                stationListModel.addElement(stationDTO);
            }
            firstQueryStation = false;
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
        if (firstQueryUser) {
            usersArray = usrInstance.getAllUsers();
            for (SystemUserDTO userDTO : usersArray) {
                defaultUser.add(userDTO);
                userListModel.addElement(userDTO);
            }
            firstQueryUser = false;
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
        if (firstQueryPractice) {
            practicesArray = practInstance.getAllPractices();
            for (PracticeDTO practiceDTO : practicesArray) {
                defaultPractice.add(practiceDTO);
                practiceListModel.addElement(practiceDTO);
            }
            firstQueryPractice = false;
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
        if (firstQueryResult) {
//            resultsArray = resInstance.getAllResults();
            for (ResultDTO resultDTO : resultsArray) {
                defaultResult.add(resultDTO);
                resultListModel.addElement(resultDTO);
            }
            firstQueryResult = false;
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
        if (firstQueryPetition) {
//            petitionsArray = petInstance.getAllPetitions();
            for (PetitionDTO petitionDTO : petitionsArray) {
                defaultPetition.add(petitionDTO);
                petitionListModel.addElement(petitionDTO);
            }
            firstQueryPetition = false;
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

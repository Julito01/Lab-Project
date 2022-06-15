package classes;

import dtos.*;

import javax.swing.*;
import java.util.List;

public class FilterModel {
    public static void filterModelPatient(DefaultListModel<PatientDTO> model, String filter, List<PatientDTO> defaultPatient) {
        for (PatientDTO s : defaultPatient) {
            if (!s.toString().startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }

    public static void filterModelPetition(DefaultListModel<PetitionDTO> model, String filter, List<PetitionDTO> defaultPetition) {
        for (PetitionDTO s : defaultPetition) {
            if (!s.toString().startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }

    public static void filterModelPractice(DefaultListModel<PracticeDTO> model, String filter, List<PracticeDTO> defaultPractice) {
        for (PracticeDTO s : defaultPractice) {
            if (!s.toString().startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }

    public static void filterModelUser(DefaultListModel<SystemUserDTO> model, String filter, List<SystemUserDTO> defaultPetition) {
        for (SystemUserDTO s : defaultPetition) {
            if (!s.toString().startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }

    public static void filterModelResult(DefaultListModel<ResultDTO> model, String filter, List<ResultDTO> defaultResult) {
        for (ResultDTO s : defaultResult) {
            if (!s.toString().startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }

    public static void filterModelStation(DefaultListModel<StationDTO> model, String filter, List<StationDTO> defaultStation) {
        for (StationDTO s : defaultStation) {
            if (!s.toString().startsWith(filter)) {
                if (model.contains(s)) {
                    model.removeElement(s);
                }
            } else {
                if (!model.contains(s)) {
                    model.addElement(s);
                }
            }
        }
    }
}

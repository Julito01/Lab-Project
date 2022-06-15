package controllers;
import classes.Practice;
import dtos.PracticeDTO;

import java.util.List;

public class PracticeController {
    private static PracticeController pcObject;
    private PracticeController() {}

    public static PracticeController getInstance() {
        if (pcObject == null) {
            pcObject = new PracticeController();
        }
        return pcObject;
    }

    public void createPractice() {

    }

    public static List<PracticeDTO> getAllPractices() {
        return Practice.getAllPractices();
    }

    public static int getCurrPracticeId(String practiceName) {
        return Practice.getCurrPracticeId(practiceName);
    }
}

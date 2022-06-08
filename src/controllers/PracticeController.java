package controllers;

import classes.Practice;

import java.util.List;

public class PracticeController {
    public static List<String> getAllPractices() {
        return Practice.getAllPractices();
    }

    public static int getCurrPracticeId(String practiceName) {
        return Practice.getCurrPracticeId(practiceName);
    }
}

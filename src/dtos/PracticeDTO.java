package dtos;
import java.util.*;

import classes.Practice;
import classes.enumerations.ResultType;
import config.Database;

import javax.xml.transform.Result;

public class PracticeDTO {
    private static int practiceId;
    private int counter = 1;
    private static int practCodeCounter = 1000;
    private static int practiceCode;
    private static String practiceName;

    public PracticeDTO(String practiceName) {
        practiceId = counter;
        practiceCode = practCodeCounter;
        PracticeDTO.practiceName = practiceName;
        createPractice(this);
        counter++;
        practCodeCounter++;
    }

    public void setPracticePetition() {

    }

    protected void getResultType() {
        // Get the type of the result (could be Critical or Reserved)
        // return /* something */
    }

    private void createPractice(PracticeDTO practiceDTO) {
        String practiceName = practiceDTO.getPracticeName();

        Practice practice = new Practice(practiceName);
    }

    private void deletePractice() {
        Database.deletePractice();
    }

    private void editPractice() {
        Database.editPractice();
    }

    public int getCurrPracticeId(String practiceName) {
        return Database.getPracticeId(practiceName);
    }

    public List<String> getAllPractices() {
        return Database.getPractices();
    }

    public int getPracticeId() {
        return practiceId;
    }

    public int getPracticeCode() {
        return practiceCode;
    }

    public String getPracticeName() {
        return practiceName;
    }
}

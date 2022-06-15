package classes;
import java.util.*;
import classes.enumerations.ResultType;
import config.Database;
import dtos.PracticeDTO;

import javax.xml.transform.Result;

public class Practice {
    private static int practiceId;
    private int counter = 1;
    private static int practiceCode;
    private static String practiceName;

    public Practice(String practiceName, int practiceCode) {
        this.practiceId = counter;
        this.practiceCode = practiceCode;
        this.practiceName = practiceName;
        counter++;
    }

    protected void getResultType() {
        // Get the type of the result (could be Critical or Reserved)
        // return /* something */
    }

    public static void createPractice(PracticeDTO practice) {
        Database.createPractice(practice);
    }

    private void deletePractice(int practiceId) {
        Database.deletePractice(practiceId);
    }

    private void editPractice(PracticeDTO practice) {
        Database.updatePractice(practice);
    }

    public static int getCurrPracticeId(String practiceName) {
        return Database.getPracticeId(practiceName);
    }

    public static List<PracticeDTO> getAllPractices() {
        return Database.getAllPractices();
    }

    public static int getPracticeId() {
        return practiceId;
    }

    public static int getPracticeCode() {
        return practiceCode;
    }

    public static String getPracticeName() {
        return practiceName;
    }
}

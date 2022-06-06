package classes;
import java.util.*;
import classes.enumerations.ResultType;
import config.Database;

import javax.xml.transform.Result;

public class Practice {
    private static int practiceId;
    private int counter = 1;
    private static int practCodeCounter = 1000;
    private static int practiceCode;
    private static String practiceName;

    public Practice(String practiceName) {
        practiceId = counter;
        practiceCode = practCodeCounter;
        Practice.practiceName = practiceName;
        counter++;
        practCodeCounter++;
    }

    protected void getResultType() {
        // Get the type of the result (could be Critical or Reserved)
        // return /* something */
    }

    private void createPractice() {
        Database.createPractice();
    }

    private void deletePractice() {
        Database.deletePractice();
    }

    private void editPractice() {
        Database.editPractice();
    }

    public static void setPracticePetition(Patient patient, Petition petition, int practId) {
        Database.createPacientPetition(patient, petition, practId);
    }

    public static int getCurrPracticeId(String practiceName) {
        int practiceData = Database.getPracticeId(practiceName);
        return practiceData;
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

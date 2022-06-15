package dtos;

import java.sql.Time;
import config.Database;

import javax.xml.transform.Result;

public class PracticeDTO {
    private static int practiceId;
    private int counter = 1;
    private static int practiceCode;
    private static String practiceName;
    private Time eth;

    public PracticeDTO(int practiceCode, String practiceName, Time eth) {
        this.practiceId = counter;
        this.practiceCode = practiceCode;
        this.practiceName = practiceName;
        this.eth = eth;
        counter++;
    }

    public void setPracticePetition() {

    }

    protected void getResultType() {
        // Get the type of the result (could be Critical or Reserved)
        // return /* something */
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

    public int getPracticeId() {
        return practiceId;
    }

    public int getPracticeCode() {
        return practiceCode;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public Time getEth() {
        return this.eth;
    }
}

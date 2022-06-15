package dtos;
<<<<<<< HEAD
import java.time.LocalTime;
import java.util.*;
=======
>>>>>>> d96e22e5c5e4c26d4ec68b50cb7b03cd84cd1cf0

import config.Database;

public class PracticeDTO {
    private static int practiceId;
    private int counter = 1;
    private static int practiceCode;
    private static String practiceName;
    private String eth;

<<<<<<< HEAD
    public PracticeDTO(String practiceName, int practiceCode) {
        this.practiceId = counter;
        this.practiceCode = practiceCode;
        this.practiceName = practiceName;
        createPractice(this);
=======
    public PracticeDTO(int practiceCode, String practiceName, String eth) {
        this.practiceId = counter;
        this.practiceCode = practiceCode;
        this.practiceName = practiceName;
        this.eth = eth;
>>>>>>> d96e22e5c5e4c26d4ec68b50cb7b03cd84cd1cf0
        counter++;
    }

    public void setPracticePetition() {

    }

    protected void getResultType() {
        // Get the type of the result (could be Critical or Reserved)
        // return /* something */
    }

    public int getCurrPracticeId(String practiceName) {
        return Database.getPracticeId(practiceName);
    }

<<<<<<< HEAD
    public LocalTime getPracticeLength(int practiceId) {return Database.getPracticeLength(practiceId);}
    public List<String> getAllPractices() {
        return Database.getPractices();
    }

=======
>>>>>>> d96e22e5c5e4c26d4ec68b50cb7b03cd84cd1cf0
    public int getPracticeId() {
        return practiceId;
    }

    public int getPracticeCode() {
        return practiceCode;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public String getEth() {
        return this.eth;
    }
}

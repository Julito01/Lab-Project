package classes;
import java.util.*;
import classes.enumerations.ResultType

import javax.xml.transform.Result;

public class Practice {
    private int practiceId;
    private int practiceCode;
    private String practiceName;
    private ArrayList group;
    private Date resultETD;

    public Practice() {}

    public Practice(int practiceId, int practiceCode, String practiceName, ArrayList group, Date resultETD) {
        this.practiceId = practiceId;
        this.practiceCode = practiceCode;
        this.practiceName = practiceName;
        this.group = group;
        this.resultETD = resultETD;
    }

    protected ResultType getResultType() {
        // Get the type of the result (could be Critical or Reserved)
        return /* something */
    }

    private void createPractice() {
        // Creates a new practice
    }

    private void deletePractice() {
        // Deletes a practice through the practice id
    }

    private void editPractice() {
        // Modifies a practice through the practice id
    }
}

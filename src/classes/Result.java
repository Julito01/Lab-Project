package classes;
import classes.enumerations.ResultType;
import classes.enumerations.ResultValueType;

public class Result {

    private int petitionId;
    private ResultType resultType;
    private ResultValueType resultValueType;

    private static int contador = 1;

    public Result(int petitionId, ResultType resultType, ResultValueType resultValueType) {
        this.petitionId = petitionId;
        this.resultType = resultType;
        this.resultValueType = resultValueType;
        contador++;
    }
}

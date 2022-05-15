package classes;
import classes.enumerations.ResultType;
import classes.enumerations.ResultValueType;

public class Result {
    private ResultType resultType;
    private ResultValueType resultValueType;

    public Result(ResultType resultType, ResultValueType resultValueType) {
        this.resultType = resultType;
        this.resultValueType = resultValueType;
    }
}

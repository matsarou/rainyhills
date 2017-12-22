package rest.validation;

/**
 * A runtime Exception thrown when the api methods take an invalid input.
 */
public class InvalidInputException extends  RuntimeException {

    private final RainyHillsError error;

    public InvalidInputException() {
        this("", null);
    }

    public InvalidInputException(String message, RainyHillsError error) {
        super(message);
        this.error = error;
    }

    public RainyHillsError getError() {
        return error;
    }
}

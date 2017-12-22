package rest.validation;

/**
 * Business errors in an enum set, with a code and a small description of the error.
 * This enum values are used to construct the ErrorDtos that the server will send to the clients.
 */
public enum RainyHillsError {
    INVALID_INPUT(1, "The input array of integers is invalid.");

    private final int errorCode;
    private final String errorValue;

    private RainyHillsError(int errorCode, String errorValue) {
        this.errorCode = errorCode;
        this.errorValue = errorValue;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorValue() {
        return errorValue;
    }
}

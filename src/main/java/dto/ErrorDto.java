package dto;

import rest.validation.RainyHillsError;

/**
 * Data Tranfer Object, that transfers the errors in JSON format, from the REST layer to the clients.
 */
public class ErrorDto {

	ErrorDto() {
		super();
	}

	/**
	 * The error status code.
	 */
	private int errorCode;

	/**
	 * Error text value. It is optional.
	 */
	private String errorValue;

	public ErrorDto(RainyHillsError error) {
		this(error.getErrorCode(), error.getErrorValue());
	}

	public ErrorDto(int errorCode, String errorValue) {
		this.errorCode = errorCode;
		this.errorValue = errorValue;
	}

	public int getErrorCode() {
		return errorCode;
	}

	void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorValue() {
		return errorValue;
	}

	void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}
}

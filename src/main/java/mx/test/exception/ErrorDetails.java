package mx.test.exception;

import java.util.Date;

public class ErrorDetails {

	/**
	 * 
	 */

	private Date timestamp;
	private String message;
	private String details;
	private String idError;

	public ErrorDetails() {
		super();
	}
	
	public ErrorDetails(Date timestamp, String message, String details, String idError) {
		super();
		this.timestamp = (Date) timestamp.clone();
		this.message = message;
		this.details = details;
		this.idError = idError;
	}

	public Date getTimestamp() {
		Date fecha = timestamp;
		return fecha;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = (Date) timestamp.clone();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getIdError() {
		return idError;
	}

	public void setIdError(String idError) {
		this.idError = idError;
	}

}
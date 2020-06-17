package mx.test.dto;

import java.io.Serializable;

public class ResponseStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Status status;
	private String rqUID;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	public String getRqUID() {
		return rqUID;
	}
	public void setRqUID(String rqUID) {
		this.rqUID = rqUID;
	}
	

}

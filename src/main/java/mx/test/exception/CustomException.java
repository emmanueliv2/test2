package mx.test.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class CustomException extends RuntimeException  {
	public CustomException(String exception) {
	    super(exception);
  }
}

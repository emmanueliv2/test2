package mx.test.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.test.dto.AdditionalStatus;
import mx.test.dto.ResponseStatus;
import mx.test.dto.Status;
import mx.test.utils.UuidGeneratorImp;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private UuidGeneratorImp uuidGen;
	
	private static final Logger LOG = Logger.getLogger(CustomizedResponseEntityExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<?> handleCodeExceptions(Exception e, ResponseStatus rs, HttpStatus status) {
		System.out.println("entre en exception");
		// Valida si se recibe un responseStatus
		if (rs == null) {
			ErrorDetails detail = new ErrorDetails();
			CharSequence cs = "{";
			if (e.getMessage().contains(cs) == true) {
				return contieneErrorDetail(e, rs, status);
			} else {
				String uuid = uuidGen.generateUuid();
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				errors.toString();
				String[] codigoDesc = e.getMessage().toString().split(",");
				detail = new ErrorDetails(new Date(), codigoDesc[0], codigoDesc[1], uuid);
			}
			rs = new ResponseStatus();
			Status st = new Status();
			AdditionalStatus as = new AdditionalStatus();

			String[] codigoDesc = e.getMessage().toString().split(",");

			st.setStatusCode("0");
			st.setServerStatusCode("200");
			st.setSeverity("INFO");
			st.setStatusDesc("OK");
			as.setStatusCode(codigoDesc[0]);
			as.setServerStatusCode(status.toString());
			as.setSeverity("INFO");
			as.setStatusDesc(codigoDesc[1]);
			st.setAdditionalStatus(as);
			rs.setStatus(st);
			return new ResponseEntity<ResponseStatus>(rs, status);
		} else {
			return new ResponseEntity<ResponseStatus>(rs, status);
		}
	}

	private ResponseEntity<?> contieneErrorDetail(Exception e, ResponseStatus rs, HttpStatus status) {

		String errorDetails = e.getMessage().substring(e.getMessage().indexOf('{') + 1, e.getMessage().indexOf('}'));

		LinkedHashMap<Object, Object> myMap = new LinkedHashMap<Object, Object>();

		String[] pairs = errorDetails.split(",");
		for (int i = 0; i < pairs.length; i++) {
			String pair = pairs[i];

			Pattern p = Pattern.compile("[=]", Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(pair);

			boolean b = m.find();
			String[] keyValue;
			if (b) {
				keyValue = pair.split("=");
			} else {
				keyValue = pair.split(":");

				ObjectMapper mapper = new ObjectMapper();
				ErrorDetails error = new ErrorDetails();
				try {
					// setea el error detail
					error = mapper.readValue("{" + errorDetails + "}", ErrorDetails.class);

					Status st = new Status();
					AdditionalStatus as = new AdditionalStatus();

					st.setStatusCode("0");
					st.setServerStatusCode("200");
					st.setSeverity("INFO");
					st.setStatusDesc("OK");
					as.setStatusCode(error.getMessage());
					as.setServerStatusCode(status.toString());
					as.setSeverity("INFO");
					as.setStatusDesc(error.getDetails());
					st.setAdditionalStatus(as);
					rs = new ResponseStatus();
					rs.setStatus(st);
				} catch (JsonParseException e1) {
					LOG.error(e1.getMessage());

				} catch (JsonMappingException e1) {
					LOG.error(e1.getMessage());

				} catch (IOException e1) {
					LOG.error(e1.getMessage());

				}
				return new ResponseEntity<ResponseStatus>(rs, status);
			}
			myMap.put(keyValue[0].toString(), keyValue[1]);
		}
		return null;

	}

}

package mx.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.test.dto.ResponseStatus;
import mx.test.service.Test2Service;
import mx.test.utils.Utils;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 
 * Class con endpoints de servicios
 */
@RestController
@RequestMapping("test/")
@EnableSwagger2
public class Test2Controller {

	@Autowired
	private Test2Service test2Service;

	/**
	 * @param mensaje
	 * @return
	 */
	@RequestMapping(value="validate",
			method =RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public ResponseEntity<?> validatePhone(@RequestParam(value="phone", required=true) String phone) {
		ResponseStatus rs = test2Service.validatePhone(phone);
		return new ResponseEntity<ResponseStatus>(rs, HttpStatus.OK);
	}
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public ResponseEntity<ResponseStatus> handleMissingParams(MissingServletRequestParameterException ex) {
	    ResponseStatus rs = new ResponseStatus();
		
		rs = Utils.ntfmx("0", "200", "INFO", "OK", "400", "400", "ERROR", ex.getParameterName() + " parameter is missing");
		return new ResponseEntity<ResponseStatus>(rs, HttpStatus.BAD_REQUEST);
	    // Actual exception handling
	}
	
}

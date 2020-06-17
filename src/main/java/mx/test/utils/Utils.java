package mx.test.utils;

import org.springframework.stereotype.Component;

import mx.test.dto.AdditionalStatus;
import mx.test.dto.ResponseStatus;
import mx.test.dto.Status;

@Component("utils")
public class Utils {

	public static ResponseStatus ntfmx(String statusCode, String serverStatusCode, String severity, String statusDesc,
			String aStatusCode, String aServerStatusCode, String aSeverity, String aStatusDesc) {

		ResponseStatus rs = new ResponseStatus();
		Status st = new Status();
		AdditionalStatus as = new AdditionalStatus();

		st.setStatusCode(statusCode);
		st.setServerStatusCode(serverStatusCode);
		st.setSeverity(severity);
		st.setStatusDesc(statusDesc);
		as.setStatusCode(aStatusCode);
		as.setServerStatusCode(aServerStatusCode);
		as.setSeverity(aSeverity);
		as.setStatusDesc(aStatusDesc);
		st.setAdditionalStatus(as);
		rs.setStatus(st);

		return rs;
	}

}

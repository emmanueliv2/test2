package mx.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.test.dto.ResponseStatus;
import mx.test.entity.Test2;
import mx.test.repository.Test2Repository;
import mx.test.service.Test2Service;
import mx.test.utils.Utils;

@Service
public class Test2ServiceImpl implements Test2Service {

	@Autowired
	private Test2Repository test2Repository;

	@Override
	public ResponseStatus validatePhone(String phone) {
		ResponseStatus rs = new ResponseStatus();
		String aux = phone;
		List<Test2> testList = null;
		boolean band = true;
		try {
			aux = aux.replaceAll(" ", "");
			if (phone.contains("+52")) {
				aux = phone.replace("+52", "");
			} else if (phone.contains("+")) {
				band = false;
			}
			if (band) {
				String ini = aux.substring(0, 2);
				testList = test2Repository.getValidNumber(Integer.parseInt(ini),
						Integer.parseInt(aux.substring(2, 6)), Integer.parseInt(aux.substring(6)));
				if (testList != null && testList.size() > 0) {
					rs = Utils.ntfmx("0", "200", "INFO", "OK", "200", "NTFMX101", "INFO",
							"Número nacional valido: " + testList.get(0).toString());
				} else {
					rs = Utils.ntfmx("0", "200", "INFO", "OK", "404", "NTFMX111", "ERROR", "Número nacional inválido");
				}
			} else {
				rs = Utils.ntfmx("0", "200", "INFO", "OK", "202", "NTFMX101", "INFO", "Número internacional");
			}
		} catch (NumberFormatException e) {
			rs = Utils.ntfmx("0", "200", "INFO", "OK", "400", "NTFMX111", "ERROR",
					"Número nacional inválido contiene caracteres");
		}
		return rs;
	}

}

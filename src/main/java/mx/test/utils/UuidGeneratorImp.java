package mx.test.utils;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class UuidGeneratorImp implements IUuidGenerator{

	@Override
	public String generateUuid() {
		return UUID.randomUUID().toString();
	}

}


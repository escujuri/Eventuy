package ar.com.eventsocial.backend.service.contract;

import java.io.IOException;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.security.jwt.JwtRequestStep1;

public interface IRegisterService {

	RegisterResponseDTO Step1(JwtRequestStep1 jwtRequestStep1) throws IOException;
	
}

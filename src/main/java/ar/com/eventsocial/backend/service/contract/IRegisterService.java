package ar.com.eventsocial.backend.service.contract;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;

public interface IRegisterService {

	RegisterResponseDTO Step1(String username, String pass, String email);
	
	
}

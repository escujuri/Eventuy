package ar.com.eventsocial.backend.service.contract;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;

public interface ILoginService {
	
	RegisterResponseDTO authenticate(String username, String password, String ipFromRequest);
	
}

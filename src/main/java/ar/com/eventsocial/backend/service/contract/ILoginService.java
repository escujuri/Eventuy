package ar.com.eventsocial.backend.service.contract;

import java.io.IOException;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.model.Consult;

public interface ILoginService {
	
	RegisterResponseDTO authenticate(String email, String password, String ipFromRequest);
	
	RegisterResponseDTO postConsult(Consult consultRequest, String ip) throws IOException;
	
}

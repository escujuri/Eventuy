package ar.com.eventsocial.backend.service.contract;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;

public interface IBuscadorService {

	RegisterResponseDTO BuscadorByWord(String word);
	
	
}

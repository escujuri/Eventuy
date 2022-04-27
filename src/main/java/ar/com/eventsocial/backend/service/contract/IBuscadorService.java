package ar.com.eventsocial.backend.service.contract;

import ar.com.eventsocial.backend.dto.InmuebleReducidoResponseDTO;
import ar.com.eventsocial.backend.dto.InmuebleResponseDTO;

public interface IBuscadorService {

	InmuebleReducidoResponseDTO BuscadorByWord(String keyWord);
	
	InmuebleResponseDTO BuscadorByInmuebleId(String inmuebleId);
}

package ar.com.eventsocial.backend.service.contract;

import ar.com.eventsocial.backend.dto.InmuebleResponseDTO;
import ar.com.eventsocial.backend.model.Inmuebles;

public interface IInmubleService {

	InmuebleResponseDTO getinmuebleByIdOriginante(String usuario, Long id);
	
	InmuebleResponseDTO saveByIdOriginante(String usuario, String userId, Inmuebles datosInmueble)  throws Exception ;

	InmuebleResponseDTO deleteByIdOriginante(String userId, String idInmueble);
	
	InmuebleResponseDTO actualizarByIdOriginante(String usuario, String userId, String inmuebleId, Inmuebles datosInmueble)  throws Exception ;

}

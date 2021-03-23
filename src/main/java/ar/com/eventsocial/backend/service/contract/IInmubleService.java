package ar.com.eventsocial.backend.service.contract;

import ar.com.eventsocial.backend.dto.InmuebleResponseDTO;
import ar.com.eventsocial.backend.model.Inmuebles;

public interface IInmubleService {

	InmuebleResponseDTO inmuebleByIdOriginante(String usuario, Long id);
	
	InmuebleResponseDTO guardarByIdOriginante(String usuario, String userId, Inmuebles datosInmueble)  throws Exception ;

	InmuebleResponseDTO bajayIdOriginante(String userId, String idInmueble);
	
	InmuebleResponseDTO actualizarByIdOriginante(String usuario, String userId, String inmuebleId,String idServicio, Inmuebles datosInmueble)  throws Exception ;

	
	
}

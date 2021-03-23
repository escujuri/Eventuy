package ar.com.eventsocial.backend.repository.contract;

import java.util.List;

import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Servicios;

public interface IInmuebleRepository {

	List<Inmueble> inmuebleByUserId(Long userId);
	
	Long guardarInmueble(Inmueble inmueble);

	int bajaInmueble(String userId, String idInmueble);
	
	Long actualizarInmueble(String userId, String inmuebleId, Inmueble inmueble, Servicios servicios);
	
}

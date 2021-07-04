package ar.com.eventsocial.backend.repository.contract;

import java.util.List;

import ar.com.eventsocial.backend.model.Inmueble;

public interface IBuscadorRepository {

	List<Inmueble> getResultByWord(String word);
	
	
	
	
}

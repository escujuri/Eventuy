package ar.com.eventsocial.backend.service_;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.repository.contract.IBuscadorRepository;
import ar.com.eventsocial.backend.service.contract.IBuscadorService;

@Service("BuscadorService")
public class BuscadorService implements IBuscadorService {

	@Autowired
	IBuscadorRepository buscadorRepository;

	
	@Override
	public RegisterResponseDTO BuscadorByWord(String word) {
	
		List<Inmueble> test = buscadorRepository.getResultByWord(word);
	
		return null;
	}



}

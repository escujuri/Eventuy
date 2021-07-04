package ar.com.eventsocial.backend.service_;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.dto.PagosResponseDTO;
import ar.com.eventsocial.backend.logs.LogMaker;
import ar.com.eventsocial.backend.model.MercadoPagoRequest;
import ar.com.eventsocial.backend.repository.contract.IPagosRepository;
import ar.com.eventsocial.backend.service.contract.IPagoService;

@Service("IPagosService")
public class PagosService implements IPagoService {

	private static final LogMaker log = new LogMaker();

	@Autowired
	IPagosRepository pagosRepository;


	@Override
	public PagosResponseDTO pagarMP(MercadoPagoRequest datosMP) {

	
		
	String devolucion =	pagosRepository.pagarMP(datosMP);
		
	
			
		return null;
	}



}

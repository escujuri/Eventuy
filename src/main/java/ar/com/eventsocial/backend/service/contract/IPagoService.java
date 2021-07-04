package ar.com.eventsocial.backend.service.contract;

import ar.com.eventsocial.backend.dto.PagosResponseDTO;
import ar.com.eventsocial.backend.model.MercadoPagoRequest;

public interface IPagoService {

PagosResponseDTO pagarMP(MercadoPagoRequest datosMP);
	
	
}

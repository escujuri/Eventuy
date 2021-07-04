package ar.com.eventsocial.backend.repository.contract;

import ar.com.eventsocial.backend.model.MercadoPagoRequest;

public interface IPagosRepository {

	String pagarMP(MercadoPagoRequest datosMP);
	
	
}

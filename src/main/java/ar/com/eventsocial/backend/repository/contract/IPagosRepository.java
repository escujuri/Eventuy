package ar.com.eventsocial.backend.repository.contract;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;

import ar.com.eventsocial.backend.model.MercadoPagoRequest;
import ar.com.eventsocial.backend.model.ResponseCollectionMP;

public interface IPagosRepository {

	Preference paymentsMP(MercadoPagoRequest dataPayment, String userId, String email) throws MPException;

	ResponseCollectionMP dataPaymentMP(String url);

}

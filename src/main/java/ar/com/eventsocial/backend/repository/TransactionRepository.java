package ar.com.eventsocial.backend.repository;

import java.util.Collections;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.Preference.AutoReturn;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;

import ar.com.eventsocial.backend.model.MercadoPagoRequest;
import ar.com.eventsocial.backend.model.ResponseCollectionMP;
import ar.com.eventsocial.backend.repository.contract.IPagosRepository;

@Repository("PagosRepository")
public class TransactionRepository implements IPagosRepository {

	RestTemplate restTemplate = new RestTemplate();

	final private String ACCESS_TOKEN_KEY = "APP_USR-7477459527147951-082201-870637e4280c5179745e129d40393b95-344059923";

	@Override
	public Preference paymentsMP(MercadoPagoRequest dataPayment, String userId, String email) throws MPException {

		Preference preference = new Preference();

		try {

			BackUrls url = new BackUrls();
			url.setSuccess(dataPayment.getUrlBack()); // TODO: verificar donde re-dirigir

			/*
			 * Configuracion para redigirir a la pagina cuando el pago este aprobado
			 */
			AutoReturn autoReturnMP = AutoReturn.all;

			/*
			 * TODO: Poner credenciales de cuenta que usaremos
			 */
			MercadoPago.SDK.setAccessToken(ACCESS_TOKEN_KEY);

			/*
			 * Cargamos los datos del item a comprar
			 */
			Item item = new Item();

			item.setTitle(dataPayment.getTitle()).setQuantity(1).setUnitPrice(dataPayment.getCosto());

			preference.appendItem(item);
			preference.setAutoReturn(autoReturnMP);
			preference.setBackUrls(url);
			preference.setExternalReference(userId + dataPayment.getIdInmueble()
					+ dataPayment.getFechaReserva().getTime() + new Date().toInstant().toEpochMilli());

			/*
			 * Endpoint status change payments
			 */
			preference.setNotificationUrl("https://eventuy.rj.r.appspot.com/v1/payments/statusChange/MP");

			com.mercadopago.resources.datastructures.preference.Payer payer = new com.mercadopago.resources.datastructures.preference.Payer();
			payer.setEmail(email);
			preference.setPayer(payer);

			preference.save();

		} catch (MPException e) {
			System.out.println(e.getMessage());
		}

		return preference;
	}

	@Override
	public ResponseCollectionMP dataPaymentMP(String url) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", "Bearer " + ACCESS_TOKEN_KEY);

		HttpEntity requestEntity = new HttpEntity(headers);

		ResponseEntity<ResponseCollectionMP> response = null;

		response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ResponseCollectionMP.class);

		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Request Successful.");
			return response.getBody();
		} else {
			System.out.println("Request Failed");
			return null;
		}
	}

}

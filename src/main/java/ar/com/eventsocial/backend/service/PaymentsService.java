package ar.com.eventsocial.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;

import ar.com.eventsocial.backend.dto.BookingResponseDTO;
import ar.com.eventsocial.backend.dto.PaymentResponseDTO;
import ar.com.eventsocial.backend.model.MercadoPagoRequest;
import ar.com.eventsocial.backend.model.ResponseCollectionMP;
import ar.com.eventsocial.backend.model.StatusChangePaymentMP;
import ar.com.eventsocial.backend.model.Transacciones;
import ar.com.eventsocial.backend.repository.contract.IInmuebleRepository;
import ar.com.eventsocial.backend.repository.contract.IPagosRepository;
import ar.com.eventsocial.backend.repository.contract.ISengridRepository;
import ar.com.eventsocial.backend.repository.contract.ITransactionRepository;
import ar.com.eventsocial.backend.service.contract.IPagoService;

@Service("IPagosService")
public class PaymentsService implements IPagoService {

	@Autowired
	IPagosRepository paymentsRepository;

	@Autowired
	ITransactionRepository transactionRepository;
	
	@Autowired
	IInmuebleRepository inmuebleRepository;

	@Autowired
	ISengridRepository sengridRepository;
	
	public enum StatusOwnerApproval
	{
	    CREATED,
	    REJECTED,
	    ACCEPTED;
	}
	

	@Override
	public PaymentResponseDTO paymentsMPService(MercadoPagoRequest dataPayment, String userId, String email)
			throws MPException {

		Transacciones transaction = new Transacciones();
		PaymentResponseDTO response = new PaymentResponseDTO();

		String ownerUserId = inmuebleRepository.getOwnerUserId(dataPayment.getIdInmueble());
		
		
		if ( ownerUserId== null) {
			response.setCode("404");
			response.setMessage("No se encuentra el duenio del inmueble");
			return response;
		}
		
	
		/*
		 * Se arma el link de pagos
		 */
		Preference paymentInfo = paymentsRepository.paymentsMP(dataPayment, userId, email);
		transaction.setRequest_id(paymentInfo.getId());
		transaction.setUserId(Long.valueOf(userId));

		transaction.setStatusPago("CREATED");
		transaction.setFechaPago(paymentInfo.getDateCreated());
		transaction.setFechaReserva(dataPayment.getFechaReserva());
		transaction.setHorarioReserva(dataPayment.getHoraReserva());
		transaction.setCantPersonas(dataPayment.getCantPersonas());
		transaction.setMonto(dataPayment.getCosto());
		transaction.setIdInmueble(dataPayment.getIdInmueble());
		long collector_id = paymentInfo.getCollectorId().longValue();
		transaction.setCollector_id(String.valueOf(collector_id));
		transaction.setIdempotencia_id(paymentInfo.getExternalReference()); // TODO: Usado como idempotencia
		transaction.setOwnerApproval(StatusOwnerApproval.CREATED.name());
		transaction.setOwnerUserId(ownerUserId);

		transactionRepository.registerTransaction(transaction);

		response.setCode("200");
		response.setUrlDirPago(paymentInfo.getInitPoint());
		response.setMessage("re-dirigir");

		return response;
	}

	@Override
	public void statusChangeMPService(StatusChangePaymentMP statusChangeInfo) {

		if (statusChangeInfo.getTopic().equals("payment")) {

			ResponseCollectionMP dataPaymentInfo = paymentsRepository.dataPaymentMP(statusChangeInfo.getResource());

			int result = 0;

			result = transactionRepository.updateTransaction(dataPaymentInfo);

		}

	}

	@Override
	public BookingResponseDTO getBookingsService(String userId) {

		BookingResponseDTO response = new BookingResponseDTO();

		List<Transacciones> bookings = new ArrayList<>();
		bookings = transactionRepository.getBookingByUserId(userId);

		if (bookings.isEmpty()) {
			response.setCode("200");
			response.setMessage("usuario sin reservaciones");
			return response;
		}

		response.setBookings(bookings);
		response.setCode("200");
		response.setMessage("se recuperaron las reservaciones con exito");

		return response;
	}
	
	@Override
	public void statusChangeApprovalService(String paymentId, StatusOwnerApproval status) {
			transactionRepository.updateApproval(paymentId, status);
	}

	@Override
	public BookingResponseDTO getBookingsOwnerUserIdService(String userId) {
		BookingResponseDTO response = new BookingResponseDTO();

		List<Transacciones> bookings = new ArrayList<>();
		bookings = transactionRepository.getBookingByOwnerUserId(userId);

		if (bookings.isEmpty()) {
			response.setCode("200");
			response.setMessage("usuario sin reservaciones");
			return response;
		}

		response.setBookings(bookings);
		response.setCode("200");
		response.setMessage("se recuperaron las reservaciones con exito");

		return response;
	}

}

package ar.com.eventsocial.backend.service.contract;

import com.mercadopago.exceptions.MPException;

import ar.com.eventsocial.backend.dto.BookingResponseDTO;
import ar.com.eventsocial.backend.dto.PaymentResponseDTO;
import ar.com.eventsocial.backend.model.MercadoPagoRequest;
import ar.com.eventsocial.backend.model.StatusChangePaymentMP;
import ar.com.eventsocial.backend.service.PaymentsService.StatusOwnerApproval;

public interface IPagoService {

	PaymentResponseDTO paymentsMPService(MercadoPagoRequest dataPayment, String userId, String email) throws MPException;

	BookingResponseDTO getBookingsService(String userId);
	
	BookingResponseDTO getBookingsOwnerUserIdService(String userId);
	
	void statusChangeMPService(StatusChangePaymentMP statusChangeInfo);
	
	void statusChangeApprovalService(String paymentId, StatusOwnerApproval status);
	
}
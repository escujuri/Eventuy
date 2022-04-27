package ar.com.eventsocial.backend.repository.contract;

import java.util.List;

import ar.com.eventsocial.backend.model.ResponseCollectionMP;
import ar.com.eventsocial.backend.model.Transacciones;
import ar.com.eventsocial.backend.service.PaymentsService.StatusOwnerApproval;

public interface ITransactionRepository {

	void registerTransaction(Transacciones transaction);

	int updateTransaction(ResponseCollectionMP dataPaymentInfo);
	
	void updateApproval(String paymentId, StatusOwnerApproval status);

	List<Transacciones> getBookingByUserId(String userId);
	
	List<Transacciones> getBookingByOwnerUserId(String userId);
	
	List<Transacciones> getBookingForChatByUserId(String userId);

	List<Transacciones> getBookingByInmuebleId(String inmuebleId);
}

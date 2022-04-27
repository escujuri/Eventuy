package ar.com.eventsocial.backend.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.eventsocial.backend.model.GenericRepository;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.ResponseCollectionMP;
import ar.com.eventsocial.backend.model.Transacciones;
import ar.com.eventsocial.backend.repository.contract.ITransactionRepository;
import ar.com.eventsocial.backend.service.PaymentsService.StatusOwnerApproval;

@Repository("TransactionRepository")
@Transactional("sqlTransactionManagerApp")
public class MercadoPagoRepository extends GenericRepository<Long> implements ITransactionRepository {

	@Qualifier("sqlEntityManagerApp")
	@PersistenceContext(name = "sqlEntityManagerApp", unitName = "sqlUnitName")
	private EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void registerTransaction(Transacciones transaction) {
		Session session = em.unwrap(Session.class);

		try {

			session.save(transaction);

		} catch (Throwable throwable) {
			throw new RuntimeException(throwable);

		} finally {
			session.close();
		}
	}

	@Override
	public int updateTransaction(ResponseCollectionMP dataPaymentInfo) {
		try {

			Query query = em.createNamedQuery(Transacciones.UPDATE_PAYMENT);
			query.setParameter(1, dataPaymentInfo.getCollection().getStatus());
			query.setParameter(2, dataPaymentInfo.getCollection().getPaymentType());
			query.setParameter(3, dataPaymentInfo.getCollection().getCurrency_id());
			query.setParameter(4, dataPaymentInfo.getCollection().getDate_approved());
			query.setParameter(5, dataPaymentInfo.getCollection().getNet_received_amount());
			query.setParameter(6, dataPaymentInfo.getCollection().getPaymentId());
			query.setParameter(7, dataPaymentInfo.getCollection().getPaymentType());
			query.setParameter(8, dataPaymentInfo.getCollection().getPaymentMethodId());
			query.setParameter(9, dataPaymentInfo.getCollection().getExternal_reference());

			int result = query.executeUpdate();

			return result;

		} catch (HibernateException ex) {
			throw ex;
		}

	}

	@Override
	public List<Transacciones> getBookingByUserId(String userId) {

		List<Transacciones> result = new ArrayList<>();

		Query query = em.createNamedQuery(Transacciones.SEACH_BOOKING_BY_ID_USERID);
		query.setParameter(1, userId);

		result = query.getResultList();

		return result;
	}

	@Override
	public List<Transacciones> getBookingByInmuebleId(String inmuebleId) {
		List<Transacciones> result = new ArrayList<>();

		Query query = em.createNamedQuery(Transacciones.SEACH_BOOKING_BY_ID_INMUEBLEID);
		query.setParameter(1, inmuebleId);

		result = query.getResultList();

		return result;
	}

	@Override
	public void updateApproval(String paymentId, StatusOwnerApproval status) {
		Query query = em.createNamedQuery(Transacciones.UPDATE_APPROVAL);
		query.setParameter(1, status.name().toLowerCase());
		query.setParameter(2, paymentId);
		query.executeUpdate();
		
	}

	@Override
	public List<Transacciones> getBookingByOwnerUserId(String userId) {
		List<Transacciones> result = new ArrayList<>();

		Query query = em.createNamedQuery(Transacciones.SEACH_BOOKING_BY_ID_OWNER_USER_ID);
		query.setParameter(1, userId);

		result = query.getResultList();

		return result;
	}

	@Override
	public List<Transacciones> getBookingForChatByUserId(String userId) {
		List<Transacciones> result = new ArrayList<>();

		Query query = em.createNamedQuery(Transacciones.SEACH_BOOKING_CHAT_BY_USER_ID);
		query.setParameter(1, userId);
		query.setParameter(2, userId);

		result = query.getResultList();

		return result;
	}

}

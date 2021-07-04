package ar.com.eventsocial.backend.repository_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mercadopago.MercadoPago;
import com.mercadopago.resources.Payment;
import com.mercadopago.resources.datastructures.payment.Identification;
import com.mercadopago.resources.datastructures.payment.Payer;

import ar.com.eventsocial.backend.dto.PagosResponseDTO;
import ar.com.eventsocial.backend.logs.LogMaker;
import ar.com.eventsocial.backend.model.GenericRepository;
import ar.com.eventsocial.backend.model.MercadoPagoRequest;
import ar.com.eventsocial.backend.repository.contract.IPagosRepository;


@Repository("PagosRepository")
@Transactional("sqlTransactionManagerApp")
public class PagosRepository extends GenericRepository<Long> implements IPagosRepository {

	private LogMaker logger;

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
	public String pagarMP(MercadoPagoRequest datosMP) {

		try {
			MercadoPago.SDK.setAccessToken("APP_USR-1884457176252726-052002-fafdcc5db9a7666c937e1a4967152440-140747834");

			Payment payment = new Payment();
			payment.getPaymentMethodId();
			//payment.setTransactionAmount(Float.valueOf("10.4"))
			  //     .setToken("APP_USR-1884457176252726-052002-fafdcc5db9a7666c937e1a4967152440-140747834")
			  //   .setDescription("pago test");
			 //      .setInstallments(Integer.valueOf("1"))
			//       .setPaymentMethodId("visa");

			Identification identification = new Identification();
			identification.setType("dni")
			              .setNumber("18879742"); 

			Payer payer = new Payer();
			payer.setEmail("mabreplast@hotmail.com") 
			     .setIdentification(identification);

			payment.setPayer(payer);

			payment.save();

 			System.out.println(payment.getStatus());
			
		} catch (Exception e) {
			logger.debug("PagosRepository", "pagarMP", e.getMessage());
		}
		

		
		

		return null;
	}

}

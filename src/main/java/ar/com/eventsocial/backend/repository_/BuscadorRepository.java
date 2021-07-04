package ar.com.eventsocial.backend.repository_;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.eventsocial.backend.logs.LogMaker;
import ar.com.eventsocial.backend.model.GenericRepository;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Login;
import ar.com.eventsocial.backend.repository.contract.IBuscadorRepository;

@Repository("IBuscadorRepository")
@Transactional("sqlTransactionManagerApp")
public class BuscadorRepository extends GenericRepository<Long> implements IBuscadorRepository {

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
	public List<Inmueble> getResultByWord(String word) {

		try {
			Query query = em.createNamedQuery(Inmueble.DOWN_PROPERTY_BY_IDUSER);
			
			query.setParameter(1, word);

			List _list = query.getResultList();

			if (_list != null && _list.size() > 0)
				return (List<Inmueble>) _list;

			return _list;

		} catch (Exception ex) {
			throw ex;
		}

	}



}

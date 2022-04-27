package ar.com.eventsocial.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

import ar.com.eventsocial.backend.model.GenericRepository;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.repository.contract.IBuscadorRepository;

@Repository("IBuscadorRepository")
@Transactional("sqlTransactionManagerApp")
public class BuscadorRepository extends GenericRepository<Long> implements IBuscadorRepository {

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
	public List<Inmueble> getResultByWord(String keyWord) {

		try {

			Query query = em.createNamedQuery(Inmueble.SEACH_BY_WORD);
			// query.setParameter(1, MatchMode.ANYWHERE.toMatchString(keyWord));

			/*
			 * query.setParameter(2, keyWord); query.setParameter(3, keyWord);
			 * query.setParameter(4, keyWord); query.setParameter(5, keyWord);
			 * query.setParameter(6, keyWord); query.setParameter(7, keyWord);
			 * query.setParameter(8, keyWord); query.setParameter(9, keyWord);
			 * query.setParameter(10, keyWord); query.setParameter(11, keyWord);
			 */
			// query.setParameter(2, MatchMode.ANYWHERE.toMatchString(keyWord));

			// MatchMode.ANYWHERE.toMatchString(keyWord))
			List _list = query.getResultList();

			if (_list != null && _list.size() > 0)
				return (List<Inmueble>) _list;

			return null;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Inmueble getResultByIdInmueble(String idInmueble) {
	
			Query query = em.createNamedQuery(Inmueble.SEACH_BY_ID_INMUEBLE);
			query.setParameter(1, idInmueble);

			Inmueble result = (Inmueble) query.getSingleResult();

			return result;	
	}

}

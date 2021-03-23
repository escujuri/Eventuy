package ar.com.eventsocial.backend.repository_;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.eventsocial.backend.logs.LogMaker;
import ar.com.eventsocial.backend.model.GenericRepository;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Servicios;
import ar.com.eventsocial.backend.repository.contract.IInmuebleRepository;

@Repository("InmuebleRepository")
@Transactional("sqlTransactionManagerApp")
public class InmuebleRepository extends GenericRepository<Long> implements IInmuebleRepository {

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
	public List<Inmueble> inmuebleByUserId(Long userId) {
		try {

			Query query = em.createNamedQuery(Inmueble.FIND_PROPERTY_BY_IDUSER);
			query.setParameter(1, userId);
			List _list = query.getResultList();

			if (_list != null && _list.size() > 0)
				return (List<Inmueble>) _list;

			return null;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Long guardarInmueble(Inmueble inmueble) {
		Session session = em.unwrap(Session.class);

		Long _id = null;

		try {
			if (!session.isOpen()) {
				throw new IllegalStateException("Cannot begin Transaction on closed Session/EntityManager");
			}
			if (inmueble.getId() != null && inmueble.getId() > 0) {
				session.update(inmueble);
				_id = (Long) inmueble.getId();
			} else {
				session.save(inmueble);
			}
		} catch (Throwable throwable) {
			throw new RuntimeException(throwable);

		} finally {
			session.close();
		}
		return _id;
	}

	@Override
	public Long actualizarInmueble(String userId, String inmuebleId, Inmueble inmueble, Servicios servicios) {

		Session session = em.unwrap(Session.class);

		Long inmuebleId_ = Long.valueOf(inmuebleId);

		Long _id = null;

		try {
			if (!session.isOpen()) {
				throw new IllegalStateException("Cannot begin Transaction on closed Session/EntityManager");
			}
			if (inmuebleId_ != null && inmuebleId_ > 0) {
				session.update(inmueble);
				_id = (Long) inmueble.getId();
			}

		} catch (Throwable throwable) {
			throw new RuntimeException(throwable);

		} finally {
			session.close();
		}
		return _id;
	}

	@Override
	public int bajaInmueble(String userId, String id) {
		try {

			Query query = em.createNamedQuery(Inmueble.DOWN_PROPERTY_BY_IDUSER);

			query.setParameter(1, Long.valueOf(userId));
			query.setParameter(2, Long.valueOf(id));

			int result = query.executeUpdate();

			return result;

		} catch (Exception ex) {
			throw ex;
		}
	}

}

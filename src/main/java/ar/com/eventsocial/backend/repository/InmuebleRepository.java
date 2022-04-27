package ar.com.eventsocial.backend.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.eventsocial.backend.model.Fotos;
import ar.com.eventsocial.backend.model.GenericRepository;
import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Servicios;
import ar.com.eventsocial.backend.repository.contract.IInmuebleRepository;

@Repository("InmuebleRepository")
@Transactional("sqlTransactionManagerApp")
public class InmuebleRepository extends GenericRepository<Long> implements IInmuebleRepository {

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
			return null;
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
	public Long actualizarInmueble(String userId, String inmuebleId, Inmueble inmueble) {

		Session session = em.unwrap(Session.class);

		Long inmuebleId_ = Long.valueOf(inmuebleId);

		Long _id = null;

		try {
			if (!session.isOpen()) {
				throw new IllegalStateException("Cannot begin Transaction on closed Session/EntityManager");
			}
			if (inmuebleId_ != null && inmuebleId_ > 0) {
				session.update(inmueble);
				_id = inmueble.getIdInmueble();
			}

		} catch (Throwable throwable) {
			throw new RuntimeException(throwable);

		} finally {
			session.close();
		}

		return _id;
	}

	@Override
	public int bajaInmueble(String userId, String idInmueble) {
		try {

			Query query = em.createNamedQuery(Inmueble.DOWN_PROPERTY_BY_IDUSER);
			query.setParameter(1, Long.valueOf(userId));
			query.setParameter(2, Long.valueOf(idInmueble));

			int result = query.executeUpdate();

			return result;

		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public int updateService(String inmuebleId, Servicios servicios) {
		try {

			Query query = em.createNamedQuery(Servicios.UPDATE_SERVICE_BY_ID);
			query.setParameter(1, servicios.getServicios());
			query.setParameter(2, servicios.getServiciosSeguridad());
			query.setParameter(3, inmuebleId);

			int result = query.executeUpdate();

			return result;

		} catch (Exception ex) {
			throw ex;
		}

	}

	@Override
	public int updatePhoto(String inmuebleId, Fotos fotoUrl) {
		try {

			Query query = em.createNamedQuery(Fotos.UPDATE_PHOTO_BY_ID);
			query.setParameter(1, fotoUrl.getUrlFoto());
			query.setParameter(2, inmuebleId);

			int result = query.executeUpdate();

			return result;

		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public String getOwnerUserId(int idInmueble) {
try {
			
			Query query = em.createNativeQuery("select idOriginante from Inmuebles where idInmueble =" + idInmueble);
			BigInteger ownerUserId = (BigInteger) query.getSingleResult();

			String owner = String.valueOf(ownerUserId);
			
			return owner;

			
		} catch (Exception ex) {
			throw ex;
		}
	
	}
}

package ar.com.eventsocial.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.eventsocial.backend.model.GenericRepository;
import ar.com.eventsocial.backend.model.Social;
import ar.com.eventsocial.backend.repository.contract.ISocialRepository;

@Repository("SocialRepository")
@Transactional("sqlTransactionManagerApp")
public class SocialRepository extends GenericRepository<Long> implements ISocialRepository {

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
	public Long postSocial(Social social) {
		Session session = em.unwrap(Session.class);

		Long _id = null;

		try {
			if (!session.isOpen()) {
				throw new IllegalStateException("Cannot begin Transaction on closed Session/EntityManager");
			}
			if (social.getId() != null && social.getId() > 0) {
				session.update(social);
				_id = (Long) social.getId();
			} else {
				session.save(social);
			}
		} catch (Throwable throwable) {
			throw new RuntimeException(throwable);

		} finally {
			session.close();
		}
		return _id;
	}

	@Override
	public List<Social> getSocial(String inmuebleId) {
		try {
			Query query = em.createNamedQuery(Social.FIND_COMMENT_BY_INMUEBLEID);
			query.setParameter(1, inmuebleId);

			List _list = query.getResultList();

			if (_list != null && _list.size() > 0)
				return (List<Social>) _list;

			return null;
		} catch (Exception ex) {
			return null;
		}
	}
}

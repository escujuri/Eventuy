package ar.com.eventsocial.backend.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.eventsocial.backend.model.ConsultTable;
import ar.com.eventsocial.backend.model.GenericRepository;
import ar.com.eventsocial.backend.model.Login;
import ar.com.eventsocial.backend.repository.contract.ILoginRepository;

@Repository("ILoginRepository")
@Transactional("sqlTransactionManagerApp")
public class LoginRepository extends GenericRepository<Long> implements ILoginRepository {

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
	public List<Login> logginAccess(String email) {

		try {
			Query query = em.createNamedQuery(Login.LOGIN_USER);
			query.setParameter(1, email);

			List _list = query.getResultList();

			if (_list != null && _list.size() > 0)
				return (List<Login>) _list;

			return _list;

		} catch (Exception ex) {
			throw ex;
		}

	}

	public int RegisterUser(Login login) {

		List<Login> user = null;

		Session session = em.unwrap(Session.class);

		Long _id = null;

		Query query = em.createNamedQuery(Login.LOGIN_DUPLICATE_USER);
		query.setParameter(1, login.getEmail());

		List _list = query.getResultList();

		if (_list.isEmpty()) {

			try {
				if (!session.isOpen()) {
					throw new IllegalStateException("Cannot begin Transaction on closed Session/EntityManager");
				}
				if (login.getId() != null && login.getId() > 0) {
					session.update(login);
					_id = (Long) login.getId();
				} else {
					session.save(login);
				}
			} catch (Throwable throwable) {
				throw new RuntimeException(throwable);

			} finally {
				session.close();
			}

			return 1;
		}
		return 2;
	}

	@Override
	public void postConsult(ConsultTable consult) {
		Session session = em.unwrap(Session.class);

		Long _id = null;

		try {
			session.save(consult);
		} catch (Throwable throwable) {
			throw new RuntimeException(throwable);
		} finally {
			session.close();
		}
	}

	@Override
	public String getEmailByUserId(String userId) {
		try {
			
			Query query = em.createNativeQuery("select email from Usuario_Login where id ="+userId);
			String email = (String) query.getSingleResult();

			return email;

		} catch (Exception ex) {
			throw ex;
		}
	}

}

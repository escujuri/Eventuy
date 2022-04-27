package ar.com.eventsocial.backend.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.eventsocial.backend.model.GenericRepository;
import ar.com.eventsocial.backend.model.Usuario;
import ar.com.eventsocial.backend.repository.contract.IRegisterRepository;

@Transactional("sqlTransactionManagerApp")
@Repository("IRegisterRepository")
public class RegisterRepository extends GenericRepository<Long> implements IRegisterRepository {

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
	public Usuario getUsuario(String userId) {
		try {

			Query query = em.createNamedQuery(Usuario.GET_USER_INFO);
			query.setParameter(1, userId);

			Usuario result = (Usuario) query.getSingleResult();

			return result;

		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Long postUsuario(Usuario usuario) {

		Session session = em.unwrap(Session.class);

		Long _id = null;

		try {
			if (!session.isOpen()) {
				throw new IllegalStateException("Cannot begin Transaction on closed Session/EntityManager");
			}
			if (usuario != null) {
				session.update(usuario);
				_id = usuario.getId();
			}

		} catch (Throwable throwable) {
			throw new RuntimeException(throwable);

		} finally {
			session.close();
		}

		return _id;
	}

	@Override
	public List<Usuario> getListUsuario() {
		try {

			List<Usuario> result = new ArrayList<Usuario>();
			
			Query query = em.createNamedQuery(Usuario.GET_LIST_USER_INFO);
					
			return result = query.getResultList();

		} catch (Exception ex) {
			throw ex;
		}

	}
}

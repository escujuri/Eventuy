package ar.com.eventsocial.backend.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;


@SuppressWarnings("unchecked")
public abstract class GenericRepository<T> {

	public GenericRepository() { }
	
	@Transient
	protected Long id;

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public Integer getId() {
		return this.getId();
	}

	private Class<T> persistentClass;

	public abstract void setEntityManager(EntityManager em);
	
	public abstract EntityManager getEntityManager();


	public void init(Class<?> c) {
		this.persistentClass = (Class<T>) c;
	}

	public T find(Object id) {
		return (T) getEntityManager().find(persistentClass, id);
	}

	public void remove(T e) {
		getEntityManager().remove(this.find(getId()));
	}

	@Transactional("event_social_bd")
	public void save(T e) {
		if (getId() == null) {
		   getEntityManager().persist(e);
		} else {
		   getEntityManager().merge(e);
		}
	}

	protected Query createQuery(String sql) {
		return this.getEntityManager().createQuery(sql);
	}

	public Long countObject() {
		return ((Long) createQuery(
				"SELECT COUNT(*) FROM " + persistentClass.getSimpleName())
				.getSingleResult()).longValue();
	}
	
	public List<T> findAll() {
		Query q = this.getEntityManager().createQuery("FROM " + persistentClass.getSimpleName());
		return q.getResultList();
	}
	

}

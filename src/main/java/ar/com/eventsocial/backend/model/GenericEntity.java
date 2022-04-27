package ar.com.eventsocial.backend.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


@MappedSuperclass
public abstract class GenericEntity<L> implements Serializable {
	
   private static final long serialVersionUID = 1L;
   
   @Transient
	protected L id;

	public GenericEntity() { }

	public void setId(L id) {
		this.id = id;
	}

	@Transient
	public abstract L getId();

}

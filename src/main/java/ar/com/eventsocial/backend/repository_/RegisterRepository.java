package ar.com.eventsocial.backend.repository_;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ar.com.eventsocial.backend.logs.LogMaker;
import ar.com.eventsocial.backend.model.Usuario;
import ar.com.eventsocial.backend.repository.contract.IRegisterRepository;

@Transactional
@Repository("RegisterRepository")
public class RegisterRepository implements IRegisterRepository {

	private LogMaker logger;

	@Qualifier()
	@PersistenceContext()
	private EntityManager em;
	
	
	@Override
	public Usuario getUsuario() {
		
	

		return null;
	}

}

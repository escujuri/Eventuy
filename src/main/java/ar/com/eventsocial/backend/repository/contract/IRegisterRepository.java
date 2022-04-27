package ar.com.eventsocial.backend.repository.contract;

import java.util.List;

import ar.com.eventsocial.backend.model.Usuario;

public interface IRegisterRepository {

	Usuario getUsuario(String userId);
	
	List<Usuario> getListUsuario();

	Long postUsuario(Usuario user);

}

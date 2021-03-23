package ar.com.eventsocial.backend.repository.contract;

import java.util.List;

import ar.com.eventsocial.backend.model.Login;

public interface ILoginRepository {

	List<Login> logginAccess(String username);
	
	int RegisterUser(Login login);
	
}

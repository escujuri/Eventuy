package ar.com.eventsocial.backend.repository.contract;

import java.util.List;

import ar.com.eventsocial.backend.model.ConsultTable;
import ar.com.eventsocial.backend.model.Login;

public interface ILoginRepository {

	List<Login> logginAccess(String email);
	
	int RegisterUser(Login login);
	
	void postConsult(ConsultTable consult);
	
	String getEmailByUserId(String userId);
}

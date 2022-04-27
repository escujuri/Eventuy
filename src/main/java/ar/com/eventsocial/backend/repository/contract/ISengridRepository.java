package ar.com.eventsocial.backend.repository.contract;

import java.io.IOException;

import ar.com.eventsocial.backend.model.Consult;

public interface ISengridRepository {

	void postSendEmail(String email) throws IOException;
	
	void postSendEmailConsult(Consult consultRequest) throws IOException;
	
}

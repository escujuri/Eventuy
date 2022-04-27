package ar.com.eventsocial.backend.repository.contract;

import java.util.List;

import ar.com.eventsocial.backend.model.Social;

public interface ISocialRepository {

	List<Social> getSocial(String inmuebleId);

	Long postSocial(Social social);

}

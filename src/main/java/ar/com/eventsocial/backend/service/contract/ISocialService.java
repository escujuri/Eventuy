package ar.com.eventsocial.backend.service.contract;

import ar.com.eventsocial.backend.dto.SocialResponseDTO;
import ar.com.eventsocial.backend.model.SocialDto;

public interface ISocialService {

	SocialResponseDTO PostRankingInmueble(SocialDto social, String userId); 	
	
}

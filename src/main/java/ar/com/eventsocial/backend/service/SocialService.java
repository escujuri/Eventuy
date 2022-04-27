package ar.com.eventsocial.backend.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.eventsocial.backend.dto.SocialResponseDTO;
import ar.com.eventsocial.backend.model.Social;
import ar.com.eventsocial.backend.model.SocialDto;
import ar.com.eventsocial.backend.model.Usuario;
import ar.com.eventsocial.backend.repository.contract.IRegisterRepository;
import ar.com.eventsocial.backend.repository.contract.ISocialRepository;
import ar.com.eventsocial.backend.repository.contract.ITransactionRepository;
import ar.com.eventsocial.backend.service.contract.ISocialService;

@Service("ISocialService")
public class SocialService implements ISocialService {

	@Autowired
	ISocialRepository socialRepository;

	@Autowired
	ITransactionRepository transactionRepository;
	
	@Override
	public SocialResponseDTO PostRankingInmueble(SocialDto socialDto, String userId) {		
		
		SocialResponseDTO response = new SocialResponseDTO();
		
		Social social = new Social();
		social.setUsuario_id(userId);
		social.setComment(socialDto.getComment());
		social.setInmueble_id(socialDto.getInmuebleId());
		social.setRanking(socialDto.getRanking());
		social.setCreated_at(new Date());
	
		socialRepository.postSocial(social);
	
		response.setCode("200");
		response.setMessage("Se guardo correctamente");

		return response;
	}
}

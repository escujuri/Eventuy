package ar.com.eventsocial.backend.service.contract;

import ar.com.eventsocial.backend.dto.UserInfoResponseDTO;
import ar.com.eventsocial.backend.model.UserInfo;

public interface IUserService {

	UserInfoResponseDTO getInfoUser(String userId);
	
	UserInfoResponseDTO getListInfoUser(String userId);
	
	UserInfoResponseDTO postInfoUser(String userId, UserInfo userInfo);
	
}

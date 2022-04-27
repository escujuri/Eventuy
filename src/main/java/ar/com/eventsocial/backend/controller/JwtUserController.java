package ar.com.eventsocial.backend.controller;

import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.eventsocial.backend.dto.SocialResponseDTO;
import ar.com.eventsocial.backend.dto.UserInfoResponseDTO;
import ar.com.eventsocial.backend.model.GenericUtils;
import ar.com.eventsocial.backend.model.MercadoPagoRequest;
import ar.com.eventsocial.backend.model.SocialDto;
import ar.com.eventsocial.backend.model.UserInfo;
import ar.com.eventsocial.backend.security.jwt.ITokenExtractor;
import ar.com.eventsocial.backend.security.jwt.JwtTokenUtil;
import ar.com.eventsocial.backend.service.contract.ISocialService;
import ar.com.eventsocial.backend.service.contract.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@CrossOrigin
@RestController
@RequestMapping(value = "v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "", tags = "Manejo de informacion de usuarios", protocols = "https")
public class JwtUserController {

	@Autowired
	private IUserService userService;
	
	@Autowired
	@Qualifier("jwtHeaderTokenExtractor")
	private ITokenExtractor tokenExtractor;

	@CrossOrigin
	@RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Recurso que recupera la informacion del usuario")
	@ResponseBody
	public ResponseEntity<UserInfoResponseDTO> getInfoUser(HttpServletRequest request) throws Exception {

		String userId = "";
		String email = "";

		String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
		Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

		if (claims != null && !claims.isEmpty()) {
			if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
				userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
			}
			if (GenericUtils.getValueFromKeyValue(claims, "email") != null) {
				email = GenericUtils.getValueFromKeyValue(claims, "email").toString();
			}
		}
	
		try {
			final UserInfoResponseDTO  _response = userService.getInfoUser(userId);
			return new ResponseEntity<UserInfoResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			UserInfoResponseDTO _response = new UserInfoResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<UserInfoResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/info", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Recurso que escribe comentarios en inmuebles")
	@ResponseBody
	public ResponseEntity<UserInfoResponseDTO> postInfoUser(HttpServletRequest request,
			@RequestBody(required = true) @Validated UserInfo userInfo) throws Exception {

		String userId = "";
		String email = "";

		String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
		Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

		if (claims != null && !claims.isEmpty()) {
			if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
				userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
			}
			if (GenericUtils.getValueFromKeyValue(claims, "email") != null) {
				email = GenericUtils.getValueFromKeyValue(claims, "email").toString();
			}
		}
	
		try {
			final UserInfoResponseDTO  _response = userService.postInfoUser(userId, userInfo);
			return new ResponseEntity<UserInfoResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			UserInfoResponseDTO _response = new UserInfoResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<UserInfoResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/info/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Recurso que recupera la informacion de un usuario")
	@ResponseBody
	public ResponseEntity<UserInfoResponseDTO> getInfoUser(HttpServletRequest request,
			@PathVariable("userId") String userId) throws Exception {

		try {
			final UserInfoResponseDTO  _response = userService.getInfoUser(userId);
			return new ResponseEntity<UserInfoResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			UserInfoResponseDTO _response = new UserInfoResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<UserInfoResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}
	}
	
	@CrossOrigin
	@RequestMapping(value = "/info/chat/list", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Recurso que recupera la informacion de un usuario")
	@ResponseBody
	public ResponseEntity<UserInfoResponseDTO> getListInfoUser(HttpServletRequest request) throws Exception {

		try {
			String userId = "";
			String email = "";

			String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
			Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

			if (claims != null && !claims.isEmpty()) {
				if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
					userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
				}
				if (GenericUtils.getValueFromKeyValue(claims, "email") != null) {
					email = GenericUtils.getValueFromKeyValue(claims, "email").toString();
				}
			}
			
			final UserInfoResponseDTO  _response = userService.getListInfoUser(userId);
			return new ResponseEntity<UserInfoResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			UserInfoResponseDTO _response = new UserInfoResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<UserInfoResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}
	}
}
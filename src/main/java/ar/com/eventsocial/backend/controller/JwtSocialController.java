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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.eventsocial.backend.dto.SocialResponseDTO;
import ar.com.eventsocial.backend.model.GenericUtils;
import ar.com.eventsocial.backend.model.SocialDto;
import ar.com.eventsocial.backend.security.jwt.ITokenExtractor;
import ar.com.eventsocial.backend.security.jwt.JwtTokenUtil;
import ar.com.eventsocial.backend.service.contract.ISocialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "v1/social", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "", tags = "Manaje toda la parte social y ranking", protocols = "https")
public class JwtSocialController {

	@Autowired
	private ISocialService socialService;
	
	@Autowired
	@Qualifier("jwtHeaderTokenExtractor")
	private ITokenExtractor tokenExtractor;

	@CrossOrigin
	@RequestMapping(value = "/ranking", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Recurso que escribe comentarios en inmuebles")
	@ResponseBody
	public ResponseEntity<SocialResponseDTO> PostRankingInmueble(HttpServletRequest request,
			@RequestBody(required = true) @Validated SocialDto social) throws Exception {

		Objects.requireNonNull(social.getComment());
		Objects.requireNonNull(social.getInmuebleId());
		Objects.requireNonNull(social.getRanking());
		
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
			final SocialResponseDTO _response = socialService.PostRankingInmueble(social, userId);
			return new ResponseEntity<SocialResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			SocialResponseDTO _response = new SocialResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<SocialResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}
	}

}

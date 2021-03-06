package ar.com.eventsocial.backend.controller;

import java.net.InetAddress;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.model.Consult;
import ar.com.eventsocial.backend.security.jwt.JwtRequestStep2;
import ar.com.eventsocial.backend.service.contract.ILoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "v1/login", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "", tags = "Autenticacion de usuarios registrados", protocols = "https")
public class JwtLoginControllerV1 {

	@Autowired
	private ILoginService loginService;

	@CrossOrigin
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "")
	@ResponseBody
	public ResponseEntity<RegisterResponseDTO> RegisterStep1(HttpServletRequest request,
			@RequestBody(required = true) @Validated JwtRequestStep2 authenticationRequest) throws Exception {

		Objects.requireNonNull(authenticationRequest.getEmail());
		Objects.requireNonNull(authenticationRequest.getPassword());

		String ip;

		try {
			ip = getClientIP(request);
		} catch (Exception e) {
			ip = InetAddress.getLocalHost().toString();
		}

		final RegisterResponseDTO _response = loginService.authenticate(authenticationRequest.getEmail(),
				authenticationRequest.getPassword(), ip);

		return new ResponseEntity<RegisterResponseDTO>(_response, HttpStatus.OK);

	}
	@CrossOrigin
	@RequestMapping(value = "/consult", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "")
	@ResponseBody
	public ResponseEntity<RegisterResponseDTO> postConsult(HttpServletRequest request,
			@RequestBody(required = true) @Validated Consult consultRequest) throws Exception {

		Objects.requireNonNull(consultRequest.getAsunto());
		Objects.requireNonNull(consultRequest.getEmail());
		Objects.requireNonNull(consultRequest.getMensaje());
		Objects.requireNonNull(consultRequest.getNombre());

		String ip;

		try {
			ip = getClientIP(request);
		} catch (Exception e) {
			ip = InetAddress.getLocalHost().toString();
		}

		final RegisterResponseDTO _response = loginService.postConsult(consultRequest, ip);

		return new ResponseEntity<RegisterResponseDTO>(_response, HttpStatus.OK);

	}

	private String getClientIP(HttpServletRequest request) {
		final String xfHeader = request.getHeader("X-Forwarded-For");
		if (xfHeader == null) {
			return request.getRemoteAddr();
		}
		return xfHeader.split(",")[0];
	}
	
}

package ar.com.eventsocial.backend.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.logs.LogMaker;
import ar.com.eventsocial.backend.security.jwt.JwtRequestStep1;
import ar.com.eventsocial.backend.service.contract.IRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "v1/register", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "", tags = "Registro de datos personales de los usuarios", protocols = "https")
public class RegisterControllerV1 {

	@Autowired
	private IRegisterService registerService;

	public static final LogMaker log = new LogMaker();

	@RequestMapping(value = "/escribir/datos-personales", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Recurso para escribir todos los datos personales. Retorno status 200 y json en Body response.")
	@ResponseBody
	public ResponseEntity<RegisterResponseDTO> RegisterStep1(HttpServletRequest request,
			@RequestBody(required = true) @Validated JwtRequestStep1 jwtRequestStep1) throws Exception {

		Objects.requireNonNull(jwtRequestStep1.getUsername());
		Objects.requireNonNull(jwtRequestStep1.getPassword());
		Objects.requireNonNull(jwtRequestStep1.getEmail());
		
		log.infoRecibe("datos/escribir", null);

		final RegisterResponseDTO _response = registerService.Step1(jwtRequestStep1.getUsername(),jwtRequestStep1.getPassword(),jwtRequestStep1.getEmail());

		return new ResponseEntity<RegisterResponseDTO>(_response, HttpStatus.OK);
	}

	@RequestMapping(value = "/recuperar/datos-personales", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Recurso para escribir todos los datos personales. Retorno status 200 y json en Body response.")
	@ResponseBody
	public ResponseEntity<RegisterResponseDTO> RegisterStep1(HttpServletRequest request) throws Exception {

		log.infoRecibe("datos/recuperar", null);

		final RegisterResponseDTO _response = registerService.Step1(null,null,null);

		return new ResponseEntity<RegisterResponseDTO>(_response, HttpStatus.OK);
	}

}

package ar.com.eventsocial.backend.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.eventsocial.backend.dto.InmuebleResponseDTO;
import ar.com.eventsocial.backend.model.GenericUtils;
import ar.com.eventsocial.backend.model.Inmuebles;
import ar.com.eventsocial.backend.security.jwt.ITokenExtractor;
import ar.com.eventsocial.backend.security.jwt.JwtTokenUtil;
import ar.com.eventsocial.backend.service.contract.IInmubleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@PreAuthorize("authenticated")
@CrossOrigin
@RestController
@RequestMapping(value = "v1/inmuebles", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "", tags = "Estos metodos realizan un ABM de los inmuebles", protocols = "https")
public class JwtInmueblesControllerV1 {

	@Autowired
	private IInmubleService InmuebleService;

	@Autowired
	@Qualifier("jwtHeaderTokenExtractor")
	private ITokenExtractor tokenExtractor;

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/recuperar", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Recurso que recupera los inmuebles de un usuario")
	@ResponseBody
	public ResponseEntity<InmuebleResponseDTO> RecuperarInmueble(HttpServletRequest request) throws Exception {

		try {

			String userId = "";
			String usuario = "";

			String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
			Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

			if (claims != null && !claims.isEmpty()) {
				if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
					userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
				}
				if (GenericUtils.getValueFromKeyValue(claims, "usuario") != null) {
					usuario = GenericUtils.getValueFromKeyValue(claims, "usuario").toString();
				}
			}

			final InmuebleResponseDTO _response = InmuebleService.getinmuebleByIdOriginante(usuario,
					Long.valueOf(userId));

			return new ResponseEntity<InmuebleResponseDTO>(_response, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/guardar", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Recurso que guarda los inmuebles de un usuario")
	@ResponseBody
	public ResponseEntity<InmuebleResponseDTO> GuardarInmueble(HttpServletRequest request,
			@RequestBody(required = true) @Validated Inmuebles datosInmueble) throws Exception {

		String userId = "";
		String usuario = "";

		String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
		Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

		if (claims != null && !claims.isEmpty()) {
			if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
				userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
			}
			if (GenericUtils.getValueFromKeyValue(claims, "usuario") != null) {
				usuario = GenericUtils.getValueFromKeyValue(claims, "usuario").toString();
			}
		}

		try {
			final InmuebleResponseDTO _response = InmuebleService.saveByIdOriginante(usuario, userId, datosInmueble);
			return new ResponseEntity<InmuebleResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			InmuebleResponseDTO _response = new InmuebleResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<InmuebleResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/actualizar/{idInmueble}", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Recurso que actualiza un inmuebles y servicios  de un usuario")
	@ResponseBody
	public ResponseEntity<InmuebleResponseDTO> ActualizarInmueble(HttpServletRequest request,
			@RequestBody(required = true) @Validated Inmuebles datosInmueble,
			@PathVariable("idInmueble") String idInmueble) throws Exception {

		String userId = "";
		String usuario = "";

		String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
		Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

		if (claims != null && !claims.isEmpty()) {
			if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
				userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
			}
			if (GenericUtils.getValueFromKeyValue(claims, "usuario") != null) {
				usuario = GenericUtils.getValueFromKeyValue(claims, "usuario").toString();
			}
		}

		try {
			final InmuebleResponseDTO _response = InmuebleService.actualizarByIdOriginante(usuario, userId, idInmueble,
					datosInmueble);
			return new ResponseEntity<InmuebleResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			InmuebleResponseDTO _response = new InmuebleResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<InmuebleResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}

	}

	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/baja/{idInmueble}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Recurso que recupera los inmuebles de un usuario")
	@ResponseBody
	public ResponseEntity<InmuebleResponseDTO> BajaInmueble(HttpServletRequest request,
			@PathVariable("idInmueble") String idInmueble) throws Exception {

		try {

			String userId = "";
			String usuario = "";

			String tokenPayload = tokenExtractor.Extract(request.getHeader(JwtTokenUtil.TOKEN_HEADER));
			Map<String, Object> claims = tokenExtractor.ReadToken(tokenPayload);

			if (claims != null && !claims.isEmpty()) {
				if (GenericUtils.getValueFromKeyValue(claims, "userId") != null) {
					userId = GenericUtils.getValueFromKeyValue(claims, "userId").toString();
				}
				if (GenericUtils.getValueFromKeyValue(claims, "usuario") != null) {
					usuario = GenericUtils.getValueFromKeyValue(claims, "usuario").toString();
				}
			}

			final InmuebleResponseDTO _response = InmuebleService.deleteByIdOriginante(userId, idInmueble);

			return new ResponseEntity<InmuebleResponseDTO>(_response, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}

	}

}

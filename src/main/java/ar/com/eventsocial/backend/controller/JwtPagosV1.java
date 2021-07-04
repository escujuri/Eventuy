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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.eventsocial.backend.dto.InmuebleResponseDTO;
import ar.com.eventsocial.backend.dto.PagosResponseDTO;
import ar.com.eventsocial.backend.logs.LogMaker;
import ar.com.eventsocial.backend.model.GenericUtils;
import ar.com.eventsocial.backend.model.MercadoPagoRequest;
import ar.com.eventsocial.backend.security.jwt.ITokenExtractor;
import ar.com.eventsocial.backend.security.jwt.JwtTokenUtil;
import ar.com.eventsocial.backend.service.contract.IPagoService;
import ar.com.eventsocial.backend.service_.InmuebleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@PreAuthorize("authenticated")
@CrossOrigin
@RestController
@RequestMapping(value = "v1/cobranzas", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "", tags = "Estos metodos realizan un ABM de los inmuebles", protocols = "https")
public class JwtPagosV1 {

	@Autowired
	private IPagoService IpagoService;

	@Autowired
	@Qualifier("jwtHeaderTokenExtractor")
	private ITokenExtractor tokenExtractor;

	public static final LogMaker log = new LogMaker();


	@PreAuthorize("hasRole('USER')")
	@RequestMapping(value = "/pagarMP", method = RequestMethod.POST, produces = "application/json")
	@ApiOperation(value = "Se realiza el pago via mercadolibre devolviendo")
	@ResponseBody
	public ResponseEntity<PagosResponseDTO> RealizarPago(HttpServletRequest request,
			@RequestBody(required = true) @Validated MercadoPagoRequest datosMP) throws Exception {

		log.infoRecibe("inmueble/guardar", null);

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
			final PagosResponseDTO _response = IpagoService.pagarMP(datosMP);
			return new ResponseEntity<PagosResponseDTO>(_response, HttpStatus.OK);

		} catch (Exception e) {
			PagosResponseDTO _response = new PagosResponseDTO();
			_response.setCode("400");
			_response.setMessage(e.getMessage());
			return new ResponseEntity<PagosResponseDTO>(_response, HttpStatus.BAD_REQUEST);
		}

	}

}

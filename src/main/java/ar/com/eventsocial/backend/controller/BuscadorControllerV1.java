package ar.com.eventsocial.backend.controller;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.eventsocial.backend.dto.InmuebleReducidoResponseDTO;
import ar.com.eventsocial.backend.dto.InmuebleResponseDTO;
import ar.com.eventsocial.backend.service.contract.IBuscadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "v1/search", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "", tags = "Devuelve busquedas ramdon por url", protocols = "https")
public class BuscadorControllerV1 {

	@Autowired
	private IBuscadorService buscadorService;

	@RequestMapping(value = "/keyword/{keyWord}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Busca inmuebles por palabra clave. Retorno status 200 y json en Body response.")
	@ResponseBody
	public ResponseEntity<InmuebleReducidoResponseDTO> seachKeyWord(HttpServletRequest request,
			@PathVariable("keyWord") String keyWord) throws Exception {

		Objects.requireNonNull(keyWord);

		final InmuebleReducidoResponseDTO response = buscadorService.BuscadorByWord(keyWord);

		return new ResponseEntity<InmuebleReducidoResponseDTO>(response, HttpStatus.OK);
	}

	@RequestMapping(value = "/IdInmueble/{IdInmueble}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Recuperar un inmuble por su Id. Retorno status 200 y json en Body response.")
	@ResponseBody
	public ResponseEntity<InmuebleResponseDTO> seachInmuebleId(HttpServletRequest request,
			@PathVariable("IdInmueble") String idInmueble) throws Exception {

		Objects.requireNonNull(idInmueble);

		final InmuebleResponseDTO response = buscadorService.BuscadorByInmuebleId(idInmueble);

		return new ResponseEntity<InmuebleResponseDTO>(response, HttpStatus.OK);
	}

}

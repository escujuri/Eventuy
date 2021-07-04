package ar.com.eventsocial.backend.controller;

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

import ar.com.eventsocial.backend.dto.RegisterResponseDTO;
import ar.com.eventsocial.backend.logs.LogMaker;
import ar.com.eventsocial.backend.service.contract.IBuscadorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin
@RestController
@RequestMapping(value = "v1/seach", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
@Validated
@Api(value = "", tags = "Devuelve busquedas ramdon por url", protocols = "https")
public class BuscadorControllerV1 {

	@Autowired
	private IBuscadorService buscadorService;

	public static final LogMaker log = new LogMaker();

	@RequestMapping(value = "/{word}", method = RequestMethod.GET, produces = "application/json")
	@ApiOperation(value = "Recurso para escribir todos los datos personales. Retorno status 200 y json en Body response.")
	@ResponseBody
	public ResponseEntity<RegisterResponseDTO> RegisterStep1(HttpServletRequest request,
			@PathVariable("word") String word
			) throws Exception {

		log.infoRecibe("seach", null);

		final RegisterResponseDTO _response = buscadorService.BuscadorByWord(word);

		return new ResponseEntity<RegisterResponseDTO>(_response, HttpStatus.OK);
	}

}

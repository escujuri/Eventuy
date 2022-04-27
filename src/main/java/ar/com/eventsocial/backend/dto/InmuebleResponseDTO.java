package ar.com.eventsocial.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ar.com.eventsocial.backend.model.Inmueble;
import ar.com.eventsocial.backend.model.Inmuebles;
import ar.com.eventsocial.backend.model.InmueblesReducido;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message" })
public class InmuebleResponseDTO {

	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("inmuebles")
	private List<Inmuebles> inmuebles;
	@JsonProperty("inmueble")
	private Inmuebles inmueble;

	/*---- GETTER SETTER----*/

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Inmuebles> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<Inmuebles> inmuebles) {
		this.inmuebles = inmuebles;
	}

	public Inmuebles getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmuebles inmueble) {
		this.inmueble = inmueble;
	}
}

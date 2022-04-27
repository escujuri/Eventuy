package ar.com.eventsocial.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ar.com.eventsocial.backend.model.InmueblesReducido;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message" })
public class InmuebleReducidoResponseDTO {

	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("inmuebles")
	private List<InmueblesReducido> inmuebles;
	
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

	public List<InmueblesReducido> getInmuebles() {
		return inmuebles;
	}

	public void setInmuebles(List<InmueblesReducido> inmuebles) {
		this.inmuebles = inmuebles;
	}

}

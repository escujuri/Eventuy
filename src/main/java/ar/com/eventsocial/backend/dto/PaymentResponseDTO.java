package ar.com.eventsocial.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message" })
public class PaymentResponseDTO {

	@JsonProperty("code")
	private String code;
	@JsonProperty("urlDirPago")
	private String urlDirPago;
	@JsonProperty("message")
	private String message;
	@JsonProperty("EstadoPago")
	private String estadoPago;

	
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

	public String getEstadoPago() {
		return estadoPago;
	}

	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}

	public String getUrlDirPago() {
		return urlDirPago;
	}

	public void setUrlDirPago(String urlDirPago) {
		this.urlDirPago = urlDirPago;
	}

}

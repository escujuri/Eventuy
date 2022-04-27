package ar.com.eventsocial.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ar.com.eventsocial.backend.model.Transacciones;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message" })
public class BookingResponseDTO {

	@JsonProperty("code")
	private String code;
	@JsonProperty("reservas")
	private List<Transacciones> bookings;
	@JsonProperty("message")
	private String message;

	
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

	public List<Transacciones> getBookings() {
		return bookings;
	}

	public void setBookings(List<Transacciones> bookings) {
		this.bookings = bookings;
	}

}

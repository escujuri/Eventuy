package ar.com.eventsocial.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MercadoPagoRequest {

	@JsonProperty("token")
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}

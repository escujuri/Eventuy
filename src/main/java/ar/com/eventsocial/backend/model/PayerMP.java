package ar.com.eventsocial.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PayerMP {

	@JsonProperty("id")
	private String id;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("identification")
	private IdentificationMP identification;
	
	@JsonProperty("type")
	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public IdentificationMP getIdentification() {
		return identification;
	}

	public void setIdentification(IdentificationMP identification) {
		this.identification = identification;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}

package ar.com.eventsocial.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IdentificationMP {

	@JsonProperty("type")
	private String type;

	@JsonProperty("number")
	private String number;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}

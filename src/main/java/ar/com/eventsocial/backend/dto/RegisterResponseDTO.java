package ar.com.eventsocial.backend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ar.com.eventsocial.backend.model.Usuario;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message" })
public class RegisterResponseDTO {

	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("userCase")
	private String userCaseId;
	@JsonProperty("sessionID")
	private String sessionEnc;


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

	public String getUserCaseId() {
		return userCaseId;
	}

	public void setUserCaseId(String userCaseId) {
		this.userCaseId = userCaseId;
	}

	public String getSessionEnc() {
		return sessionEnc;
	}

	public void setSessionEnc(String sessionEnc) {
		this.sessionEnc = sessionEnc;
	}


}

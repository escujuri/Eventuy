package ar.com.eventsocial.backend.security.jwt;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;

public class JwtRequestStep2  implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	@ApiModelProperty(required=true, value="String correspondiente al email del usuario")
	private String email;
	@ApiModelProperty(required=true, value="String correspondiente a la password del usuario")
	private String password;

	@JsonCreator
    public JwtRequestStep2(
    		@JsonProperty("email") String email, 
    		@JsonProperty("password") String password) {
		this.setEmail(email);
		this.setPassword(password);
    }
	
	@JsonProperty(value = "email", access = Access.READ_ONLY)
	public String getEmail() {
		return this.email;
	}
	@JsonProperty(value = "email", access = Access.WRITE_ONLY)
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty(value = "password", access = Access.READ_ONLY)
	public String getPassword() {
		return this.password;
	}
	@JsonProperty(value = "password", access = Access.WRITE_ONLY)
	public void setPassword(String password) {
		this.password = password;
	}
}

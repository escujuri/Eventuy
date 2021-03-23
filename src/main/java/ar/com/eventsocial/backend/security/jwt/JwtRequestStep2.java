package ar.com.eventsocial.backend.security.jwt;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import io.swagger.annotations.ApiModelProperty;

public class JwtRequestStep2  implements Serializable {

	private static final long serialVersionUID = 5926468583005150707L;
	
	@ApiModelProperty(required=true, value="String correspondiente al username del usuario")
	private String username;
	@ApiModelProperty(required=true, value="String correspondiente a la password del usuario")
	private String password;

	@JsonCreator
    public JwtRequestStep2(
    		@JsonProperty("username") String username, 
    		@JsonProperty("password") String password) {
		this.setUsername(username);
		this.setPassword(password);
    }
	
	@JsonProperty(value = "username", access = Access.READ_ONLY)
	public String getUsername() {
		return this.username;
	}
	@JsonProperty(value = "username", access = Access.WRITE_ONLY)
	public void setUsername(String username) {
		this.username = username;
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

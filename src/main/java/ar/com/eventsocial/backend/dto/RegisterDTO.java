package ar.com.eventsocial.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterDTO {

	@JsonProperty("username")
	private static String username;
	@JsonProperty("password")
	private static String password;
	@JsonProperty("email")
	private static String email;
	
	/*---- GETTER SETTER----*/

	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		RegisterDTO.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		RegisterDTO.password = password;
	}
	public static String getEmail() {
		return email;
	}
	public static void setEmail(String email) {
		RegisterDTO.email = email;
	}

}

package ar.com.eventsocial.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Consult {

	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("email")
	private String email;
	
	@JsonProperty("asunto")
	private String asunto;

	@JsonProperty("mensaje")
	private String mensaje;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

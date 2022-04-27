package ar.com.eventsocial.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInfo {
	
	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("edad")
	private Integer edad;

	@JsonProperty("documento")
	private String documento;
	
	@JsonProperty("telefono")
	private String telefono;
	
	@JsonProperty("urlAvatar")
	private String urlAvatar;

	@JsonProperty("fechaNacimiento")
	private Date fechaNacimiento;

	// -- GETTER AND SETTER--//
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUrlAvatar() {
		return urlAvatar;
	}

	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}

}

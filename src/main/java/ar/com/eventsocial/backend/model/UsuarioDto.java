package ar.com.eventsocial.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDto {

	@JsonProperty("id")
	private Long id;

	@JsonProperty("nombre")
	private String nombre;

	@JsonProperty("edad")
	private Integer edad;

	@JsonProperty("telefono")
	private String telefono;

	@JsonProperty("urlAvatar")
	private String urlAvatar;

	@JsonProperty("documento")
	private String documento;

	@JsonProperty("fechaNacimiento")
	private Date fechaNacimiento;

	@JsonProperty("anfitrion")
	private Boolean anfitrion;
	
	@JsonProperty("email")
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Boolean getAnfitrion() {
		return anfitrion;
	}

	public void setAnfitrion(Boolean anfitrion) {
		this.anfitrion = anfitrion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

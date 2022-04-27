package ar.com.eventsocial.backend.model;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InmueblesReducido {

	@JsonProperty("IdInmueble")
	private Long IdInmueble;

	@JsonProperty("titulo")
	private String titulo;

	@JsonProperty("tipoLugar")
	private String tipoLugar;

	@JsonProperty("ownerId")
	private String idOriginante;
	
	@JsonProperty("direccion")
	private Direccion direccion;

	@JsonProperty("descripcion")
	private String descripcion;

	@JsonProperty("precio")
	private Float precio;

	@JsonProperty("puntuacion")
	private Float puntuacion;

	@JsonProperty("fechaDisponibilidad")
	private Date fechaDisponibilidad;

	@JsonProperty("servicios")
	private List<String> servicios;

	@JsonProperty("fotos")
	private List<String> fotos;
	
	@JsonProperty("social")
	private List<SocialDto> social;

	/*----- SETTER AND GETTER-----*/

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipoLugar() {
		return tipoLugar;
	}

	public void setTipoLugar(String tipoLugar) {
		this.tipoLugar = tipoLugar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Float puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Date getFechaDisponibilidad() {
		return fechaDisponibilidad;
	}

	public void setFechaDisponibilidad(Date fechaDisponibilidad) {
		this.fechaDisponibilidad = fechaDisponibilidad;
	}

	public List<String> getServicios() {
		return servicios;
	}

	public void setServicios(List<String> servicios) {
		this.servicios = servicios;
	}

	public List<String> getFotos() {
		return fotos;
	}

	public void setFotos(List<String> fotos) {
		this.fotos = fotos;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Long getIdInmueble() {
		return IdInmueble;
	}

	public void setIdInmueble(Long idInmueble) {
		IdInmueble = idInmueble;
	}

	public String getIdOriginante() {
		return idOriginante;
	}

	public void setIdOriginante(String idOriginante) {
		this.idOriginante = idOriginante;
	}

	public List<SocialDto> getSocial() {
		return social;
	}

	public void setSocial(List<SocialDto> social) {
		this.social = social;
	}
	
}

package ar.com.eventsocial.backend.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Inmuebles {

	@JsonProperty("idInmueble")
	private Long idInmueble;

	@JsonProperty("titulo")
	private String titulo;

	@JsonProperty("tipoLugar")
	private String tipoLugar;

	@JsonProperty("direccion")
	private Direccion direccion;

	@JsonProperty("descripcion")
	private String descripcion;

	@JsonProperty("precio")
	private Float precio;

	@JsonProperty("puntuacion")
	private Float puntuacion;

	@JsonProperty("maxPersonas")
	private Integer maxPersonas;

	@JsonProperty("fechaDisponibilidad")
	private Date fechaDisponibilidad;

	@JsonProperty("servicios")
	private List<String> servicios;

	@JsonProperty("serviciosDeSeguridad")
	private List<String> serviciosDeSeguridad;

	@JsonProperty("fotos")
	private List<String> fotos;

	@JsonProperty("horariosReserva")
	private List<String> horariosReserva;

	@JsonProperty("horarios")
	private HorariosDias horariosDias;

	@JsonProperty("esPrecioFijo")
	private Boolean esPrecioFijo;

	@JsonProperty("publicada")
	private Boolean publicada;

	@JsonProperty("fechaNoDisponibles")
	private List<String> fechaNoDisponibles;

	@JsonProperty("disponibilidadPersonas")
	private Integer disponibilidadPersonas;
	
	@JsonProperty("social")
	private List<SocialDto> social;
	
	@JsonProperty("nombreContacto")
	private String nombreContacto;
	
	@JsonProperty("apellidoContacto")
	private String apellidoContacto;
	
	@JsonProperty("telefonoContacto")
	private String telefonoContacto;

	@JsonProperty("ownerId")
	private String idOriginante;

	/*----- SETTER AND GETTER-----*/

	public String getTitulo() {
		return titulo;
	}

	public String getIdOriginante() {
		return idOriginante;
	}

	public void setIdOriginante(String idOriginante) {
		this.idOriginante = idOriginante;
	}

	public Long getIdInmueble() {
		return idInmueble;
	}

	public void setIdInmueble(Long idInmueble) {
		this.idInmueble = idInmueble;
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

	public Integer getMaxPersonas() {
		return maxPersonas;
	}

	public void setMaxPersonas(Integer maxPersonas) {
		this.maxPersonas = maxPersonas;
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

	public List<String> getServiciosDeSeguridad() {
		return serviciosDeSeguridad;
	}

	public void setServiciosDeSeguridad(List<String> serviciosDeSeguridad) {
		this.serviciosDeSeguridad = serviciosDeSeguridad;
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

	public List<String> getHorariosReserva() {
		return horariosReserva;
	}

	public void setHorariosReserva(List<String> horariosReserva) {
		this.horariosReserva = horariosReserva;
	}

	public HorariosDias getHorariosDias() {
		return horariosDias;
	}

	public void setHorariosDias(HorariosDias horariosDias) {
		this.horariosDias = horariosDias;
	}

	public Boolean getEsPrecioFijo() {
		return esPrecioFijo;
	}

	public void setEsPrecioFijo(Boolean esPrecioFijo) {
		this.esPrecioFijo = esPrecioFijo;
	}

	public Boolean getPublicada() {
		return publicada;
	}

	public void setPublicada(Boolean publicada) {
		this.publicada = publicada;
	}

	public List<String> getFechaNoDisponibles() {
		return fechaNoDisponibles;
	}

	public void setFechaNoDisponibles(List<String> fechaNoDisponibles) {
		this.fechaNoDisponibles = fechaNoDisponibles;
	}

	public Integer getDisponibilidadPersonas() {
		return disponibilidadPersonas;
	}

	public void setDisponibilidadPersonas(Integer disponibilidadPersonas) {
		this.disponibilidadPersonas = disponibilidadPersonas;
	}

	public List<SocialDto> getSocial() {
		return social;
	}

	public void setSocial(List<SocialDto> social) {
		this.social = social;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getApellidoContacto() {
		return apellidoContacto;
	}

	public void setApellidoContacto(String apellidoContacto) {
		this.apellidoContacto = apellidoContacto;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

}

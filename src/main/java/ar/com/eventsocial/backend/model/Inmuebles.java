package ar.com.eventsocial.backend.model;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Inmuebles {

	@JsonProperty("titulo")
	private String titulo;

	@JsonProperty("tipoLugar")
	private String tipoLugar;

	@JsonProperty("ubicacion")
	private String ubicacion;

	@JsonProperty("calle")
	private String calle;

	@JsonProperty("altura")
	private Integer altura;

	@JsonProperty("localidad")
	private String localidad;

	@JsonProperty("ciudad")
	private String ciudad;

	@JsonProperty("provincia")
	private String provincia;

	@JsonProperty("pais")
	private String pais;

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

	@JsonProperty("piscina")
	private Boolean piscina;

	@JsonProperty("quincho")
	private Boolean quincho;

	@JsonProperty("parrilla")
	private Boolean parrilla;

	@JsonProperty("mascotas")
	private Boolean mascotas;
	
	@JsonProperty("wifi")
	private Boolean wifi;

	@JsonProperty("catering")
	private Boolean catering;
	
	@JsonProperty("dj")
	private Boolean dj;

	@JsonProperty("file")
	private MultipartFile multipartFile;

	/*----- SETTER AND GETTER-----*/
	public String getTitulo() {
		return titulo;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
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

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
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

	public Boolean getPiscina() {
		return piscina;
	}

	public void setPiscina(Boolean piscina) {
		this.piscina = piscina;
	}

	public Boolean getQuincho() {
		return quincho;
	}

	public void setQuincho(Boolean quincho) {
		this.quincho = quincho;
	}

	public Boolean getParrilla() {
		return parrilla;
	}

	public void setParrilla(Boolean parrilla) {
		this.parrilla = parrilla;
	}

	public Boolean getMascotas() {
		return mascotas;
	}

	public void setMascotas(Boolean mascotas) {
		this.mascotas = mascotas;
	}

	public Boolean getWifi() {
		return wifi;
	}

	public void setWifi(Boolean wifi) {
		this.wifi = wifi;
	}

	public Boolean getCatering() {
		return catering;
	}

	public void setCatering(Boolean catering) {
		this.catering = catering;
	}

	public Boolean getDj() {
		return dj;
	}

	public void setDj(Boolean dj) {
		this.dj = dj;
	}

}

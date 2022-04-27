package ar.com.eventsocial.backend.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MercadoPagoRequest {

	@JsonProperty("fechaReserva")
	private Date fechaReserva;
	
	@JsonProperty("HoraReserva")
	private String horaReserva;
	
	@JsonProperty("urlBack")
	private String urlBack;

	@JsonProperty("idInmueble")
	private int idInmueble;
	
	@JsonProperty("costo")
	private float costo;
	
	@JsonProperty("cantPersonas")
	private int cantPersonas;

	@JsonProperty("title")
	private String title;
	

	//-- GETTER AND SETTER--//
	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getHoraReserva() {
		return horaReserva;
	}

	public void setHoraReserva(String horaReserva) {
		this.horaReserva = horaReserva;
	}

	public int getIdInmueble() {
		return idInmueble;
	}

	public void setIdInmueble(int idInmueble) {
		this.idInmueble = idInmueble;
	}

	public float getCosto() {
		return costo;
	}

	public void setCosto(float costo) {
		this.costo = costo;
	}

	public int getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(int cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrlBack() {
		return urlBack;
	}

	public void setUrlBack(String urlBack) {
		this.urlBack = urlBack;
	}	
	
}

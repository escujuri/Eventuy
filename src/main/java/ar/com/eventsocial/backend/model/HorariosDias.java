package ar.com.eventsocial.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HorariosDias {

	@JsonProperty("lunes")
	private List<String> lunes;

	@JsonProperty("martes")
	private List<String> martes;

	@JsonProperty("miercoles")
	private List<String> miercoles;

	@JsonProperty("jueves")
	private List<String> jueves;
	
	@JsonProperty("viernes")
	private List<String> viernes;
	
	@JsonProperty("sabado")
	private List<String> sabado;
	
	@JsonProperty("domingo")
	private List<String> domingo;
	
	@JsonProperty("general")
	private List<String> general;
	
	@JsonProperty("feriado")
	private List<String> feriado;

	
	//-- GETTER AND SETTER --//
	public List<String> getLunes() {
		return lunes;
	}

	public void setLunes(List<String> lunes) {
		this.lunes = lunes;
	}

	public List<String> getMartes() {
		return martes;
	}

	public void setMartes(List<String> martes) {
		this.martes = martes;
	}

	public List<String> getMiercoles() {
		return miercoles;
	}

	public void setMiercoles(List<String> miercoles) {
		this.miercoles = miercoles;
	}

	public List<String> getJueves() {
		return jueves;
	}

	public void setJueves(List<String> jueves) {
		this.jueves = jueves;
	}

	public List<String> getViernes() {
		return viernes;
	}

	public void setViernes(List<String> viernes) {
		this.viernes = viernes;
	}

	public List<String> getSabado() {
		return sabado;
	}

	public void setSabado(List<String> sabado) {
		this.sabado = sabado;
	}

	public List<String> getDomingo() {
		return domingo;
	}

	public void setDomingo(List<String> domingo) {
		this.domingo = domingo;
	}

	public List<String> getGeneral() {
		return general;
	}

	public void setGeneral(List<String> general) {
		this.general = general;
	}

	public List<String> getFeriado() {
		return feriado;
	}

	public void setFeriado(List<String> feriado) {
		this.feriado = feriado;
	}
	
}
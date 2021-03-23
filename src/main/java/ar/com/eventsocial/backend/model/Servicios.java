package ar.com.eventsocial.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Servicios")
public class Servicios extends GenericEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "inmueble_id")
	private Inmueble inmueble;

	@Column(name = "piscina")
	private Boolean piscina;

	@Column(name = "quincho")
	private Boolean quincho;

	@Column(name = "parrilla")
	private Boolean parrilla;

	@Column(name = "mascotas")
	private Boolean mascotas;

	@Column(name = "wifi")
	private Boolean wifi;

	@Column(name = "catering")
	private Boolean catering;

	@Column(name = "dj")
	private Boolean dj;

	public Servicios() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Servicios(Long id, Inmueble inmueble, Boolean piscina, Boolean quincho, Boolean parrilla, Boolean mascotas,
			Boolean wifi, Boolean catering, Boolean dj) {
		super();
		this.id = id;
		this.inmueble = inmueble;
		this.piscina = piscina;
		this.quincho = quincho;
		this.parrilla = parrilla;
		this.mascotas = mascotas;
		this.wifi = wifi;
		this.catering = catering;
		this.dj = dj;
	}

	@Override
	public String toString() {
		return "Servicios [id=" + id + ", inmueble=" + inmueble + ", piscina=" + piscina + ", quincho=" + quincho
				+ ", parrilla=" + parrilla + ", mascotas=" + mascotas + ", wifi=" + wifi + ", catering=" + catering
				+ ", dj=" + dj + "]";
	}

}

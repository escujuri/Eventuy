package ar.com.eventsocial.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedNativeQueries({
	@NamedNativeQuery(name = Servicios.UPDATE_SERVICE_BY_ID, query = "Update"
			+ "  Servicios set servicios = ? , servicios_seguridad = ?  Where "
			+ "  inmueble_id = ?", resultClass = Servicios.class)
})

@Entity
@Table(name = "Servicios")
public class Servicios extends GenericEntity<Long> {

	private static final long serialVersionUID = 1L;

	public static final String UPDATE_SERVICE_BY_ID = "ActualizarServicioByIdinmueble";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "inmueble_id")
	private Inmueble inmueble;

	@Column(name = "servicios")
	private String servicios;

	@Column(name = "servicios_seguridad")
	private String serviciosSeguridad;
	
	public Long getId() {
		return id;
	}

	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}

	public String getServicios() {
		return servicios;
	}

	public void setServicios(String servicios) {
		this.servicios = servicios;
	}
	
	public String getServiciosSeguridad() {
		return serviciosSeguridad;
	}

	public void setServiciosSeguridad(String serviciosSeguridad) {
		this.serviciosSeguridad = serviciosSeguridad;
	}

	@Override
	public String toString() {
		return "Servicios [id=" + id + ", inmueble=" + inmueble + ", servicios=" + servicios + "]";
	}
}

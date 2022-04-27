package ar.com.eventsocial.backend.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@NamedNativeQueries({
	@NamedNativeQuery(name = Fotos.UPDATE_PHOTO_BY_ID, query = "Update"
			+ "  FotosInmuebles set urlFoto = ?  Where "
			+ "  inmueble_id = ?", resultClass = Fotos.class)
})


@Entity
@Table(name = "FotosInmuebles")
public class Fotos extends GenericEntity<Long> {

	private static final long serialVersionUID = 1L;

	public static final String UPDATE_PHOTO_BY_ID = "ActualizarFotosByIdinmueble";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "inmueble_id")
	private Inmueble inmueble;
	
	@Column(name = "urlFoto", columnDefinition="LONGTEXT")
	private String urlFoto;

	
	public Inmueble getInmueble() {
		return inmueble;
	}

	public void setInmueble(Inmueble inmueble) {
		this.inmueble = inmueble;
	}


	public Long getId() {
		return id;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Fotos [id=" + id + ", inmueble=" + inmueble + ", urlFoto=" + urlFoto + "]";
	}
}

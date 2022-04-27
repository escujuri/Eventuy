package ar.com.eventsocial.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;


@NamedNativeQueries({
	@NamedNativeQuery(name = Social.FIND_COMMENT_BY_INMUEBLEID, query = "select * from Social"
			+ "  where inmueble_id = ?", resultClass = Social.class)
})

@Entity
@Table(name = "Social")
public class Social {

	public static final String FIND_COMMENT_BY_INMUEBLEID = "buscarComentariosByInmuebleId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
		
	@Column(name = "usuario_id")
	private String usuario_id;
		
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "created_at")
	private Date created_at;

	@Column(name = "ranking")
	private Float ranking;
	
	@JoinColumn(name = "inmueble_id")
	private String inmueble_id;

	// -- GETTER AND SETTER--//
	public Long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Float getRanking() {
		return ranking;
	}

	public void setRanking(Float ranking) {
		this.ranking = ranking;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public String getInmueble_id() {
		return inmueble_id;
	}

	public void setInmueble_id(String inmueble_id) {
		this.inmueble_id = inmueble_id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario_id() {
		return usuario_id;
	}

	public void setUsuario_id(String usuario_id) {
		this.usuario_id = usuario_id;
	}
}

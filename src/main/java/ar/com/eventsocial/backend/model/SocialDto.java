package ar.com.eventsocial.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocialDto {

	@JsonProperty("nombreUsuario")
	private String nombreUsuario;
	
	@JsonProperty("urlAvatar")
	private String urlAvatar;
	
	@JsonProperty("created_at")
	private Date created_at;
	
	@JsonProperty("comment")
	private String comment;

	@JsonProperty("ranking")
	private Float ranking;
	
	@JsonProperty("inmuebleId")
	private String inmuebleId;

	// -- GETTER AND SETTER--//
	public Float getRanking() {
		return ranking;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setRanking(Float ranking) {
		this.ranking = ranking;
	}

	public String getInmuebleId() {
		return inmuebleId;
	}

	public void setInmuebleId(String inmuebleId) {
		this.inmuebleId = inmuebleId;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getUrlAvatar() {
		return urlAvatar;
	}

	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

}

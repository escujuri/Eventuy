package ar.com.eventsocial.backend.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import ar.com.eventsocial.backend.model.Usuario;
import ar.com.eventsocial.backend.model.UsuarioDto;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message" })
public class UserInfoResponseDTO {

	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("usuario")
	private UsuarioDto usuario;
	@JsonProperty("listaUsuario")
	private List<UsuarioDto> listUsuario;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public List<UsuarioDto> getListUsuario() {
		return listUsuario;
	}

	public void setListUsuario(List<UsuarioDto> listUsuario) {
		this.listUsuario = listUsuario;
	}

}

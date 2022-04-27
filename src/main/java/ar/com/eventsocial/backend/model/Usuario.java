package ar.com.eventsocial.backend.model;

import java.util.Date;

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
	@NamedNativeQuery(name = Usuario.GET_USER_INFO, query = "select *"
			+ "  from Usuarios" 
			+ "  where usuario_id = ?", resultClass = Usuario.class),
	@NamedNativeQuery(name = Usuario.GET_LIST_USER_INFO, query = "select *"
			+ "  from Usuarios", resultClass = Usuario.class)
})


@Entity
@Table(name = "Usuarios")
public class Usuario extends GenericEntity<Long> {

	private static final long serialVersionUID = 1L;
	
	public static final String GET_USER_INFO = "getUserInfo";
	public static final String GET_LIST_USER_INFO = "getListUserInfo";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "edad")
	private Integer edad;

	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "urlAvatar")
	private String urlAvatar;
	
	@Column(name = "documento")
	private String documento;

	@Column(name = "fechaNacimiento")
	private Date fechaNacimiento;
	
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Login login;

	public Usuario() {
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getUrlAvatar() {
		return urlAvatar;
	}

	public void setUrlAvatar(String urlAvatar) {
		this.urlAvatar = urlAvatar;
	}
	
	
	public Usuario(Long id, String nombre, Integer edad, String telefono, String urlAvatar, String documento,
			Date fechaNacimiento, Login login) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.telefono = telefono;
		this.urlAvatar = urlAvatar;
		this.documento = documento;
		this.fechaNacimiento = fechaNacimiento;
		this.login = login;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", telefono=" + telefono + ", urlAvatar="
				+ urlAvatar + ", documento=" + documento + ", fechaNacimiento=" + fechaNacimiento + ", login=" + login
				+ "]";
	}

}

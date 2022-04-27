package ar.com.eventsocial.backend.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@NamedNativeQueries({
	@NamedNativeQuery(name = Login.LOGIN_USER, query = "select *"
			+ "  from Usuario_Login" 
			+ "  where email = ?", resultClass = Login.class),
	@NamedNativeQuery(name = Login.LOGIN_DUPLICATE_USER, query = "select *"
			+ "  from Usuario_Login" 
			+ "  where email = ?", resultClass = Login.class),
	@NamedNativeQuery(name = Login.EMAIL_BY_USERID, query = "select email"
			+ "  from Usuario_Login" 
			+ "  where id = ?", resultClass = Login.class),
})


@Entity
@Table(name = "Usuario_Login")
public class Login extends GenericEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String LOGIN_USER = "LoginByUser";
	public static final String LOGIN_DUPLICATE_USER = "LoginByUserDuplicate";
	public static final String EMAIL_BY_USERID = "EmailByUserId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "email")
	private String email;

	@Column(name = "pass")
	private String password_;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_creacion")
	private Date fecha_creacion;
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "login")
	private Usuario usuario;

	public Login() {

	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getPassword_() {
		return password_;
	}

	public void setPassword_(String password_) {
		this.password_ = password_;
	}


	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}


	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Login(Long id, String email, String password_, Date fecha_creacion, Usuario usuario) {
		super();
		this.id = id;
		this.email = email;
		this.password_ = password_;
		this.fecha_creacion = fecha_creacion;
		this.usuario = usuario;
	}


	@Override
	public String toString() {
		return "Login [id=" + id + ", email=" + email + ", password_=" + password_ + ", fecha_creacion="
				+ fecha_creacion + ", usuario=" + usuario + "]";
	}
}

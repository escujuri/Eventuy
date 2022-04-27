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

@NamedNativeQueries({
		@NamedNativeQuery(name = Inmueble.FIND_PROPERTY_BY_IDUSER, query = "select * from Inmuebles"
				+ "  where idOriginante = ?", resultClass = Inmueble.class),
		@NamedNativeQuery(name = Inmueble.SEACH_BY_WORD, query = "select * from Inmuebles", resultClass = Inmueble.class),
		@NamedNativeQuery(name = Inmueble.DOWN_PROPERTY_BY_IDUSER, query = "Update"
				+ "  Inmuebles set publicada = 0 " + "  Where idOriginante = ?"
				+ "  and idInmueble = ?", resultClass = Inmueble.class),
		@NamedNativeQuery(name = Inmueble.SEACH_BY_ID_INMUEBLE, query = "select * from Inmuebles"
				+ "  where idInmueble = ?", resultClass = Inmueble.class) })

@Entity
@Table(name = "Inmuebles")
public class Inmueble extends GenericEntity<Long> {

	public static final String FIND_PROPERTY_BY_IDUSER = "buscarInmueblesByUserID";

	public static final String DOWN_PROPERTY_BY_IDUSER = "desHabilitarInmueblesByUserID";

	public static final String SEACH_BY_WORD = "inmueblesByKeyWord";

	public static final String SEACH_BY_ID_INMUEBLE = "inmueblesByIdInmueble";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idInmueble")
	private Long idInmueble;

	@Column(name = "idOriginante")
	private Long idOriginante;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "tipoLugar")
	private String tipoLugar;

	@Column(name = "latitud")
	private String latitud;

	@Column(name = "longitud")
	private String longitud;

	@Column(name = "calle")
	private String calle;

	@Column(name = "cp")
	private String cp;

	@Column(name = "altura")
	private Integer altura;

	@Column(name = "localidad")
	private String localidad;

	@Column(name = "ciudad")
	private String ciudad;

	@Column(name = "provincia")
	private String provincia;

	@Column(name = "pais")
	private String pais;

	@Column(name = "descripcion", columnDefinition = "LONGTEXT")
	private String descripcion;

	@Column(name = "precio")
	private Float precio;

	@Column(name = "puntuacion")
	private Float puntuacion;

	@Column(name = "maxPersonas")
	private Integer maxPersonas;

	@Column(name = "fechaDisponibilidad")
	private Date fechaDisponibilidad;

	@Column(name = "fechaPublicacion")
	private Date fechaPublicacion;

	@Column(name = "horariosReserva")
	private String horariosReserva;

	@Column(name = "horariosDias")
	private String HorariosDias;

	@Column(name = "esPrecioFijo")
	private Boolean esPrecioFijo;

	@Column(name = "disponibilidadPersonas")
	private Integer disponibilidadPersonas;

	@Column(name = "publicada")
	private Boolean publicada;

	@Column(name = "fechaNoDisponibles")
	private String fechaNoDisponibles;
	
	@Column(name = "nombreContacto")
	private String nombreContacto;
	
	@Column(name = "apellidoContacto")
	private String apellidoContacto;
	
	@Column(name = "telefonoContacto")
	private String telefonoContacto;
	

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "inmueble")
	private Servicios servicios;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "inmueble")
	private Fotos fotos;
	

	@Column(name = "inmuebleHabilitado")
	private Boolean habilitado;
	
	@Override
	public Long getId() {
		return null;
	}

	public Long getIdInmueble() {
		return idInmueble;
	}

	public Fotos getFotos() {
		return fotos;
	}

	public void setFotos(Fotos fotos) {
		this.fotos = fotos;
	}

	public Servicios getServicios() {
		return servicios;
	}

	public void setServicios(Servicios servicios) {
		this.servicios = servicios;
	}

	public Inmueble() {
	}

	public Long getIdOriginante() {
		return idOriginante;
	}

	public void setIdOriginante(Long idOriginante) {
		this.idOriginante = idOriginante;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTipoLugar() {
		return tipoLugar;
	}

	public void setTipoLugar(String tipoLugar) {
		this.tipoLugar = tipoLugar;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	public Float getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Float puntuacion) {
		this.puntuacion = puntuacion;
	}

	public Integer getMaxPersonas() {
		return maxPersonas;
	}

	public void setMaxPersonas(Integer maxPersonas) {
		this.maxPersonas = maxPersonas;
	}

	public Date getFechaDisponibilidad() {
		return fechaDisponibilidad;
	}

	public void setFechaDisponibilidad(Date fechaDisponibilidad) {
		this.fechaDisponibilidad = fechaDisponibilidad;
	}

	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Boolean getHabilitado() {
		return habilitado;
	}

	public void setHabilitado(Boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getLatitud() {
		return latitud;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public String getLongitud() {
		return longitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getHorariosReserva() {
		return horariosReserva;
	}

	public void setHorariosReserva(String horariosReserva) {
		this.horariosReserva = horariosReserva;
	}

	public String getHorariosDias() {
		return HorariosDias;
	}

	public void setHorariosDias(String horariosDias) {
		HorariosDias = horariosDias;
	}

	public void setIdInmueble(Long idInmueble) {
		this.idInmueble = idInmueble;
	}

	public Boolean getEsPrecioFijo() {
		return esPrecioFijo;
	}

	public void setEsPrecioFijo(Boolean esPrecioFijo) {
		this.esPrecioFijo = esPrecioFijo;
	}

	public Integer getDisponibilidadPersonas() {
		return disponibilidadPersonas;
	}

	public void setDisponibilidadPersonas(Integer disponibilidadPersonas) {
		this.disponibilidadPersonas = disponibilidadPersonas;
	}

	public Boolean getPublicada() {
		return publicada;
	}

	public void setPublicada(Boolean publicada) {
		this.publicada = publicada;
	}

	public String getFechaNoDisponibles() {
		return fechaNoDisponibles;
	}

	public void setFechaNoDisponibles(String fechaNoDisponibles) {
		this.fechaNoDisponibles = fechaNoDisponibles;
	}
	
	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getApellidoContacto() {
		return apellidoContacto;
	}

	public void setApellidoContacto(String apellidoContacto) {
		this.apellidoContacto = apellidoContacto;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	
	public Inmueble(Long idInmueble, Long idOriginante, String titulo, String tipoLugar, String latitud,
			String longitud, String calle, String cp, Integer altura, String localidad, String ciudad, String provincia,
			String pais, String descripcion, Float precio, Float puntuacion, Integer maxPersonas,
			Date fechaDisponibilidad, Date fechaPublicacion, String horariosReserva, String horariosDias,
			Boolean esPrecioFijo, Integer disponibilidadPersonas, Boolean publicada, String fechaNoDisponibles,
			String nombreContacto, String apellidoContacto, String telefonoContacto, String emailContacto,
			Servicios servicios, Fotos fotos, Boolean habilitado) {
		super();
		this.idInmueble = idInmueble;
		this.idOriginante = idOriginante;
		this.titulo = titulo;
		this.tipoLugar = tipoLugar;
		this.latitud = latitud;
		this.longitud = longitud;
		this.calle = calle;
		this.cp = cp;
		this.altura = altura;
		this.localidad = localidad;
		this.ciudad = ciudad;
		this.provincia = provincia;
		this.pais = pais;
		this.descripcion = descripcion;
		this.precio = precio;
		this.puntuacion = puntuacion;
		this.maxPersonas = maxPersonas;
		this.fechaDisponibilidad = fechaDisponibilidad;
		this.fechaPublicacion = fechaPublicacion;
		this.horariosReserva = horariosReserva;
		HorariosDias = horariosDias;
		this.esPrecioFijo = esPrecioFijo;
		this.disponibilidadPersonas = disponibilidadPersonas;
		this.publicada = publicada;
		this.fechaNoDisponibles = fechaNoDisponibles;
		this.nombreContacto = nombreContacto;
		this.apellidoContacto = apellidoContacto;
		this.telefonoContacto = telefonoContacto;
		this.servicios = servicios;
		this.fotos = fotos;
		this.habilitado = habilitado;
	}

	@Override
	public String toString() {
		return "Inmueble [idInmueble=" + idInmueble + ", idOriginante=" + idOriginante + ", titulo=" + titulo
				+ ", tipoLugar=" + tipoLugar + ", latitud=" + latitud + ", longitud=" + longitud + ", calle=" + calle
				+ ", cp=" + cp + ", altura=" + altura + ", localidad=" + localidad + ", ciudad=" + ciudad
				+ ", provincia=" + provincia + ", pais=" + pais + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", puntuacion=" + puntuacion + ", maxPersonas=" + maxPersonas + ", fechaDisponibilidad="
				+ fechaDisponibilidad + ", fechaPublicacion=" + fechaPublicacion + ", horariosReserva="
				+ horariosReserva + ", HorariosDias=" + HorariosDias + ", esPrecioFijo=" + esPrecioFijo
				+ ", disponibilidadPersonas=" + disponibilidadPersonas + ", publicada=" + publicada
				+ ", fechaNoDisponibles=" + fechaNoDisponibles + ", nombreContacto=" + nombreContacto
				+ ", apellidoContacto=" + apellidoContacto + ", telefonoContacto=" + telefonoContacto
				+ ", servicios=" + servicios + ", fotos=" + fotos + ", habilitado="
				+ habilitado + "]";
	}
	
}

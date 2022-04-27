package ar.com.eventsocial.backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@NamedNativeQueries({ @NamedNativeQuery(name = Transacciones.UPDATE_PAYMENT, query = "Update"
		+ "  Transacciones set statusPago = ?, formaPago = ?, currency_id = ?"
		+ "  , fechaAprobado = ? , montoNetoRecibido = ? , payment_id = ? "
		+ "  , paymentType = ? , paymentTypeId = ? Where idempotencia_id = ? ", resultClass = Transacciones.class),
@NamedNativeQuery(name = Transacciones.SEACH_BOOKING_BY_ID_USERID, query = "select *"
			+ "  from Transacciones" 
			+ "  where userId = ?", resultClass = Transacciones.class),
@NamedNativeQuery(name = Transacciones.SEACH_BOOKING_BY_ID_OWNER_USER_ID, query = "select *"
		+ "  from Transacciones" 
		+ "  where ownerUserId = ?", resultClass = Transacciones.class),
@NamedNativeQuery(name = Transacciones.SEACH_BOOKING_CHAT_BY_USER_ID, query = "select *"
		+ "  from Transacciones" 
		+ "  where (ownerUserId = ? or userId = ?) and ownerApproval='ACCEPTED'", resultClass = Transacciones.class),
@NamedNativeQuery(name = Transacciones.SEACH_BOOKING_BY_ID_INMUEBLEID, query = "select *"
		+ "  from Transacciones" 
		+ "  where idInmueble = ? and"
		+ "  fechaReserva >= CURDATE()", resultClass = Transacciones.class),
@NamedNativeQuery(name = Transacciones.UPDATE_APPROVAL, query = "Update"
		+ "  Transacciones set ownerApproval = ? Where payment_id = ? ", resultClass = Transacciones.class),

})

@Entity
@Table(name = "Transacciones")
public class Transacciones extends GenericEntity<Long> {

	private static final long serialVersionUID = 1L;

	public static final String UPDATE_PAYMENT = "updatePaymentByIdempotencyId";
	public static final String SEACH_BOOKING_BY_ID_USERID = "seachBookingsByUserId";
	public static final String SEACH_BOOKING_BY_ID_OWNER_USER_ID = "seachBookingsByOwnerUserId";
	public static final String SEACH_BOOKING_CHAT_BY_USER_ID = "seachBookingsChatByUserId";
	public static final String SEACH_BOOKING_BY_ID_INMUEBLEID= "seachBookingsByInmuebleId";
	public static final String UPDATE_APPROVAL= "updateApprovalByPaymentId";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "idempotencia_id")
	private String idempotencia_id;

	@Column(name = "userId")
	private Long userId;

	@Column(name = "request_id")
	private String request_id;

	@Column(name = "fechaReserva")
	private Date fechaReserva;

	@Column(name = "horarioReserva")
	private String horarioReserva;

	@Column(name = "cantPersonas")
	private Integer cantPersonas;

	@Column(name = "fechaPago")
	private Date fechaPago;

	@Column(name = "statusPago")
	private String statusPago;

	@Column(name = "formaPago")
	private String formaPago;

	@Column(name = "monto")
	private Float monto;

	@Column(name = "idInmueble")
	private Integer idInmueble;

	@Column(name = "currency_id")
	private String currency_id;

	@Column(name = "collector_id")
	private String collector_id;

	@Column(name = "fechaAprobado")
	private Date fechaAprobado;

	@Column(name = "montoNetoRecibido")
	private Float montoNetoRecibido;

	@Column(name = "payment_id")
	private String payment_id;

	@Column(name = "paymentType")
	private String paymentType;

	@Column(name = "paymentTypeId")
	private String paymentTypeId;
	
	@Column(name = "ownerApproval")
	private String ownerApproval;
	
	@Column(name = "ownerUserId")
	private String ownerUserId;

	// --GETTER AND SETTER--//
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date date) {
		this.fechaPago = date;
	}

	public String getStatusPago() {
		return statusPago;
	}

	public void setStatusPago(String statusPago) {
		this.statusPago = statusPago;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public Integer getIdInmueble() {
		return idInmueble;
	}

	public void setIdInmueble(Integer idInmueble) {
		this.idInmueble = idInmueble;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public String getHorarioReserva() {
		return horarioReserva;
	}

	public void setHorarioReserva(String horarioReserva) {
		this.horarioReserva = horarioReserva;
	}

	public Integer getCantPersonas() {
		return cantPersonas;
	}

	public void setCantPersonas(Integer cantPersonas) {
		this.cantPersonas = cantPersonas;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}

	public String getCollector_id() {
		return collector_id;
	}

	public void setCollector_id(String collector_id) {
		this.collector_id = collector_id;
	}

	public Date getFechaAprobado() {
		return fechaAprobado;
	}

	public void setFechaAprobado(Date fechaAprobado) {
		this.fechaAprobado = fechaAprobado;
	}

	public Float getMontoNetoRecibido() {
		return montoNetoRecibido;
	}

	public void setMontoNetoRecibido(Float montoNetoRecibido) {
		this.montoNetoRecibido = montoNetoRecibido;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(String paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getIdempotencia_id() {
		return idempotencia_id;
	}

	public void setIdempotencia_id(String idempotencia_id) {
		this.idempotencia_id = idempotencia_id;
	}

	public String getOwnerApproval() {
		return ownerApproval;
	}

	public void setOwnerApproval(String ownerApproval) {
		this.ownerApproval = ownerApproval;
	}

	public String getOwnerUserId() {
		return ownerUserId;
	}

	public void setOwnerUserId(String ownerUserId) {
		this.ownerUserId = ownerUserId;
	}

}

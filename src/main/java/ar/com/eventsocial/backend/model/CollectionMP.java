package ar.com.eventsocial.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectionMP {

	@JsonProperty("id")
	private Long paymentId;

	@JsonProperty("date_created")
	private Date date_created;

	@JsonProperty("date_approved")
	private Date date_approved;

	@JsonProperty("payer")
	private PayerMP payer;

	@JsonProperty("status")
	private String status;

	@JsonProperty("status_detail")
	private String status_detail;

	@JsonProperty("transaction_amount")
	private Float transaction_amount;

	@JsonProperty("net_received_amount")
	private Float net_received_amount;

	@JsonProperty("currency_id")
	private String currency_id;

	@JsonProperty("payment_type")
	private String paymentType;

	@JsonProperty("payment_method_id")
	private String paymentMethodId;

	@JsonProperty("collector")
	private CollectorMP collector;

	@JsonProperty("external_reference")
	private String external_reference;

		
	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public Date getDate_approved() {
		return date_approved;
	}

	public void setDate_approved(Date date_approved) {
		this.date_approved = date_approved;
	}

	public PayerMP getPayer() {
		return payer;
	}

	public void setPayer(PayerMP payer) {
		this.payer = payer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus_detail() {
		return status_detail;
	}

	public void setStatus_detail(String status_detail) {
		this.status_detail = status_detail;
	}

	public Float getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(Float transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public Float getNet_received_amount() {
		return net_received_amount;
	}

	public void setNet_received_amount(Float net_received_amount) {
		this.net_received_amount = net_received_amount;
	}

	public String getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(String paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public CollectorMP getCollector() {
		return collector;
	}

	public void setCollector(CollectorMP collector) {
		this.collector = collector;
	}

	public String getExternal_reference() {
		return external_reference;
	}

	public void setExternal_reference(String external_reference) {
		this.external_reference = external_reference;
	}
	
}

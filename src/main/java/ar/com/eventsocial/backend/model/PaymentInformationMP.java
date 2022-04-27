package ar.com.eventsocial.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentInformationMP {

	@JsonProperty("id")
	private int id;
	
	@JsonProperty("date_created")
	private Date date_created;
	
	@JsonProperty("date_approved")
	private Date date_approved;
	
	@JsonProperty("date_last_updated")
	private Date date_last_updated;
	
	@JsonProperty("money_release_date")
	private Date money_release_date;
	
	@JsonProperty("payment_method_id")
	private String payment_method_id;
	
	@JsonProperty("payment_type_id")
	private String payment_type_id;
	
	@JsonProperty("idstatus")
	private String idstatus;
	
	@JsonProperty("status_detail")
	private String status_detail;
	
	@JsonProperty("currency_id")
	private String currency_id;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("collector_id")
	private String collector_id;
		
	@JsonProperty("payer")
	private PayerMP payer;
	
	@JsonProperty("transaction_amount")
	private Float transaction_amount;
	
	@JsonProperty("transaction_amount_refunded")
	private String transaction_amount_refunded;


	//-- GETTER AND SETTER--//	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Date getDate_last_updated() {
		return date_last_updated;
	}

	public void setDate_last_updated(Date date_last_updated) {
		this.date_last_updated = date_last_updated;
	}

	public Date getMoney_release_date() {
		return money_release_date;
	}

	public void setMoney_release_date(Date money_release_date) {
		this.money_release_date = money_release_date;
	}

	public String getPayment_method_id() {
		return payment_method_id;
	}

	public void setPayment_method_id(String payment_method_id) {
		this.payment_method_id = payment_method_id;
	}

	public String getPayment_type_id() {
		return payment_type_id;
	}

	public void setPayment_type_id(String payment_type_id) {
		this.payment_type_id = payment_type_id;
	}

	public String getIdstatus() {
		return idstatus;
	}

	public void setIdstatus(String idstatus) {
		this.idstatus = idstatus;
	}

	public String getStatus_detail() {
		return status_detail;
	}

	public void setStatus_detail(String status_detail) {
		this.status_detail = status_detail;
	}

	public String getCurrency_id() {
		return currency_id;
	}

	public void setCurrency_id(String currency_id) {
		this.currency_id = currency_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCollector_id() {
		return collector_id;
	}

	public void setCollector_id(String collector_id) {
		this.collector_id = collector_id;
	}

	public PayerMP getPayer() {
		return payer;
	}

	public void setPayer(PayerMP payer) {
		this.payer = payer;
	}

	public Float getTransaction_amount() {
		return transaction_amount;
	}

	public void setTransaction_amount(Float transaction_amount) {
		this.transaction_amount = transaction_amount;
	}

	public String getTransaction_amount_refunded() {
		return transaction_amount_refunded;
	}

	public void setTransaction_amount_refunded(String transaction_amount_refunded) {
		this.transaction_amount_refunded = transaction_amount_refunded;
	}

}

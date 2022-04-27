package ar.com.eventsocial.backend.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StatusChangePaymentMP {

	@JsonProperty("action")
	private String action;

	@JsonProperty("id")
	private String paymentId;
	
	@JsonProperty("updateAt")
	private Date updateAt;

	@JsonProperty("type")
	private String type;

	@JsonProperty("resource")
	private String resource;

	@JsonProperty("topic")
	private String topic;

	// -- GETTER AND SETTER--//
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Object getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

}

package ar.com.eventsocial.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CollectorMP {

	@JsonProperty("id")
	private Long collectorId;

	public Long getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(Long collectorId) {
		this.collectorId = collectorId;
	}

}

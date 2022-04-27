package ar.com.eventsocial.backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;



public class ResponseCollectionMP {

	@JsonProperty("collection")
	private CollectionMP collection;

	public CollectionMP getCollection() {
		return collection;
	}

	public void setCollection(CollectionMP collection) {
		this.collection = collection;
	}
}

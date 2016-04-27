package api.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class createEntryRepresentation {
	
	private int id;
	
	public createEntryRepresentation(){
		// Jackson deserialization
	}

	public createEntryRepresentation(int id) {
		this.id = id;
	}
	
	@JsonProperty
	public int getId() {
		return id;
	}
}

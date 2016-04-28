package api.representation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateEntryRepresentation {
	
	private int id;
	
	public CreateEntryRepresentation(){
		// Jackson deserialization
	}

	public CreateEntryRepresentation(int id) {
		this.id = id;
	}
	
	@JsonProperty
	public int getId() {
		return id;
	}
}

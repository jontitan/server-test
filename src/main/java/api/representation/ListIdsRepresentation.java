package api.representation;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import server.db.InMemoryDatabase;

public class ListIdsRepresentation {
	private Set<Integer> ids;
	
	public ListIdsRepresentation(){
		// Jackson deserialization
	}

	public ListIdsRepresentation(InMemoryDatabase imd) {
		this.ids = new HashSet<Integer>(imd.getIds());
	}
	
	@JsonProperty
	public Set<Integer> getIds() {
		return ids;
	}
}

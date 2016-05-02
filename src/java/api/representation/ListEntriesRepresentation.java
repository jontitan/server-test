package api.representation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import server.db.InMemoryDatabase;

public class ListEntriesRepresentation {
	private List<MovieEntryRepresentation> entries;

	public ListEntriesRepresentation(InMemoryDatabase imd) {
		this.entries = new ArrayList<MovieEntryRepresentation>(imd.getLibrary().values());
	}
	
	@JsonProperty
	public List<MovieEntryRepresentation> getEntries() {
		return entries;
	}
}

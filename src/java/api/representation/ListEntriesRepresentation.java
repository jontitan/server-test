package api.representation;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import server.db.InMemoryDatabase;
import server.db.MovieEntry;

public class ListEntriesRepresentation {
	private List<MovieEntry> entries;

	public ListEntriesRepresentation(InMemoryDatabase imd) {
		this.entries = new ArrayList<MovieEntry>(imd.getLibrary().values());
	}
	
	@JsonProperty
	public List<MovieEntry> getEntries() {
		return entries;
	}
}
